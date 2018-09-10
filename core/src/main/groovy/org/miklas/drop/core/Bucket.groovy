package org.miklas.drop.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static org.miklas.drop.core.Conf.X_RES

@CompileStatic
class Bucket implements Disposable {

    Rectangle position

    private Texture img = [Gdx.files.internal("assets/bucket.png")]
    private Vector3 touchPos

    Bucket() {
        touchPos = []

        // create a Rectangle to logically represent the bucket
        // note the division by floats, which is much faster than by ints in Groovy
        position = [X_RES / 2f - 64 / 2f as float, 20, 64, 64]
    }

    void render(SpriteBatch batch) {
        // make sure the bucket stays within the screen bounds
        if (position.x < 0) {
            position.x = 0
        }
        if (position.x > X_RES - 64) {
            position.x = X_RES - 64
        }

        batch.draw img, position.x, position.y
    }

    void processUserInput(Camera camera) {
        if (Gdx.input.touched) {
            touchPos.set Gdx.input.x, Gdx.input.y, 0
            camera.unproject touchPos
            position.x = touchPos.x - 64 / 2f as float
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= 200 * Gdx.graphics.deltaTime
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += 200 * Gdx.graphics.deltaTime
        }
    }

    @Override
    void dispose() {
        img.dispose();
    }
}
