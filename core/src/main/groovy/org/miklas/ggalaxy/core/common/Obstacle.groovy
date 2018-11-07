package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.math.Rectangle

interface Obstacle {

    boolean checkCollision(Obstacle other)

    void hit(Obstacle other)

    ObstacleType getType()

    Rectangle getPosition()

}