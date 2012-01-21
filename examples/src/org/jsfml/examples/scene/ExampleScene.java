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
    private final VertexArray background = new VertexArray(PrimitiveType.QUADS, 4);
    private final Texture jsfmlLogoTexture = new Texture();
    private final Sprite jsfmlLogo = new Sprite();
    private final RenderStates jsfmlLogoStates = new RenderStates();

    private final Font terminatorFont = new Font();
    private final Text instructionsText =
            new Text("Left mouse button and move to rotate logo\n" +
                    "Right mouse button and move to wave logo\n" +
                    "Mouse wheel to scale logo\n\n" +
                    "Press A to add an entity\n" +
                    "Press D to delete an entity\n" +
                    "Press Escape to quit");
    private final Text instructionsTextShadow = new Text(instructionsText.getString());

    private final Text infoText = new Text();
    private final Text infoTextShadow = new Text();

    private final Text fpsText = new Text();
    private final Text fpsTextShadow = new Text();

    private final RectangleShape textBackground = new RectangleShape();

    private Text noShadersText = null;

    //screen size
    private int screenWidth, screenHeight;

    //FPS counter
    private int fpsFrames = 0;
    private float fpsTime = 1.0f;

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
        screenWidth = target.getWidth();
        screenHeight = target.getHeight();

        //Create background
        background.append(new Vertex(new Vector2f(0, 0), Color.RED));
        background.append(new Vertex(new Vector2f(target.getWidth(), 0), Color.BLUE));
        background.append(new Vertex(new Vector2f(target.getWidth(), target.getHeight()), Color.GREEN));
        background.append(new Vertex(new Vector2f(0, target.getHeight()), Color.YELLOW));

        //Load logo
        jsfmlLogoTexture.loadFromStream(getClass().getResourceAsStream("/resources/jsfml-y_ex.png"));
        jsfmlLogoTexture.setSmooth(true);

        //Setup logo sprite
        jsfmlLogo.setTexture(jsfmlLogoTexture);
        jsfmlLogo.setOrigin(jsfmlLogoTexture.getWidth() / 2, jsfmlLogoTexture.getHeight() / 2);
        jsfmlLogo.setPosition(target.getWidth() / 2, target.getHeight() / 2);

        //Load font
        terminatorFont.loadFromStream(
                getClass().getResourceAsStream("/resources/terminator real nfi.ttf"));

        //Setup istructions text
        instructionsText.setFont(terminatorFont);
        instructionsText.setColor(new Color(255, 255, 255, 192));
        instructionsText.setCharacterSize(14);
        instructionsText.setPosition(5, 5);
        instructionsText.setScale(0.9f, 1.0f);

        instructionsTextShadow.setFont(terminatorFont);
        instructionsTextShadow.setColor(new Color(0, 0, 0, 128));
        instructionsTextShadow.setCharacterSize(14);
        instructionsTextShadow.setPosition(7, 7);
        instructionsTextShadow.setScale(0.9f, 1.0f);

        //Setup entity count text
        updateInfoText();

        infoText.setFont(terminatorFont);
        infoText.setColor(Color.YELLOW);
        infoText.setCharacterSize(24);

        infoTextShadow.setFont(terminatorFont);
        infoTextShadow.setColor(new Color(0, 0, 0, 96));
        infoTextShadow.setCharacterSize(24);

        FloatRect infoTextBounds = infoText.getGlobalBounds();
        infoText.setPosition(5, target.getHeight() - infoTextBounds.height - 10);
        infoTextShadow.setPosition(7, target.getHeight() - infoTextBounds.height - 8);

        //Setup FPS text
        updateFpsText(99);

        fpsText.setFont(terminatorFont);
        fpsText.setColor(Color.GREEN);
        fpsText.setCharacterSize(24);

        fpsTextShadow.setFont(terminatorFont);
        fpsTextShadow.setColor(new Color(0, 0, 0, 96));
        fpsTextShadow.setCharacterSize(24);

        FloatRect fpsTextBounds = fpsText.getGlobalBounds();
        fpsText.setPosition(5, target.getHeight() - infoTextBounds.height - fpsTextBounds.height - 15);
        fpsTextShadow.setPosition(7, target.getHeight() - infoTextBounds.height - fpsTextBounds.height - 13);

        //Setup text background
        textBackground.setOutlineColor(Color.BLACK);
        textBackground.setOutlineThickness(3.0f);
        textBackground.setFillColor(new Color(0, 0, 0, 128));
        textBackground.setSize(new Vector2f(
                target.getWidth(),
                infoTextBounds.height + fpsTextBounds.height + 18.0f));
        textBackground.setOrigin(0, textBackground.getSize().y);
        textBackground.setPosition(0, target.getHeight());

        //Load and setup wave shader
        if (Shader.isAvailable()) {
            waveXShader = new Shader();
            waveXShader.loadFromStream(
                    getClass().getResourceAsStream("/resources/wave-x.frag"), Shader.Type.FRAGMENT);

            waveXShader.setParameter("texture", Shader.CURRENT_TEXTURE);
            waveXShader.setParameter("amplitude", waveXAmp);
            waveXShader.setParameter("wavelen", waveXLen);

            jsfmlLogoStates.setShader(waveXShader);
        } else {
            noShadersText = new Text(
                    "Your graphics card\ndoes not support shaders,\nthe wave effect won't work!");

            noShadersText.setFont(terminatorFont);
            noShadersText.setColor(Color.WHITE);
            noShadersText.setCharacterSize(14);
            noShadersText.setScale(0.9f, 1.0f);
            noShadersText.setStyle(Text.ITALIC);
            FloatRect b = noShadersText.getGlobalBounds();
            noShadersText.setPosition(
                    target.getWidth() - b.width - 5,
                    target.getHeight() - b.height - 5);
        }
    }

    private void updateInfoText() {
        infoText.setString("Entities: " + entities.size());
        infoTextShadow.setString(infoText.getString());
    }

    private void updateFpsText(int fps) {
        fpsText.setString("FPS: " + fps);
        fpsTextShadow.setString(fpsText.getString());
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.getType()) {
            case CLOSED:
                //The close button has been pressed
                quit = true;
                break;

            case MOUSE_WHEEL_MOVED:
                //The mouse wheel was moved
                int delta = event.asMouseWheelEvent().getDelta();
                if (delta > 0)
                    jsfmlLogo.scale(1.1f, 1.1f);
                else
                    jsfmlLogo.scale(0.9f, 0.9f);

                break;

            case MOUSE_BUTTON_PRESSED:
                //A mouse button was pressed
                mouseDown = true;
                mouseButton = event.asMouseButtonEvent().getButton();
                mouseDownPos = event.asMouseEvent().getPosition();
                mouseLogoRotationStart = jsfmlLogo.getRotation();
                mouseWaveXAmpStart = waveXAmp;
                mouseWaveXOffsetStart = waveXOffset;
                break;

            case MOUSE_BUTTON_RELEASED:
                //A mouse button was released
                if (event.asMouseButtonEvent().getButton() == mouseButton) {
                    mouseDown = false;
                }
                break;

            case MOUSE_MOVED:
                //The mouse has been moved
                if (mouseDown) {
                    switch (mouseButton) {
                        case LEFT:
                            //rotate logo
                            Vector2i pos = event.asMouseEvent().getPosition();
                            int deg = pos.x - mouseDownPos.x;

                            jsfmlLogo.setRotation(mouseLogoRotationStart + deg);
                            break;

                        case RIGHT:
                            //change amplitude and offset
                            if (waveXShader != null) {
                                int amp = event.asMouseEvent().getY() - mouseDownPos.y;
                                waveXAmp = mouseWaveXAmpStart + 0.001f * amp;
                                waveXShader.setParameter("amplitude", waveXAmp);

                                int off = event.asMouseEvent().getX() - mouseDownPos.x;
                                waveXOffset = mouseWaveXOffsetStart + 0.001f * off;
                                waveXShader.setParameter("offset", waveXOffset);
                            }

                            break;
                    }
                }
                break;

            case KEY_PRESSED:
                switch (event.asKeyEvent().getKeyCode()) {
                    case A:
                        Entity e = new Entity();
                        e.shape.setPosition(screenWidth / 2, screenHeight / 2);
                        entities.add(e);
                        updateInfoText();
                        break;

                    case D:
                        if (entities.size() > 0) {
                            entities.removeFirst();
                            updateInfoText();
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
        fpsTime -= dt;
        if (fpsTime <= 0.0f) {
            updateFpsText(fpsFrames);
            fpsTime += 1.0f;
            fpsFrames = 0;
        }

        //Update entities
        Vector2f v = new Vector2f();
        FloatRect bounds;

        for (Entity e : entities) {
            //Move the entity's shape
            v.x = dt * e.velocity.x;
            v.y = dt * e.velocity.y;

            e.shape.move(v);

            //Rotate the entity's shape
            e.shape.rotate(dt * e.rotSpeed);

            //Make sure the entity stays on the screen
            bounds = e.shape.getGlobalBounds();

            if ((v.x < 0 && bounds.left <= 0) ||
                    (v.x > 0 && bounds.left + bounds.width >= screenWidth)) {

                e.velocity.x = -e.velocity.x;
            }

            if ((v.y < 0 && bounds.top <= 0) ||
                    (v.y > 0 && bounds.top + bounds.height >= screenHeight)) {

                e.velocity.y = -e.velocity.y;
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

            Color color = colors[random.nextInt(colors.length)];
            color.setA(96);
            shape.setFillColor(color);

            velocity = new Vector2f(
                    VEL_MIN + random.nextFloat() * (VEL_MAX - VEL_MIN),
                    VEL_MIN + random.nextFloat() * (VEL_MAX - VEL_MIN));

            rotSpeed = ROTS_MIN + random.nextFloat() * (ROTS_MAX - ROTS_MIN);

            if (random.nextFloat() < 0.5f) velocity.x = -velocity.x;
            if (random.nextFloat() < 0.5f) velocity.y = -velocity.y;
            if (random.nextFloat() < 0.5f) rotSpeed = -rotSpeed;
        }
    }
}
