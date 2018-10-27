package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

/**
 * Parallax Background.
 */
@CompileStatic
class Background extends Actor implements Disposable {


    private Texture bgPink = [Gdx.files.internal("assets/background/space/Nebula Aqua-Pink.png")]
    private Texture bgBlue = [Gdx.files.internal("assets/background/space/Nebula Blue.png")]
    private Texture bgRead = [Gdx.files.internal("assets/background/space/Nebula Red.png")]

    private Texture stS1 = [Gdx.files.internal("assets/background/space/Stars Small_1.png")]
    private Texture stS2 = [Gdx.files.internal("assets/background/space/Stars Small_2.png")]

    private Texture stB1 = [Gdx.files.internal("assets/background/space/Stars-Big_1_1_PC.png")]
    private Texture stB2 = [Gdx.files.internal("assets/background/space/Stars-Big_1_1_PC.png")]

    private List<Texture> layers = [bgBlue, stS1, stS2]

    private final int LAYER_SPEED_DIFFERENCE = 2

    private int speedY = 4
    private int scrollY = 0

    private int speedX = 0
    private int scrollX = 0

    @Override
    void dispose() {
    }

    Background() {
        layers*.setWrap Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        processUserInput()

        scrollY += speedY
        scrollX += speedX

        layers.eachWithIndex { layer, idx ->
            int srcY = (scrollY + (idx + 1) * LAYER_SPEED_DIFFERENCE * scrollY) / 2 as int
            int srcX = (scrollX + idx * LAYER_SPEED_DIFFERENCE * scrollX) / 10 as int
            batch.draw layer, 0, 0, SCR_WIDTH, SCR_HEIGHT, srcX, srcY, layer.width, layer.height, false, true
        }
    }

    private void processUserInput() {

        if (Key.pressed(Key.Code.UP, Key.Code.BOOST)) {
            speedY = 10

        } else if (Key.pressed(Key.Code.UP)) {
            speedY = 6

        } else if (Key.pressed(Key.Code.DOWN, Key.Code.BOOST)) {
            speedY = 1

        } else if (Key.pressed(Key.Code.DOWN)) {
            speedY = 2

        } else {
            speedY = 4
        }

        if (Key.pressed(Key.Code.LEFT, Key.Code.BOOST)) {
            speedX = 6

        } else if (Key.pressed(Key.Code.LEFT)) {
            speedX = 2

        } else if (Key.pressed(Key.Code.RIGHT, Key.Code.BOOST)) {
            speedX = -6

        } else if (Key.pressed(Key.Code.RIGHT)) {
            speedX = -2

        } else {
            speedX = 0
        }
    }
}