package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class Key {

    static boolean pressed(Code... code) {
        code.every { it.val.any { Gdx.input.isKeyPressed(it) } }
    }

    enum Code {
        UP(Input.Keys.UP, Input.Keys.W),
        DOWN(Input.Keys.DOWN, Input.Keys.S),
        LEFT(Input.Keys.LEFT, Input.Keys.A),
        RIGHT(Input.Keys.RIGHT, Input.Keys.D),
        BOOST(Input.Keys.SPACE, Input.Keys.B)

        int[] val

        Code(int ... val) {
            this.val = val
        }


    }
}
