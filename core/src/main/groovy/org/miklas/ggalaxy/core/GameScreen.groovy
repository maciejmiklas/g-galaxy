package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.StretchViewport
import groovy.transform.CompileStatic

import static org.miklas.ggalaxy.core.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.Conf.SCR_WIDTH

@CompileStatic
class GameScreen implements Screen {

    private final ClearScr clearScr = []
    private final SpriteBatch batch = []
    private final Background background = []
    private final List<Disposable> disposable = []

    private final OrthographicCamera camera
    private final Asteroids asteroids
    private final MainShip mainShip
    private final Stage stage

    GameScreen() {
        stage = [new StretchViewport(SCR_WIDTH, SCR_HEIGHT)]
        camera = stage.getViewport().getCamera() as OrthographicCamera
        mainShip = [AnimationFactory.Asset.MAIN_SHIP_BLUE]
        asteroids = [mainShip]

        // create the camera and the SpriteBatch
        camera.setToOrtho false, SCR_WIDTH, SCR_HEIGHT

        // spawn the first raindrop
        asteroids.spawn()

        disposable << mainShip << asteroids << batch << Asteroid.disposable() << background << stage

        stage.addActor clearScr
        stage.addActor background
        stage.addActor mainShip
        stage.addActor asteroids
    }

    @Override
    void render(float delta) {
        // tell the camera to update its matrices, then
        // tell the SpriteBatch to render in the camera's coordinate system
        camera.update()
        batch.projectionMatrix = camera.combined

        batch.begin()
        stage.act()
        stage.draw()
        batch.end()

        // pressedAll if we need to create a new raindrop
        asteroids.spawn()
    }

    @Override
    void dispose() {
        disposable*.dispose()
    }

    @Override
    void show() {
        Gdx.input.inputProcessor = stage
    }

    @Override
    void resize(int x, int y) {
        stage.viewport.update x, y, true
    }

    @Override
    void pause() {
    }

    @Override
    void resume() {
    }

    @Override
    void hide() {

    }
}

