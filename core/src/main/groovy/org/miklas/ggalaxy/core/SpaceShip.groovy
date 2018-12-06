package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.cannon.Cannon
import org.miklas.ggalaxy.core.common.*
import org.miklas.ggalaxy.core.event.EventBus
import org.miklas.ggalaxy.core.event.EventType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static org.miklas.ggalaxy.core.common.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.common.Conf.SCR_WIDTH

@PackageScope
@Component
class SpaceShip extends Actor implements Asset {

    @Autowired
    private Cannon mainCannon

    final Rectangle position
    final AssetType type = AssetType.SPACE_SHIP
    private Animation<Sprite> animation
    private final AssetName assetNormal = AssetName.SHIP_INTERCEPTOR_BLUE//TODO get from config
    private final AssetName assetBoost = AssetName.SHIP_INTERCEPTOR_RED
    private float animationStartTime = 0.0f
    private Speed speed
    private long lastFireMs = 0
    def c_an
    def c_sh

    SpaceShip() {
        c_an = Conf.animation assetNormal
        c_sh = Conf.spaceShip()

        position = [SCR_WIDTH / 2f - c_an.spriteWith / 2f as float, 20, c_an.spriteWith, c_an.spriteHeight]
        animation = AnimationFactory.create(assetNormal, Animation.PlayMode.LOOP, type)


        EventBus.register(EventType.BOOST_START) {
            animation = AnimationFactory.create(assetBoost, Animation.PlayMode.LOOP, type)
        }

        EventBus.register(EventType.BOOST_END) {
            animation = AnimationFactory.create(assetNormal, Animation.PlayMode.LOOP, type)
        }
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        speed = Keyboard.BOOST.pressed() ? Speed.BOOST : Speed.NORMAL

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
        Keyboard.LEFT.onMove { position.x -= it }
        Keyboard.RIGHT.onMove { position.x += it }
        Keyboard.UP.onMove { position.y += it }
        Keyboard.DOWN.onMove { position.y -= it }
        Keyboard.FIRE.onFire {
            if (TimeUtils.millis() - lastFireMs > c_sh.cannon.main.delayMs) {
                mainCannon.fire position.x + c_an.cannon.main.position.x as int,
                        position.y + c_an.cannon.main.position.y as int,
                        0,
                        c_sh.cannon.main.moveSpeed
                lastFireMs = TimeUtils.millis()
            }
        }
    }

    @Override
    boolean checkCollision(Asset other) {
        position.overlaps(other.position)
    }

    @Override
    void hit(Asset other) {

    }

    private enum Speed {
        NORMAL,
        BOOST
    }
}
