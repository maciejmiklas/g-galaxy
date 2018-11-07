package org.miklas.ggalaxy.core.cannon

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle
import org.miklas.ggalaxy.core.common.ObstacleType
import org.miklas.ggalaxy.core.common.SpriteFactory

class Shot implements Obstacle {

    Mode mode = Mode.ACTIVE
    Rectangle position = []
    final ObstacleType type = ObstacleType.SHOT


    private SpriteFactory.Asset asset
    private Sprite sprite
    private int angle

    Shot(SpriteFactory.Asset asset) {
        this.asset = asset
        sprite = SpriteFactory.create asset
    }

    @Override
    boolean checkCollision(Obstacle other) {
        other.type != ObstacleType.SPACE_SHIP && mode == Mode.ACTIVE && position.overlaps(other.position)
    }

    @Override
    void hit(Obstacle other) {
        if (other.type == ObstacleType.SPACE_SHIP) {
            return
        }

        mode = Mode.INACTIVE
    }

    void fire(int x, int y, int angle) {
        mode = Mode.ACTIVE
        this.position.x = x
        this.position.y = y
        this.angle = angle
    }

    void draw(Batch batch) {
        if (mode == Mode.INACTIVE) {
            return
        }

        sprite.setPosition position.x, position.y
        sprite.draw batch

        move()
    }

    private void move() {
        // reached top of the screen ?
        if (position.y + asset.spriteHeight > Conf.SCR_HEIGHT || position.y + asset.spriteHeight > Conf.SCR_HEIGHT) {
            mode = Mode.INACTIVE
            println "$position.x = $position.y"
            return
        }

        double radian = angle * Math.PI / 180
        int offset = Conf.ins.shot.moveSpeed * Gdx.graphics.deltaTime

        position.x = position.x + offset * Math.sin(radian)
        position.y = position.y + offset * Math.cos(radian)
    }

    enum Mode {
        INACTIVE,
        ACTIVE
    }
}
