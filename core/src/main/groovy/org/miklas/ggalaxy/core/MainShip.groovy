package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

@CompileStatic
class MainShip extends Actor implements Disposable {

    final Rectangle position
    private final  Animation<Sprite> animation
    private final AnimationFactory.Asset asset

    private float animationStartTime = 0.0f // TODO reset

    MainShip(AnimationFactory.Asset asset) {
        this.asset = asset
        position = [SCR_WIDTH / 2f - asset.spriteWith / 2f as float, 20, asset.spriteWith, asset.spriteHeight]
        animation =  AnimationFactory.createAnimation(asset)
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        // make sure the mainShip stays within the screen bounds
        if (position.x < 0) {
            position.x = 0
        }

        if (position.y < 0) {
            position.y = 0
        }

        if (position.x > SCR_WIDTH - asset.spriteWith) {
            position.x = SCR_WIDTH - asset.spriteWith as float
        }

        if (position.y > SCR_HEIGHT - asset.spriteHeight) {
            position.y = SCR_HEIGHT - asset.spriteHeight as float
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
    void dispose() {
        // TODO dispose sprites from animation
    }
}
