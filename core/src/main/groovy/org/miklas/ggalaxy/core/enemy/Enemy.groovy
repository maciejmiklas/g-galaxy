package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import org.miklas.ggalaxy.core.common.Obstacle

interface Enemy extends Obstacle {

    void reset()
    void draw(Batch batch)
}