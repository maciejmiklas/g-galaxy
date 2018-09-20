package org.miklas.ggalaxy.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import org.miklas.ggalaxy.core.Conf
import org.miklas.ggalaxy.core.Game

LwjglApplicationConfiguration config = new LwjglApplicationConfiguration()
config.title = "Drop"
config.width = Conf.X_RES
config.height = Conf.Y_RES
new LwjglApplication(new Game(), config)