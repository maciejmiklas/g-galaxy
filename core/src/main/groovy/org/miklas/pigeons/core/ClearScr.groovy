package org.miklas.pigeons.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import groovy.transform.CompileStatic

@CompileStatic
class ClearScr implements Renderable {

    @Override
    void render(Batch batch) {
        Gdx.gl.glClearColor 0, 0, 0.2f, 1
        Gdx.gl.glClear GL20.GL_COLOR_BUFFER_BIT
    }
}
