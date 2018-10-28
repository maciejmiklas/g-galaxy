package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import groovy.transform.CompileStatic

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

@CompileStatic
class MainShip extends Actor implements Obstacle {

    final Rectangle position
    final Type type = Type.SHIP
    private final Animation<Sprite> animation
    private final AnimationFactory.Asset assetNormal
    private final AnimationFactory.Asset assetBoost
    private float animationStartTime = 0.0f
    private Speed speed

    MainShip(AnimationFactory.Asset assetNormal, AnimationFactory.Asset assetBoost) {
        this.assetNormal = assetNormal
        this.assetBoost = assetBoost
        position = [SCR_WIDTH / 2f - assetNormal.spriteWith / 2f as float, 20, assetNormal.spriteWith, assetNormal.spriteHeight]
        animation = AnimationFactory.createAnimation(assetNormal, Animation.PlayMode.LOOP)
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        speed = Key.BOOST.pressed() ? Speed.BOOST : Speed.NORMAL

        // make sure the mainShip stays within the screen bounds
        if (position.x < 0) {
            position.x = 0
        }

        if (position.y < 0) {
            position.y = 0
        }

        if (position.x > SCR_WIDTH - assetNormal.spriteWith) {
            position.x = SCR_WIDTH - assetNormal.spriteWith as float
        }

        if (position.y > SCR_HEIGHT - assetNormal.spriteHeight) {
            position.y = SCR_HEIGHT - assetNormal.spriteHeight as float
        }

        processUserInput()

        animationStartTime += Gdx.graphics.getDeltaTime()
        Sprite sprite = animation.getKeyFrame animationStartTime
        sprite.setPosition position.x, position.y
        sprite.draw batch
    }

    private void processUserInput() {
        Key.LEFT.move { position.x -= it }
        Key.RIGHT.move { position.x += it }
        Key.UP.move { position.y += it }
        Key.DOWN.move { position.y -= it }
    }

    @Override
    boolean collision(Rectangle other) {
        return false
    }

    @Override
    void hit(Type other) {

    }

    private enum Speed {
        NORMAL,
        BOOST
    }
}
