package org.miklas.ggalaxy.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import org.miklas.ggalaxy.core.Conf
import org.miklas.ggalaxy.core.GgalaxyGame

LwjglApplicationConfiguration config = new LwjglApplicationConfiguration()
config.title = "Drop"
config.width = Conf.SCR_WIDTH
config.height = Conf.SCR_HEIGHT
new LwjglApplication(new GgalaxyGame(), config)