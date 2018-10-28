package org.miklas.ggalaxy.core

import com.badlogic.gdx.math.Rectangle

interface Obstacle {

    boolean collision(Rectangle other)

    void hit(Type other)

    Type getType()

    Rectangle getPosition()

    enum Type {
        SHIP,
        SHOT,
        ASTEROID,
    }
}