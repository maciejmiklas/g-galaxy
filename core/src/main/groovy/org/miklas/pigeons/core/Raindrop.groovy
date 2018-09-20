package org.miklas.pigeons.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static Conf.X_RES
import static Conf.Y_RES

@CompileStatic
class Raindrop implements Renderable {

    static Texture IMG = [Gdx.files.internal("assets/drop.png")]
    private final int WIDTH = 64
    private final int HEIGHT = 64

    private Rectangle position
    private Sprite sprite

    Raindrop() {
        position = [MathUtils.random(0, X_RES - 64), Y_RES, 64, 64]
        sprite = [IMG, WIDTH, HEIGHT]
    }

    @Override
    void render(Batch batch) {
        sprite.setPosition position.x, position.y
        sprite.draw batch
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
