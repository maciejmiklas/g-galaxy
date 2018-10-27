package org.miklas.ggalaxy.core;

import com.badlogic.gdx.Game
import groovy.transform.CompileStatic;

@CompileStatic
class GgalaxyGame extends Game {
    @Override
    void create() {
        setScreen(new GameScreen())
    }
}
