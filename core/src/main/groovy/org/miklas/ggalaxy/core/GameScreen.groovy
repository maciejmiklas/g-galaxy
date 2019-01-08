package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.StretchViewport
import org.miklas.ggalaxy.core.cannon.Cannon
import org.miklas.ggalaxy.core.common.Booster
import org.miklas.ggalaxy.core.enemy.EnemyDeploy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

import static org.miklas.ggalaxy.core.common.Conf.SCR_HEIGHT
import static org.miklas.ggalaxy.core.common.Conf.SCR_WIDTH

@Component
class GameScreen implements Screen {

    @Autowired
    private SpaceShip mainShip

    @Autowired
    private Cannon mainCannon

    @Autowired
    private Background background

    @Autowired
    private ClearScr clearScr

    @Autowired
    private CollisionDetection collisionDetection

    @Autowired
    private EnemyDeploy enemyDeploy

    @Autowired
    private Booster booster

    private final SpriteBatch batch = []
    private final List<Disposable> disposable = []
    private final OrthographicCamera camera
    private final Stage stage

    GameScreen() {
        stage = [new StretchViewport(SCR_WIDTH, SCR_HEIGHT)]
        camera = stage.getViewport().getCamera() as OrthographicCamera
    }

    @PostConstruct
    void init() {
        collisionDetection << mainShip

        // create the camera and the SpriteBatch
        camera.setToOrtho false, SCR_WIDTH, SCR_HEIGHT

        disposable << batch << stage
        stage << clearScr << background << mainShip << enemyDeploy << mainCannon << booster
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
