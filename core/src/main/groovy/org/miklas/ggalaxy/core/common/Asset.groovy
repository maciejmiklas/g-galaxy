package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.graphics.g2d.Batch
import javafx.scene.shape.Rectangle

interface Asset {

    boolean checkCollision(Asset other)

    void hit(Asset other)

    AssetType getType()

    Point getPosition()

    void draw(Batch batch, float parentAlpha)

}