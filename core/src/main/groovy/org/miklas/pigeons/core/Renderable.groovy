package org.miklas.pigeons.core

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.Camera
import groovy.transform.CompileStatic

@CompileStatic
trait Renderable {

    void render(Batch batch, Camera camera) {
        render(batch)
    }

    void render(Batch batch) {

    }
}
