package org.miklas.ggalaxy.core


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
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

    private OrthographicCamera camera
    private SpriteBatch batch
    private Music rainMusic
    private List<Disposable> disposable
    private Raindrops raindrops
    private ClearScr clearScr
    private Spaceship bucket
    private Background background
    private Stage stage

    GameScreen() {
        batch = []
        disposable = []
        clearScr = []
        background = []
        stage = [new StretchViewport(SCR_WIDTH, SCR_HEIGHT)]
        camera = stage.getViewport().getCamera() as OrthographicCamera
        bucket = [camera]
        raindrops = [bucket]

        // load the drop sound effect and the rain clearScr "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"))

        // start the playback of the clearScr music immediately
        //rainMusic.looping = true
        // rainMusic.play()

        // create the camera and the SpriteBatch
        camera.setToOrtho false, SCR_WIDTH, SCR_HEIGHT

        // spawn the first raindrop
        raindrops.spawnRaindrop()

        disposable << bucket << raindrops << rainMusic << batch << Raindrop.disposable() << background << stage

        stage.addActor clearScr
        stage.addActor background
        stage.addActor bucket
        stage.addActor raindrops
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

        // pressed if we need to create a new raindrop
        raindrops.spawnRaindrop()
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

