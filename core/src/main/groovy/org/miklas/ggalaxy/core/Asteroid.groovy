package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

@CompileStatic
class Raindrop extends Actor {

    static Texture IMG = [Gdx.files.internal("assets/packs/Spaceship_art_pack_larger/Blue/Spacemines/1.png")]
    private final int WIDTH = 64
    private final int HEIGHT = 64

    private Rectangle position
    private Sprite sprite

    Raindrop() {
        position = [MathUtils.random(0, Conf.SCR_WIDTH - 64), Conf.SCR_HEIGHT, 64, 64]
        sprite = [IMG, 256, 256]
        sprite.setSize(WIDTH, HEIGHT)
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
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
