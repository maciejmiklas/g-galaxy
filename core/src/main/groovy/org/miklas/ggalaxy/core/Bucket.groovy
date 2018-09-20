package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic

import static Conf.X_RES

@CompileStatic
class Bucket implements Disposable, Renderable {

    Rectangle position

    private Texture bucketImg = [Gdx.files.internal("assets/bucket.png")]
    private Vector3 touchPos
    private final int WIDTH = 64
    private final int HEIGHT = 64
    private Sprite bucketSpr

    Bucket() {
        touchPos = []

        // create a Rectangle to logically represent the bucket
        // note the division by floats, which is much faster than by ints in Groovy

        position = [X_RES / 2f - WIDTH / 2f as float, 20, WIDTH, HEIGHT]

        bucketSpr = [bucketImg, WIDTH, HEIGHT]
    }

    @Override
    void render(Batch batch, Camera camera) {
        // make sure the bucket stays within the screen bounds
        if (position.x < 0) {
            position.x = 0
        }

        if (position.x > X_RES - WIDTH) {
            position.x = X_RES - WIDTH as float
        }

        processUserInput camera

        bucketSpr.setPosition position.x, position.y
        bucketSpr.draw batch
    }

    private void processUserInput(Camera camera) {
        if (Gdx.input.touched) {
            touchPos.set Gdx.input.x, Gdx.input.y, 0
            camera.unproject touchPos
            position.x = touchPos.x - WIDTH / 2f as float
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
        bucketImg.dispose()
    }
}
