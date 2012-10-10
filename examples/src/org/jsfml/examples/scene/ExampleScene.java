package org.jsfml.examples.scene;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import java.util.LinkedList;
import java.util.Random;

/**
 * An example scene.
 */
public class ExampleScene implements Scene {
    private final VertexArray background = new VertexArray(PrimitiveType.QUADS);
    private final Texture jsfmlLogoTexture = new Texture();
    private final Sprite jsfmlLogo = new Sprite();
    private RenderStates jsfmlLogoStates = RenderStates.DEFAULT;

    private final Font nakadaiFont = new Font();
    private final Font freeSansFont = new Font();

    private final Text instructionsText =
            new Text("Left mouse button and move to rotate logo\n" +
                    "Right mouse button and move to wave logo\n" +
                    "Mouse wheel to scale logo\n\n" +
                    "Press A to add an entity\n" +
                    "Press D to delete an entity\n" +
                    "Press Escape to quit", nakadaiFont);
    private final Text instructionsTextShadow = new Text(instructionsText.getString(), nakadaiFont);

    private final Text infoText = new Text();
    private final Text infoTextShadow = new Text();

    private final Text fpsText = new Text();
    private final Text fpsTextShadow = new Text();

    private final Text eventText = new Text();
    private final Text eventTextShadow = new Text();

    private final RectangleShape textBackground = new RectangleShape();

    private Text noShadersText = null;

    //screen size
    private int screenWidth, screenHeight;

    //FPS counter
    private int fpsFrames = 0;

    //Event counter
    private int events = 0;

    private float timer = 1.0f;

    //wave shader
    private Shader waveXShader = null;
    private float waveXOffset = 0.0f;
    private float waveXLen = 0.25f;
    private float waveXAmp = 0.0f;

    //mouse features state
    private boolean mouseDown = false;
    private Vector2i mouseDownPos;
    private Mouse.Button mouseButton;
    private float mouseLogoRotationStart;
    private float mouseWaveXOffsetStart;
    private float mouseWaveXAmpStart;

    //entities (look at the bottom of the file for the class definition)
    private final LinkedList<Entity> entities = new LinkedList<Entity>();

    //quit flag
    private boolean quit = false;

