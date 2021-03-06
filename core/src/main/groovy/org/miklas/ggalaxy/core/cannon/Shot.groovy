package org.miklas.ggalaxy.core.cannon

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.*
import org.miklas.ggalaxy.core.event.EventBus
import org.miklas.ggalaxy.core.event.EventType

@PackageScope
class Shot implements Asset, Cannon {

    Mode mode = Mode.ACTIVE
    PointG position = []
    final AssetType type = AssetType.SHOT
    int moveSpeed

    Sprite sprite
    int angle

    Shot(AssetName asset) {
        sprite = SpriteFactory.create asset
        EventBus.event EventType.OBSTACLE_CREATED, this
    }

    @Override
    boolean checkCollision(Asset other) {
        other.type != AssetType.SPACE_SHIP && mode == Mode.ACTIVE && position.overlaps(other.position)
    }

    @Override
    void hit(Asset other) {
        if (other.type == AssetType.SPACE_SHIP) {
            return
        }

        mode = Mode.INACTIVE
    }

    @Override
    void fire(int x, int y, int angle, int moveSpeed) {
        mode = Mode.ACTIVE
        this.position.x = x
        this.position.y = y
        this.angle = angle
        this.moveSpeed = moveSpeed
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        if (mode == Mode.INACTIVE) {
            return
        }

        sprite.setPosition position.x, position.y
        sprite.draw batch
        move()
    }

    void move() {
        // reached top of the screen ?
        if (position.x < 0 || position.x > Conf.SCR_WIDTH || position.y < 0 || position.y > Conf.SCR_HEIGHT) {
            mode = Mode.INACTIVE
            return
        }

        double radian = Math.toRadians angle
        int offset = moveSpeed * Gdx.graphics.deltaTime

        position.x = position.x + offset * Math.sin(radian)
        position.y = position.y + offset * Math.cos(radian)
    }

    enum Mode {
        INACTIVE,
        ACTIVE
    }
}
