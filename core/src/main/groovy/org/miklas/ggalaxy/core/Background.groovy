package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
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
        for (int lid = 0; lid < layers.size(); lid++) {
            int srcY = (scrollY + (lid + 1) * LAYER_SPEED_DIFFERENCE * scrollY) / 2 as int
            int srcX = (scrollX + lid * LAYER_SPEED_DIFFERENCE * scrollX) / 10 as int
            Texture layer = layers.get lid

            batch.draw layer, 0, 0, SCR_WIDTH, SCR_HEIGHT, srcX, srcY, layer.width, layer.height, false, true
        }
    }

    private void processUserInput() {
        int boost = Gdx.input.isKeyPressed(Input.Keys.SPACE) ? 6 : 0


        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            speedY = 6 + boost

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            speedY = 2 + boost

        } else {
            speedY = 4 + boost
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            speedX = 2 + boost

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            speedX = -2 - boost

        } else {
            speedX = 0 + boost
        }
    }
}