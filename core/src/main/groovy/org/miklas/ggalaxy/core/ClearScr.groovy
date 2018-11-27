package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import org.springframework.stereotype.Component

@CompileStatic
@PackageScope
@Component
class ClearScr extends Actor {

    @Override
    void draw(Batch batch, float parentAlpha) {
        Gdx.gl.glClearColor 0, 0, 0.2f, 1
        Gdx.gl.glClear GL20.GL_COLOR_BUFFER_BIT
    }
}
