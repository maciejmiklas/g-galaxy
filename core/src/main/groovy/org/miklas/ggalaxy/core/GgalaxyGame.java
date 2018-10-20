package org.miklas.ggalaxy.core;

import com.badlogic.gdx.Game;

public class GalaxyGame extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
