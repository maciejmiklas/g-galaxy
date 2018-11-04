package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

/**
 * Parallax Background.
 */
class Background extends Actor implements Disposable {

    private final Texture bgPink = [Gdx.files.internal("assets/background/space/Nebula Aqua-Pink.png")]
    private final Texture bgBlue = [Gdx.files.internal("assets/background/space/Nebula Blue.png")]
    private final Texture bgRead = [Gdx.files.internal("assets/background/space/Nebula Red.png")]

    private final Texture stS1 = [Gdx.files.internal("assets/background/space/Stars Small_1.png")]
    private final Texture stS2 = [Gdx.files.internal("assets/background/space/Stars Small_2.png")]

    private final Texture stB1 = [Gdx.files.internal("assets/background/space/Stars-Big_1_1_PC.png")]
    private final Texture stB2 = [Gdx.files.internal("assets/background/space/Stars-Big_1_1_PC.png")]

    private final List<Texture> layers = [bgBlue, stS1, stS2]

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
            int srcY = (scrollY + (idx + 1) * Conf.ins.background.layerSpeedDiference * scrollY) / 2 as int
            int srcX = (scrollX + idx * Conf.ins.background.layerSpeedDiference * scrollX) / 10 as int
            batch.draw layer, 0, 0, SCR_WIDTH, SCR_HEIGHT, srcX, srcY, layer.width, layer.height, false, true
        }
    }

    private void processUserInput() {
        boolean vertical = Key.vertical() { code ->
            def cv = Conf.ins.background.speed."$code"
            speedY = Key.BOOST.pressed() ? cv.boost : cv.normal
        }
        if (!vertical) {
            speedY = Conf.ins.background.speed.NONE.y
        }

        boolean horizontal = Key.horizontal { code ->
            def cv = Conf.ins.background.speed."$code"
            speedX = Key.BOOST.pressed() ? cv.boost : cv.normal
        }
        if (!horizontal) {
            speedX = Conf.ins.background.speed.NONE.x
        }

    }
}