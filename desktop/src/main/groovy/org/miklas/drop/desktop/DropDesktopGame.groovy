package org.miklas.drop.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import org.miklas.drop.core.Conf
import org.miklas.drop.core.DropGame

LwjglApplicationConfiguration config = new LwjglApplicationConfiguration()
config.title = "Drop"
config.width = Conf.X_RES
config.height = Conf.Y_RES
new LwjglApplication(new DropGame(), config)