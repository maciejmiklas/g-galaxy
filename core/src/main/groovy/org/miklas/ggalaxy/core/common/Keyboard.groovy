package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

enum Keyboard {

    UP(Input.Keys.UP, Input.Keys.W),
    DOWN(Input.Keys.DOWN, Input.Keys.S),
    LEFT(Input.Keys.LEFT, Input.Keys.A),
    RIGHT(Input.Keys.RIGHT, Input.Keys.D),
    BOOST(Input.Keys.SPACE, Input.Keys.B),
    FIRE(Input.Keys.X)

    final int[] val

    Keyboard(int ... val) {
        this.val = val
    }

    boolean pressed() {
        val.any { Gdx.input.isKeyPressed(it) }
    }

    /**
     * @param code combination of key codes, all given keys has to be pressed at once.
     */
    static boolean pressedAll(Keyboard... code) {
        code.every { it.val.any { Gdx.input.isKeyPressed(it) } }
    }

    /**
     * @param true if any of given keys is on
     */
    static Keyboard pressedAny(Keyboard... code) {
        code.find { it.val.find { Gdx.input.isKeyPressed(it) } }
    }

    void onFire(Closure cl) {
        if (FIRE.pressed()) {
            cl()
        }
    }

    void onMove(@ClosureParams(value = SimpleType, options = ['float']) Closure cl) {
        if (!pressed()) {
            return
        }

        int speedConst = Conf.ins.key.move.speed
        if (Keyboard.BOOST.pressed()) {
            speedConst += Conf.ins.key.move.boost
        }
        float speedCalc = speedConst * Gdx.graphics.deltaTime as float
        cl(speedCalc)
    }

    static boolean vertical(@ClosureParams(value = SimpleType, options = "org.miklas.ggalaxy.core.Keyboard") Closure cl) {
        if (!pressedAny(UP, DOWN)) {
            return false
        }

        Keyboard key = UP.pressed() ? UP : DOWN
        cl.call key
        true
    }

    static boolean horizontal(@ClosureParams(value = SimpleType, options = "org.miklas.ggalaxy.core.Keyboard") Closure cl) {
        Keyboard dKey = pressedAny(LEFT, RIGHT)
        if (!dKey) {
            return false
        }
        cl.call dKey
        true
    }

}
