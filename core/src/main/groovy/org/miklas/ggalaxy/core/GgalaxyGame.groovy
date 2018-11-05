package org.miklas.ggalaxy.core

import com.badlogic.gdx.Game

class GgalaxyGame extends Game {
    @Override
    void create() {
        println "Loaded config: $Conf.ins"
        setScreen(new GameScreen())
    }
}
