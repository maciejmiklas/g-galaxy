package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

@CompileStatic
class Asteroid extends Actor {

    private Animation<Sprite> animation
    private float animationStartTime = 0.0f // TODO reset
    private Rectangle position

    Asteroid(AnimationFactory.Asset asset) {
        position = [MathUtils.random(0, Conf.SCR_WIDTH - asset.spriteWith), Conf.SCR_HEIGHT, asset.spriteWith, asset.spriteHeight]
        animation = AnimationFactory.createAnimation(asset)
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        animationStartTime += Gdx.graphics.getDeltaTime()
        Sprite sprite = animation.getKeyFrame animationStartTime
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
        [dispose: { /*IMG.dispose() TODO*/ }] as Disposable
    }

}
