package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

@CompileStatic
class Spaceship extends Actor implements Disposable {

    Rectangle position

    private Texture texture = [Gdx.files.internal("assets/small_drone.png")]
    private Vector3 touchPos
    private final int WIDTH = 64
    private final int HEIGHT = 64
    private final int SPEED = 200
    private Sprite sprite
    private OrthographicCamera camera

    Spaceship(OrthographicCamera camera) {
        touchPos = []
        this.camera = camera

        // create a Rectangle to logically represent the bucket
        // note the division by floats, which is much faster than by ints in Groovy

        position = [SCR_WIDTH / 2f - WIDTH / 2f as float, 20, WIDTH, HEIGHT]

        sprite = [texture, 170, 260]
        sprite.setSize(WIDTH, HEIGHT)
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        // make sure the bucket stays within the screen bounds
        if (position.x < 0) {
            position.x = 0
        }

        if (position.y < 0) {
            position.y = 0
        }

        if (position.x > SCR_WIDTH - WIDTH) {
            position.x = SCR_WIDTH - WIDTH as float
        }

        if (position.y > SCR_HEIGHT - HEIGHT) {
            position.y = SCR_HEIGHT - HEIGHT as float
        }

        processUserInput()

        sprite.setPosition position.x, position.y
        sprite.draw batch
    }

    private void processUserInput() {
        if (Gdx.input.touched) {
            touchPos.set Gdx.input.x, Gdx.input.y, 0
            camera.unproject touchPos
            position.x = touchPos.x - WIDTH / 2f as float
        }

        move(Input.Keys.LEFT) { position.x -= it }
        move(Input.Keys.RIGHT) { position.x += it }
        move(Input.Keys.UP) { position.y += it }
        move(Input.Keys.DOWN) { position.y -= it }
    }

    private void move(int key, @ClosureParams(value = SimpleType, options = ['float']) Closure cl) {
        if (!Gdx.input.isKeyPressed(key)) {
            return
        }
        cl.delegate = this.position
        float speed = SPEED * Gdx.graphics.deltaTime as float
        cl(speed)
    }


    @Override
    void dispose() {
        texture.dispose()
    }
}
