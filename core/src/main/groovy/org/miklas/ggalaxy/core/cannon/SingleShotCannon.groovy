package org.miklas.ggalaxy.core.cannon

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import org.miklas.ggalaxy.core.common.AssetName
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class SingleShotCannon extends Actor implements Cannon {

    final List<Shot> shots = []

    @Override
    void fire(int x, int y, int angle, int moveSpeed) {
        Shot shot = shots.find { it.mode == Shot.Mode.INACTIVE }
        if (shot == null) {
            shot = new Shot(AssetName.SHOT_RED)
            shots << shot
        }
        shot.fire x, y, angle, moveSpeed
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        shots.forEach {
            it.draw batch, parentAlpha
        }
    }
}