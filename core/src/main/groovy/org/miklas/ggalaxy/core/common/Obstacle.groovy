package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle

interface Obstacle {

    boolean checkCollision(Obstacle other)

    void hit(Obstacle other)

    AssetType getType()

    Rectangle getPosition()

    void reset()

    void draw(Batch batch, float parentAlpha)

}