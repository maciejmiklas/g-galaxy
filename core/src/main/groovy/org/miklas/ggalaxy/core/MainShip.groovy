package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Disposable
import groovy.transform.CompileStatic
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

@CompileStatic
class MainShip extends Actor implements Disposable {

    Rectangle position
    private final int WIDTH = 64
    private final int HEIGHT = 64
    private final int SPEED = 200
    private final int BOOST = 100
    private final float FRAME_DURATION = 0.05f
    private Animation<Sprite> animation
    private float animationStartTime = 0.0f

    MainShip() {

        // create a Rectangle to logically represent the mainShip
        // note the division by floats, which is much faster than by ints in Groovy

        position = [SCR_WIDTH / 2f - WIDTH / 2f as float, 20, WIDTH, HEIGHT]
        animation = new Animation<>(FRAME_DURATION, AnimationFactory.load(AnimationFactory.Name.MAIN_SHIP_BLUE), Animation.PlayMode.LOOP)
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        // make sure the mainShip stays within the screen bounds
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

        animationStartTime += Gdx.graphics.getDeltaTime()
        Sprite sprite = animation.getKeyFrame animationStartTime
        sprite.setPosition position.x, position.y
        sprite.draw batch
    }

    private void processUserInput() {
        move(Key.Code.LEFT) { position.x -= it }
        move(Key.Code.RIGHT) { position.x += it }
        move(Key.Code.UP) { position.y += it }
        move(Key.Code.DOWN) { position.y -= it }
    }

    private void move(Key.Code code, @ClosureParams(value = SimpleType, options = ['float']) Closure cl) {
        if (!Key.pressed(code)) {
            return
        }

        int speedConst = Key.pressed(Key.Code.BOOST) ? SPEED + BOOST : SPEED
        float speed = speedConst * Gdx.graphics.deltaTime as float
        cl(speed)
    }


    @Override
    void dispose() {
        // TODO dispose sprites from animation
    }
}
