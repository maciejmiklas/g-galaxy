package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.StretchViewport
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.cannon.SingleShotCannon
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.Booster
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.enemy.EnemyDeploy

import static org.miklas.ggalaxy.core.common.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.common.Conf.SCR_WIDTH

@PackageScope
class Game extends com.badlogic.gdx.Game {

    @Override
    void create() {
        Stage.metaClass.leftShift = { delegate.addActor it; delegate }

        println "Loaded config: $Conf.cfg"
        setScreen(new GameScreen())
    }

    class GameScreen implements Screen {

        private final ClearScr clearScr = []
        private final SpriteBatch batch = []
        private final Background background = []
        private final List<Disposable> disposable = []
        private final CollisionDetection collisionDetection = []
        private final SingleShotCannon singleCannon = []
        private final OrthographicCamera camera
        private final EnemyDeploy enemyDeploy
        private final SpaceShip mainShip
        private final Stage stage
        private final Booster booster = []

        GameScreen() {
            stage = [new StretchViewport(SCR_WIDTH, SCR_HEIGHT)]
            camera = stage.getViewport().getCamera() as OrthographicCamera
            mainShip = [AssetName.SHIP_INTERCEPTOR_BLUE, AssetName.SHIP_INTERCEPTOR_RED, singleCannon]
            enemyDeploy = [singleCannon]
            collisionDetection << mainShip

            // create the camera and the SpriteBatch
            camera.setToOrtho false, SCR_WIDTH, SCR_HEIGHT

            disposable << batch << stage
            stage << clearScr << background << mainShip << enemyDeploy << singleCannon << booster
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
