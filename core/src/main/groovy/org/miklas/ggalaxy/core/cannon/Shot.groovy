package org.miklas.ggalaxy.core.cannon

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle
import org.miklas.ggalaxy.core.common.SpriteFactory

class Shot implements Obstacle {

    Mode mode = Mode.ACTIVE
    Rectangle position = []
    final Type type = Type.SHOT


    private SpriteFactory.Asset asset
    private Sprite sprite

    Shot(SpriteFactory.Asset asset) {
        this.asset = asset
        sprite = SpriteFactory.create asset
    }

    @Override
    boolean checkCollision(Obstacle other) {
        other.type != Type.SPACE_SHIP && mode == Mode.ACTIVE && position.overlaps(other.position)
    }

    @Override
    void hit(Obstacle other) {
        if (other.type == Type.SPACE_SHIP) {
            return
        }

        mode = Mode.INACTIVE
    }

    def fire(int x, int y) {
        mode = Mode.ACTIVE
        this.position.x = x
        this.position.y = y
    }

    void draw(Batch batch) {
        if (mode == Mode.INACTIVE) {
            return
        }

        // reached top of the screen ?
        if (position.y + asset.spriteHeight > Conf.SCR_HEIGHT) {
            mode = Mode.INACTIVE
            return
        }

        sprite.setPosition position.x, position.y
        sprite.draw batch
        position.y += Conf.ins.shot.moveSpeed * Gdx.graphics.deltaTime
    }

    enum Mode {
        INACTIVE,
        ACTIVE
    }
}
