package org.miklas.ggalaxy.core.cannon

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.CollisionDetection

class Shots extends Actor implements MainCannon {

    private final List<Shot> shots = []
    private final CollisionDetection collisionDetection

    Shots(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection
    }

    @Override
    void fire(int x, int y, int angle, int moveSpeed) {
        Shot shot = shots.find { it.mode == Shot.Mode.INACTIVE }
        if (shot == null) {
            shot = new Shot(AssetName.SHOT_RED)
            shots << shot
            collisionDetection << shot
        }
        shot.fire x, y, angle, moveSpeed
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        shots.forEach {
            it.draw batch
        }
    }
}