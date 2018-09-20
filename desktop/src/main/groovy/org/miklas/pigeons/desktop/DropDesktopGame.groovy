package org.miklas.pigeons.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import org.miklas.pigeons.core.Conf
import org.miklas.pigeons.core.Game

LwjglApplicationConfiguration config = new LwjglApplicationConfiguration()
config.title = "Drop"
config.width = Conf.X_RES
config.height = Conf.Y_RES
new LwjglApplication(new Game(), config)