    @Override
    public void initialize(RenderTarget target) throws Exception {
        screenWidth = target.getSize().x;
        screenHeight = target.getSize().y;

        //Create background
        background.add(new Vertex(new Vector2f(0, 0), Color.RED));
        background.add(new Vertex(new Vector2f(target.getSize().x, 0), Color.BLUE));
        background.add(new Vertex(new Vector2f(target.getSize().x, target.getSize().y), Color.GREEN));
        background.add(new Vertex(new Vector2f(0, target.getSize().y), Color.YELLOW));

        //Load logo
        jsfmlLogoTexture.loadFromStream(getClass().getResourceAsStream("/resources/jsfml-y_ex.png"));
        jsfmlLogoTexture.setSmooth(true);

        //Setup logo sprite
        jsfmlLogo.setTexture(jsfmlLogoTexture);
        jsfmlLogo.setOrigin(jsfmlLogoTexture.getSize().x / 2, jsfmlLogoTexture.getSize().y / 2);
        jsfmlLogo.setPosition(target.getSize().x / 2, target.getSize().y / 2);

        //Load font
        nakadaiFont.loadFromStream(
                getClass().getResourceAsStream("/resources/Nakadai.ttf"));

        freeSansFont.loadFromStream(
                getClass().getResourceAsStream("/resources/FreeSans.ttf"));

        //Setup istructions text
        instructionsText.setFont(freeSansFont);
        instructionsText.setStyle(Text.BOLD);
        instructionsText.setColor(new Color(255, 255, 255, 192));
        instructionsText.setCharacterSize(16);
        instructionsText.setPosition(5, 5);

        instructionsTextShadow.setFont(freeSansFont);
        instructionsTextShadow.setStyle(Text.BOLD);
        instructionsTextShadow.setColor(new Color(0, 0, 0, 128));
        instructionsTextShadow.setCharacterSize(16);
        instructionsTextShadow.setPosition(7, 7);

        infoText.setFont(nakadaiFont);
        infoText.setColor(Color.YELLOW);
        infoText.setCharacterSize(24);

        infoTextShadow.setFont(nakadaiFont);
        infoTextShadow.setColor(new Color(0, 0, 0, 96));
        infoTextShadow.setCharacterSize(24);

        updateEntityText();

        FloatRect infoTextBounds = infoText.getGlobalBounds();
        infoText.setPosition(5, target.getSize().y - infoTextBounds.height - 10);
        infoTextShadow.setPosition(7, target.getSize().y - infoTextBounds.height - 8);

        //Setup FPS text
        fpsText.setFont(nakadaiFont);
        fpsText.setColor(Color.GREEN);
        fpsText.setCharacterSize(24);
        fpsText.setString("FPS:");

        fpsTextShadow.setFont(nakadaiFont);
        fpsTextShadow.setColor(new Color(0, 0, 0, 96));
        fpsTextShadow.setCharacterSize(24);
        fpsTextShadow.setString(fpsText.getString());

        FloatRect fpsTextBounds = fpsText.getGlobalBounds();
        fpsText.setPosition(5, infoText.getPosition().y - fpsTextBounds.height - 5);
        fpsTextShadow.setPosition(7, infoText.getPosition().y - fpsTextBounds.height - 3);

        //Setup Events text
        eventText.setFont(nakadaiFont);
        eventText.setColor(Color.CYAN);
        eventText.setCharacterSize(24);
        eventText.setString("Events:");

        eventTextShadow.setFont(nakadaiFont);
        eventTextShadow.setColor(new Color(0, 0, 0, 96));
        eventTextShadow.setCharacterSize(24);
        eventTextShadow.setString(eventText.getString());

        FloatRect eventTextBounds = eventText.getGlobalBounds();
        eventText.setPosition(5, fpsText.getPosition().y - eventTextBounds.height - 5);
        eventTextShadow.setPosition(7, fpsText.getPosition().y - eventTextBounds.height - 3);

        //Setup text background
        textBackground.setOutlineColor(Color.BLACK);
        textBackground.setOutlineThickness(3.0f);
        textBackground.setFillColor(new Color(0, 0, 0, 128));
        textBackground.setSize(new Vector2f(
                target.getSize().x,
                infoTextBounds.height + fpsTextBounds.height + eventTextBounds.height + 18.0f));
        textBackground.setOrigin(0, textBackground.getSize().y);
        textBackground.setPosition(0, target.getSize().y);

        //Load and setup wave shader
        if (Shader.isAvailable()) {
            waveXShader = new Shader();
            waveXShader.loadFromStream(
                    getClass().getResourceAsStream("/resources/wave-x.frag"), Shader.Type.FRAGMENT);

            waveXShader.setParameter("texture", Shader.CURRENT_TEXTURE);
            waveXShader.setParameter("amplitude", waveXAmp);
            waveXShader.setParameter("wavelen", waveXLen);

            jsfmlLogoStates = new RenderStates(waveXShader);
        } else {
            noShadersText = new Text(
                    "Your graphics card\ndoes not support shaders,\nthe wave effect won't work!",
                    nakadaiFont);

            noShadersText.setColor(Color.WHITE);
            noShadersText.setCharacterSize(14);
            noShadersText.setScale(0.9f, 1.0f);
            noShadersText.setStyle(Text.ITALIC);
            FloatRect b = noShadersText.getGlobalBounds();
            noShadersText.setPosition(
                    target.getSize().x - b.width - 5,
                    target.getSize().y - b.height - 5);
        }
    }

    private void updatePerformanceTexts() {
        eventText.setString("Events: " + events);
        eventTextShadow.setString(eventText.getString());

        fpsText.setString("FPS: " + fpsFrames);
        fpsTextShadow.setString(fpsText.getString());
    }

    private void updateEntityText() {
        infoText.setString("Entities: " + entities.size());
        infoTextShadow.setString(infoText.getString());
    }

    @Override
    public void handleEvent(Event event) {
        events++;

        switch (event.type) {
            case CLOSED:
                //The close button has been pressed
                quit = true;
                break;

            case MOUSE_WHEEL_MOVED:
                //The mouse wheel was moved
                int delta = event.asMouseWheelEvent().delta;
                if (delta > 0)
                    jsfmlLogo.scale(1.1f, 1.1f);
                else
                    jsfmlLogo.scale(0.9f, 0.9f);

                break;

            case MOUSE_BUTTON_PRESSED:
                //A mouse button was pressed
                mouseDown = true;
                mouseButton = event.asMouseButtonEvent().button;
                mouseDownPos = event.asMouseEvent().position;
                mouseLogoRotationStart = jsfmlLogo.getRotation();
                mouseWaveXAmpStart = waveXAmp;
                mouseWaveXOffsetStart = waveXOffset;
                break;

            case MOUSE_BUTTON_RELEASED:
                //A mouse button was released
                if (event.asMouseButtonEvent().button == mouseButton) {
                    mouseDown = false;
                }
                break;

            case MOUSE_MOVED:
                //The mouse has been moved
                if (mouseDown) {
                    switch (mouseButton) {
                        case LEFT:
                            //rotate logo
                            Vector2i pos = event.asMouseEvent().position;
                            int deg = pos.x - mouseDownPos.x;

                            jsfmlLogo.setRotation(mouseLogoRotationStart + deg);
                            break;

                        case RIGHT:
                            //change amplitude and offset
                            if (waveXShader != null) {
                                int amp = event.asMouseEvent().position.y - mouseDownPos.y;
                                waveXAmp = mouseWaveXAmpStart + 0.001f * amp;
                                waveXShader.setParameter("amplitude", waveXAmp);

                                int off = event.asMouseEvent().position.x - mouseDownPos.x;
                                waveXOffset = mouseWaveXOffsetStart + 0.001f * off;
                                waveXShader.setParameter("offset", waveXOffset);
                            }

                            break;
                    }
                }
                break;

            case KEY_PRESSED:
                switch (event.asKeyEvent().key) {
                    case A:
                        Entity e = new Entity();
                        e.shape.setPosition(screenWidth / 2, screenHeight / 2);
                        entities.add(e);
                        updateEntityText();
                        break;

                    case D:
                        if (entities.size() > 0) {
                            entities.removeFirst();
                            updateEntityText();
                        }
                        break;

                    case ESCAPE:
                        quit = true;
                        break;
                }
        }
    }

