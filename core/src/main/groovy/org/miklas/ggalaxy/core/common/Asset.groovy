package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.graphics.g2d.Batch

interface Asset {

    boolean checkCollision(Asset other)

    void hit(Asset other)

    AssetType getType()

    PointG getPosition()

    void draw(Batch batch, float parentAlpha)

}