package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle

interface Asset {

    boolean checkCollision(Asset other)

    void hit(Asset other)

    AssetType getType()

    Rectangle getPosition()

    void draw(Batch batch, float parentAlpha)

}