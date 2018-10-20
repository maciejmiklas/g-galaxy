package org.miklas.ggalaxy.core


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import groovy.transform.CompileStatic

import static Conf.X_RES
import static Conf.Y_RES

@CompileStatic
class GameScreen implements Screen {

    private OrthographicCamera camera
    private SpriteBatch batch
    private Music rainMusic
    private List<Disposable> disposable
    private Raindrops raindrops
    private ClearScr clearScr
    private Bucket bucket
    private Background background
    private Stage stage

    GameScreen() {
        batch = []
        disposable = []
        clearScr = []
        background = []
        stage = [new ScreenViewport()]
        camera = stage.getViewport().getCamera() as OrthographicCamera
        bucket = [camera]
        raindrops = [bucket]

        // load the drop sound effect and the rain clearScr "music"
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"))

        // start the playback of the clearScr music immediately
        //rainMusic.looping = true
        // rainMusic.play()

        // create the camera and the SpriteBatch
        camera.setToOrtho(false, X_RES, Y_RES)

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

        // begin a new batch and draw the bucket and all drops
        batch.begin()
        stage.act()
        stage.draw()
        batch.end()

        // check if we need to create a new raindrop
        raindrops.spawnRaindrop()
    }

    @Override
    void dispose() {
        disposable*.dispose()
    }

    @Override
    void show() {

    }

    @Override
    void resize(int x, int y) {
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

