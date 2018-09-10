package org.miklas.drop.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import org.miklas.drop.core.Drop

LwjglApplicationConfiguration config = new LwjglApplicationConfiguration()
config.title = "Drop"
config.width = Drop.X_RES
config.height = Drop.Y_RES
new LwjglApplication(new Drop(), config)