package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.Asset
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.AssetType

@PackageScope
class Asteroid extends Enemy {

    final AssetType type = AssetType.ASTEROID

    Asteroid(AssetName assetName) {
        super(assetName)
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        if (mode == Mode.INACTIVE) {
            return
        }

        // reached bottom of the screen ?
        if (position.y + c_an.spriteHeight < 0) {
            mode = Mode.INACTIVE
            return
        }

        Sprite sprite = animation.getKeyFrame animationStateTime
        sprite.setPosition position.x, position.y
        sprite.draw batch
        animationStateTime += Gdx.graphics.getDeltaTime()
        position.y -= c_ea.modeSpeed * Gdx.graphics.deltaTime
    }

    @Override
    boolean checkCollision(Asset other) {
        mode == Mode.ACTIVE && other.type != AssetType.ASTEROID && position.overlaps(other.position)
    }

}
