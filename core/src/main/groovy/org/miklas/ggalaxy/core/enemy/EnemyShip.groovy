package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import org.miklas.ggalaxy.core.common.Obstacle

interface EnemyShip extends Obstacle {

    void reset()
    void draw(Batch batch)
}