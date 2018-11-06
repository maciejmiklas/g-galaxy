package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.math.Rectangle

interface Obstacle {

    boolean checkCollision(Obstacle other)

    void hit(Obstacle other)

    Type getType()

    Rectangle getPosition()

    enum Type {
        SPACE_SHIP,
        ENEMY_SHIP,
        SHOT,
        ASTEROID,
    }
}