package org.jsfml.examples.scene;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

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
            new Text("Left mouse button and move left/right to rotate\n" +
                    "Right mouse button and move up/down to WaVe\n" +
                    "Mouse wheel to scale");

    private final Text instructionsTextShadow = new Text(instructionsText.getString());

    //wave shader
    private Shader waveXShader = null;
    private float waveXOffset = 0.0f;
    private float waveXLen = 0.25f;
    private float waveXAmp = 0.025f;

    //mouse features state
    private boolean mouseDown = false;
    private Vector2i mouseDownPos;
    private Mouse.Button mouseButton;
    private float mouseLogoRotationStart;
    private float mouseWaveXAmpStart;

    //quit flag
    private boolean quit = false;

    @Override
    public void initialize(RenderTarget target) throws Exception {
        background.append(new Vertex(new Vector2f(0, 0), Color.RED));
        background.append(new Vertex(new Vector2f(target.getWidth(), 0), Color.BLUE));
        background.append(new Vertex(new Vector2f(target.getWidth(), target.getHeight()), Color.GREEN));
        background.append(new Vertex(new Vector2f(0, target.getHeight()), Color.YELLOW));

        jsfmlLogoTexture.loadFromStream(getClass().getResourceAsStream("/resources/jsfml-y_ex.png"));
        jsfmlLogoTexture.setSmooth(true);

        jsfmlLogo.setTexture(jsfmlLogoTexture);
        jsfmlLogo.setOrigin(jsfmlLogoTexture.getWidth() / 2, jsfmlLogoTexture.getHeight() / 2);
        jsfmlLogo.setPosition(target.getWidth() / 2, target.getHeight() / 2);

        if (Shader.isAvailable()) {
            waveXShader = new Shader();
            waveXShader.loadFromStream(
                    getClass().getResourceAsStream("/resources/wave-x.frag"), Shader.Type.FRAGMENT);

            waveXShader.setParameter("texture", Shader.CURRENT_TEXTURE);
            waveXShader.setParameter("amplitude", waveXAmp);
            waveXShader.setParameter("wavelen", waveXLen);

            jsfmlLogoStates.setShader(waveXShader);
        }

        terminatorFont.loadFromStream(
                getClass().getResourceAsStream("/resources/terminator real nfi.ttf"));

        instructionsText.setFont(terminatorFont);
        instructionsText.setColor(new Color(255, 255, 255, 160));
        instructionsText.setCharacterSize(14);
        instructionsText.setPosition(5, 5);
        instructionsText.setScale(0.9f, 1.0f);

        instructionsTextShadow.setFont(terminatorFont);
        instructionsTextShadow.setColor(new Color(0, 0, 0, 128));
        instructionsTextShadow.setCharacterSize(14);
        instructionsTextShadow.setPosition(7, 7);
        instructionsTextShadow.setScale(0.9f, 1.0f);
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
                            int deg = event.asMouseEvent().getX() - mouseDownPos.x;
                            jsfmlLogo.setRotation(mouseLogoRotationStart + deg);
                            break;

                        case RIGHT:


                            //change amplitude
                            if (waveXShader != null) {
                                int amp = event.asMouseEvent().getY() - mouseDownPos.y;
                                waveXAmp = mouseWaveXAmpStart + 0.001f * amp;
                                waveXShader.setParameter("amplitude", waveXAmp);
                            }

                            break;
                    }
                }

                break;
        }
    }

    @Override
    public void update(float dt) {
        if (waveXShader != null) {
            //Update the wave effect
            waveXOffset += 0.1f * dt;

            if (waveXOffset > waveXLen)
                waveXOffset -= waveXLen;

            waveXShader.setParameter("offset", waveXOffset);
        }
    }

    @Override
    public void render(RenderTarget target) {
        target.draw(background);
        target.draw(jsfmlLogo, jsfmlLogoStates);

        target.draw(instructionsTextShadow);
        target.draw(instructionsText);
    }

    @Override
    public boolean isDone() {
        return quit;
    }
}
