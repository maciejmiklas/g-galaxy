package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils
import org.miklas.ggalaxy.core.cannon.MainCannon
import org.miklas.ggalaxy.core.common.AnimationFactory
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle
import org.miklas.ggalaxy.core.common.AssetType

import static org.miklas.ggalaxy.core.common.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.common.Conf.SCR_WIDTH

class SpaceShip extends Actor implements Obstacle {

    final Rectangle position
    final AssetType type = AssetType.SPACE_SHIP
    private final Animation<Sprite> animation
    private final AssetName assetNormal
    private final AssetName assetBoost
    private float animationStartTime = 0.0f
    private Speed speed
    private MainCannon mainCannon
    private long lastFireMs = 0
    def c_an
    def c_cm

    SpaceShip(AssetName assetNormal, AssetName assetBoost, MainCannon mainCannon) {
        this.assetNormal = assetNormal
        this.assetBoost = assetBoost
        this.mainCannon = mainCannon
        c_an = Conf.animation assetNormal
        c_cm = Conf.cannonMain AssetType.SPACE_SHIP
        position = [SCR_WIDTH / 2f - c_an.spriteWith / 2f as float, 20, c_an.spriteWith, c_an.spriteHeight]
        animation = AnimationFactory.create(assetNormal, Animation.PlayMode.LOOP, type)
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

        if (position.x > SCR_WIDTH - c_an.spriteWith) {
            position.x = SCR_WIDTH - c_an.spriteWith as float
        }

        if (position.y > SCR_HEIGHT - c_an.spriteHeight) {
            position.y = SCR_HEIGHT - c_an.spriteHeight as float
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
            if (TimeUtils.millis() - lastFireMs > c_cm.delayMs) {
                mainCannon.fire position.x + c_an.cannon.main.position.x as int,
                                position.y + c_an.cannon.main.position.y as int,
                            0,
                                c_cm.moveSpeed
                lastFireMs = TimeUtils.millis()
            }
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
