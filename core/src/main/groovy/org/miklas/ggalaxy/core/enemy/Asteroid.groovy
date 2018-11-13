package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import org.miklas.ggalaxy.core.common.AnimationFactory
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle
import org.miklas.ggalaxy.core.common.AssetType

class Asteroid implements EnemyShip {
    private final static Sound CRASH_SOUND

    Mode mode = Mode.ACTIVE
    private Animation<Sprite> animation
    private Animation<Sprite> animationExplosion
    private Animation<Sprite> animationNormal
    private float animationStateTime = 0.0f
    final Rectangle position = []
    final AssetType type = AssetType.ASTEROID
    private AssetName asteroid
    private AssetName explosion
    private float explosionAdjustX = 0
    private float explosionAdjustY = 0
    private def c_ac
    static {
        CRASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
    }

    Asteroid(AssetName asteroid, AssetName explosion) {
        this.c_ac = Conf.animation asteroid
        this.asteroid = asteroid
        this.explosion = explosion
        this.explosionAdjustX = c_ac.spriteHeight / 2 - c_ac.spriteHeight / 2
        this.explosionAdjustY = c_ac.spriteWith / 2 - c_ac.spriteWith / 2
        this.animationNormal = AnimationFactory.create(asteroid, Animation.PlayMode.LOOP, type)
        this.animationExplosion = AnimationFactory.create(explosion, Animation.PlayMode.NORMAL, type)
        reset()
    }

    @Override
    void draw(Batch batch) {
        if (mode == Mode.INACTIVE) {
            return
        }

        // reached bottom of the screen ?
        if (position.y + c_ac.spriteHeight < 0) {
            mode = Mode.INACTIVE
            return
        }

        Sprite sprite = animation.getKeyFrame animationStateTime
        sprite.setPosition position.x, position.y
        sprite.draw batch
        animationStateTime += Gdx.graphics.getDeltaTime()
        position.y -= Conf.movement(AssetType.ASTEROID).moveSpeed * Gdx.graphics.deltaTime
    }

    @Override
    void reset() {
        mode = Mode.ACTIVE
        animation = animationNormal
        animationStateTime = 0.0f
        position.set MathUtils.random(0, Conf.SCR_WIDTH - c_ac.spriteWith), Conf.SCR_HEIGHT, c_ac.spriteWith, c_ac.spriteHeight
    }

    @Override
    boolean checkCollision(Obstacle other) {
        mode == Mode.ACTIVE && other.type != AssetType.ASTEROID && position.overlaps(other.position)
    }

    @Override
    void hit(Obstacle other) {
        if (mode != Mode.ACTIVE) {
            return
        }

        mode = Mode.EXPLODING
        CRASH_SOUND.play()
        animationStateTime = 0.0f
        position.x -= explosionAdjustX
        position.y -= explosionAdjustY
        animation = animationExplosion
    }

    enum Mode {
        EXPLODING,
        INACTIVE,
        ACTIVE
    }
}
