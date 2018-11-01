package org.miklas.ggalaxy.core

import com.badlogic.gdx.math.Rectangle

interface Obstacle {

    boolean checkCollision(Obstacle other)

    void hit(Obstacle other)

    Type getType()

    Rectangle getPosition()

    enum Type {
        MAIN_SHIP,
        ENEMY_SHIP,
        SHOT,
        ASTEROID,
    }
}