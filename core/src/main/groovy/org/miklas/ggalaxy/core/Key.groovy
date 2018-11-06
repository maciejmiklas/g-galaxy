package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

enum Key {

    UP(Input.Keys.UP, Input.Keys.W),
    DOWN(Input.Keys.DOWN, Input.Keys.S),
    LEFT(Input.Keys.LEFT, Input.Keys.A),
    RIGHT(Input.Keys.RIGHT, Input.Keys.D),
    BOOST(Input.Keys.SPACE, Input.Keys.B)

    final int[] val
    Key(int ... val) {
        this.val = val
    }

    boolean pressed() {
        val.any { Gdx.input.isKeyPressed(it) }
    }

    /**
     * @param code combination of key codes, all given keys has to be pressed at once.
     */
    static boolean pressedAll(Key... code) {
        code.every { it.val.any { Gdx.input.isKeyPressed(it) } }
    }

    /**
     * @param true if any of given keys is on
     */
    static Key pressedAny(Key... code) {
        code.find { it.val.find { Gdx.input.isKeyPressed(it) } }
    }

    void move(@ClosureParams(value = SimpleType, options = ['float']) Closure cl) {
        if (!pressed()) {
            return
        }

        int speedConst = Conf.ins.key.move.speed
        if (Key.BOOST.pressed()) {
            speedConst += Conf.ins.key.move.boost
        }
        float speedCalc = speedConst * Gdx.graphics.deltaTime as float
        cl(speedCalc)
    }


    static boolean vertical(@ClosureParams(value = SimpleType, options = "org.miklas.ggalaxy.core.Key") Closure cl) {
        if (!pressedAny(UP, DOWN)) {
            return false
        }

        Key key = UP.pressed() ? UP : DOWN
        cl.call key
        true
    }

    static boolean horizontal(@ClosureParams(value = SimpleType, options = "org.miklas.ggalaxy.core.Key") Closure cl) {
        Key dKey = pressedAny(LEFT, RIGHT)
        if (!dKey) {
            return false
        }
        cl.call dKey
        true
    }

}
