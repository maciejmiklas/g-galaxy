package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import org.miklas.ggalaxy.core.common.AnimationFactory
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Obstacle
import org.miklas.ggalaxy.core.common.ObstacleType

class Asteroid implements Enemy {
    private final static Sound CRASH_SOUND

    Mode mode = Mode.ACTIVE
    private Animation<Sprite> animation
    private Animation<Sprite> animationExplosion
    private Animation<Sprite> animationNormal
    private float animationStateTime = 0.0f
    final Rectangle position = []
    final ObstacleType type = ObstacleType.ASTEROID
    private AnimationFactory.Asset asteroid
    private AnimationFactory.Asset explosion
    private float explosionAdjustX = 0
    private float explosionAdjustY = 0

    static {
        CRASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
    }

    Asteroid(AnimationFactory.Asset asteroid, AnimationFactory.Asset explosion) {
        this.asteroid = asteroid
        this.explosion = explosion
        this.explosionAdjustX = explosion.spriteHeight / 2 - asteroid.spriteHeight / 2
        this.explosionAdjustY = explosion.spriteWith / 2 - asteroid.spriteWith / 2
        this.animationNormal = AnimationFactory.create(asteroid, Animation.PlayMode.LOOP, type)
        this.animationExplosion = AnimationFactory.create(explosion, Animation.PlayMode.NORMAL, type)
        reset()
    }

    void draw(Batch batch) {
        if (mode == Mode.INACTIVE) {
            return
        }

        // reached bottom of the screen ?
        if (position.y + asteroid.spriteHeight < 0) {
            mode = Mode.INACTIVE
            return
        }

        Sprite sprite = animation.getKeyFrame animationStateTime
        sprite.setPosition position.x, position.y
        sprite.draw batch
        animationStateTime += Gdx.graphics.getDeltaTime()
        position.y -= Conf.ins.asteroid.moveSpeed * Gdx.graphics.deltaTime
    }

    @Override
    void reset() {
        mode = Mode.ACTIVE
        animation = animationNormal
        animationStateTime = 0.0f
        position.set MathUtils.random(0, Conf.SCR_WIDTH - asteroid.spriteWith), Conf.SCR_HEIGHT, asteroid.spriteWith, asteroid.spriteHeight
    }

    @Override
    boolean checkCollision(Obstacle other) {
        mode == Mode.ACTIVE && other.type != ObstacleType.ASTEROID && position.overlaps(other.position)
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
