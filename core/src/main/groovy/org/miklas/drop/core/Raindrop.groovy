package org.miklas.drop.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static org.miklas.drop.core.Conf.X_RES
import static org.miklas.drop.core.Conf.Y_RES

@CompileStatic
class Raindrop {

    static Texture IMG = [Gdx.files.internal("assets/drop.png")]

    private Rectangle position

    Raindrop() {
        position = [MathUtils.random(0, X_RES - 64), Y_RES, 64, 64]
    }

    void render(SpriteBatch batch) {
        batch.draw IMG, position.x, position.y
    }

    boolean move() {
        position.y -= 200 * Gdx.graphics.deltaTime
        position.y + 64 > 0
    }

    boolean overlaps(Rectangle rectangle) {
        position.overlaps(rectangle)
    }

    static Disposable disposable() {
        [dispose: { IMG.dispose() }] as Disposable
    }

}
