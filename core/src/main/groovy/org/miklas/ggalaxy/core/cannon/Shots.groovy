package org.miklas.ggalaxy.core.cannon

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.TimeUtils
import org.miklas.ggalaxy.core.common.CollisionDetection
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.SpriteFactory

class Shots extends Actor implements MainCannon {

    private long lastFireMs = 0
    private final List<Shot> shots = []
    private final CollisionDetection collisionDetection

    Shots(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection
    }

    @Override
    void fire(int x, int y, int angle) {
        if (TimeUtils.millis() - lastFireMs < Conf.ins.shot.delayMs) {
            return
        }
        Shot shot = shots.find { it.mode == Shot.Mode.INACTIVE }
        if (shot == null) {
            shot = new Shot(SpriteFactory.Asset.SHOT_RED)
            shots << shot
            collisionDetection << shot
        }
        shot.fire x, y, angle
        lastFireMs = TimeUtils.millis()
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        shots.forEach {
            it.draw batch
        }
    }
}