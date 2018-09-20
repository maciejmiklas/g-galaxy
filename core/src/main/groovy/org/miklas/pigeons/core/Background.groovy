package org.miklas.pigeons.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static org.miklas.pigeons.core.Conf.X_RES
import static org.miklas.pigeons.core.Conf.Y_RES

@CompileStatic
class Background implements Renderable, Disposable {

    private Texture skyImg = [Gdx.files.internal("assets/pujohn-das-110287-unsplash.jpg")]

    @Override
    void render(Batch batch) {

        // background should replace everything on screen, otherwise translucent pixels would be merged
        batch.disableBlending()
        batch.draw skyImg, 0, 0, X_RES, Y_RES
        batch.enableBlending()
    }

    @Override
    void dispose() {

    }
}