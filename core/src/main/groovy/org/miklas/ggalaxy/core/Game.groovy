package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.StretchViewport
import groovy.transform.CompileStatic
import org.miklas.ggalaxy.core.cannon.Shots
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.CollisionDetection
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.enemy.EnemyDeploy

import static org.miklas.ggalaxy.core.common.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.common.Conf.SCR_WIDTH

class Game extends com.badlogic.gdx.Game {

    @Override
    void create() {
        println "Loaded config: $Conf.ins"
        setScreen(new GameScreen())
    }

    @CompileStatic
    class GameScreen implements Screen {

        private final ClearScr clearScr = []
        private final SpriteBatch batch = []
        private final Background background = []
        private final List<Disposable> disposable = []
        private final CollisionDetection collisionDetection = []
        private final Shots shots
        private final OrthographicCamera camera
        private final EnemyDeploy enemyDeploy
        private final SpaceShip mainShip
        private final Stage stage

        GameScreen() {
            stage = [new StretchViewport(SCR_WIDTH, SCR_HEIGHT)]
            camera = stage.getViewport().getCamera() as OrthographicCamera
            shots = [collisionDetection]
            mainShip = [AssetName.SHIP_2_BLUE, AssetName.SHIP_2_RED, shots]
            enemyDeploy = [collisionDetection, shots]
            collisionDetection << mainShip

            // create the camera and the SpriteBatch
            camera.setToOrtho false, SCR_WIDTH, SCR_HEIGHT

            disposable << batch << stage

            stage.addActor clearScr
            stage.addActor background
            stage.addActor mainShip
            stage.addActor enemyDeploy
            stage.addActor shots
        }

        @Override
        void render(float delta) {
            // tell the camera to update its matrices, then
            // tell the SpriteBatch to render in the camera's coordinate system
            camera.update()
            batch.projectionMatrix = camera.combined

            batch.begin()
            stage.act()
            collisionDetection.detect()
            stage.draw()
            batch.end()
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
}
