package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

class MainShip extends Actor implements Obstacle {

    final Rectangle position
    final Type type = Type.MAIN_SHIP
    private final Animation<Sprite> animation
    private final AnimationFactory.Asset assetNormal
    private final AnimationFactory.Asset assetBoost
    private float animationStartTime = 0.0f
    private Speed speed
    private MainCannon mainCannon

    MainShip(AnimationFactory.Asset assetNormal, AnimationFactory.Asset assetBoost, MainCannon mainCannon) {
        this.assetNormal = assetNormal
        this.assetBoost = assetBoost
        this.mainCannon = mainCannon
        position = [SCR_WIDTH / 2f - assetNormal.spriteWith / 2f as float, 20, assetNormal.spriteWith, assetNormal.spriteHeight]
        animation = AnimationFactory.create(assetNormal, Animation.PlayMode.LOOP)
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
        // TODO do we have to register closures on every invocation, would it be more efficient to register it once ?
        Key.LEFT.onMove { position.x -= it }
        Key.RIGHT.onMove { position.x += it }
        Key.UP.onMove { position.y += it }
        Key.DOWN.onMove { position.y -= it }
        Key.FIRE.onFire {
            mainCannon.fire position.x + assetNormal.conf.cannon.main.x as int, position.y + assetNormal.conf.cannon.main.y as int
        }
    }

    @Override
    boolean checkCollision(Obstacle other) {
        position.overlaps(other.position)
    }

    @Override
    void hit(Obstacle other) {

    }

    private enum Speed {
        NORMAL,
        BOOST
    }
}
