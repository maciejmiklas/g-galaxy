package org.miklas.ggalaxy.core


import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

@CompileStatic
class Background extends Actor implements Disposable {

    // private Texture skyImg = [Gdx.files.internal("assets/pujohn-das-110287-unsplash.jpg")]

    @Override
    void dispose() {

    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        // background should replace everything on screen, otherwise translucent pixels would be merged
        // batch.disableBlending()
        // batch.draw skyImg, 0, 0, X_RES, Y_RES
        // batch.enableBlending()
    }
}