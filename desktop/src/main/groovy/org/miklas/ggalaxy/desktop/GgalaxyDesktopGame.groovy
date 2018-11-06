package org.miklas.ggalaxy.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.Game

LwjglApplicationConfiguration config = new LwjglApplicationConfiguration()
config.title = "G Galaxy"
config.width = Conf.SCR_WIDTH
config.height = Conf.SCR_HEIGHT
new LwjglApplication(new Game(), config)