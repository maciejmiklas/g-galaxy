package org.miklas.ggalaxy.core.cannon

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle
import org.miklas.ggalaxy.core.common.AssetType
import org.miklas.ggalaxy.core.common.SpriteFactory

class Shot implements Obstacle, MainCannon {

    Mode mode = Mode.ACTIVE
    Rectangle position = []
    final AssetType type = AssetType.SHOT
    private int moveSpeed

    private Sprite sprite
    private int angle

    Shot(AssetName asset) {
        sprite = SpriteFactory.create asset
    }

    @Override
    boolean checkCollision(Obstacle other) {
        other.type != AssetType.SPACE_SHIP && mode == Mode.ACTIVE && position.overlaps(other.position)
    }

    @Override
    void hit(Obstacle other) {
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
        if (position.x < 0 || position.x > Conf.SCR_WIDTH || position.y < 0 || position.y > Conf.SCR_HEIGHT) {
            mode = Mode.INACTIVE
            return
        }

        double radian = angle * Math.PI / 180
        int offset = moveSpeed * Gdx.graphics.deltaTime

        position.x = position.x + offset * Math.sin(radian)
        position.y = position.y + offset * Math.cos(radian)
    }

    enum Mode {
        INACTIVE,
        ACTIVE
    }
}