    @Override
    public void update(float dt) {
        //Update FPS
        fpsFrames++;
        timer -= dt;
        if (timer <= 0.0f) {
            updatePerformanceTexts();

            fpsFrames = 0;
            events = 0;

            timer += 1.0f;
        }

        //Update entities
        FloatRect bounds;

        for (Entity e : entities) {
            Vector2f v = Vector2f.mul(e.velocity, dt);

            //Move the entity's shape
            e.shape.move(v);

            //Rotate the entity's shape
            e.shape.rotate(dt * e.rotSpeed);

            //Make sure the entity stays on the screen
            bounds = e.shape.getGlobalBounds();

            if ((v.x < 0 && bounds.left <= 0) ||
                    (v.x > 0 && bounds.left + bounds.width >= screenWidth)) {

                e.velocity = new Vector2f(-e.velocity.x, e.velocity.y);
            }

            if ((v.y < 0 && bounds.top <= 0) ||
                    (v.y > 0 && bounds.top + bounds.height >= screenHeight)) {

                e.velocity = new Vector2f(e.velocity.x, -e.velocity.y);
            }
        }
    }

    @Override
    public void render(RenderTarget target) {
        target.draw(background);

        //Draw entities
        for (Entity e : entities)
            target.draw(e.shape);

        target.draw(jsfmlLogo, jsfmlLogoStates);

        target.draw(instructionsTextShadow);
        target.draw(instructionsText);

        target.draw(textBackground);

        if (noShadersText != null)
            target.draw(noShadersText);

        target.draw(infoTextShadow);
        target.draw(infoText);

        target.draw(fpsTextShadow);
        target.draw(fpsText);

        target.draw(eventTextShadow);
        target.draw(eventText);
    }

    @Override
    public boolean isDone() {
        return quit;
    }

    //Entity class
    static class Entity {
        final static Random random = new Random();
        final static float SIZE_MIN = 5.0f;
        final static float SIZE_MAX = 20.0f;
        final static float VEL_MIN = 25.0f;
        final static float VEL_MAX = 75.0f;
        final static float ROTS_MIN = 13.75f;
        final static float ROTS_MAX = 90.0f;
        final static Color[] colors = new Color[]{
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN,
                Color.WHITE, Color.BLACK};

        static int shapeCounter = 0;

        Shape shape;
        Vector2f velocity;
        float rotSpeed;

        Entity() {
            float size = SIZE_MIN + random.nextFloat() * (SIZE_MAX - SIZE_MIN);

            switch (random.nextInt(3)) {
                case 0:
                    ConvexShape convex = new ConvexShape(3);
                    convex.setPoint(0, new Vector2f(0, -size));
                    convex.setPoint(1, new Vector2f(size, size));
                    convex.setPoint(2, new Vector2f(-size, size));
                    shape = convex;
                    break;

                case 1:
                    shape = new CircleShape(size, 32);
                    break;

                case 2:
                    shape = new RectangleShape(new Vector2f(size, size));
                    break;
            }

            shape.setOutlineColor(Color.BLACK);
            shape.setOutlineThickness(2);

            Color color = new Color(colors[random.nextInt(colors.length)], 96);
            shape.setFillColor(color);

            velocity = new Vector2f(
                    VEL_MIN + random.nextFloat() * (VEL_MAX - VEL_MIN),
                    VEL_MIN + random.nextFloat() * (VEL_MAX - VEL_MIN));

            rotSpeed = ROTS_MIN + random.nextFloat() * (ROTS_MAX - ROTS_MIN);

            if (random.nextFloat() < 0.5f) velocity = new Vector2f(-velocity.x, velocity.y);
            if (random.nextFloat() < 0.5f) velocity = new Vector2f(velocity.x, -velocity.y);
            if (random.nextFloat() < 0.5f) rotSpeed = -rotSpeed;
        }
    }
}
