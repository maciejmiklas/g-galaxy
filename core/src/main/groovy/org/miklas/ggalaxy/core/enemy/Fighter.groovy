package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.TimeUtils
import org.miklas.ggalaxy.core.cannon.MainCannon
import org.miklas.ggalaxy.core.common.*

class Fighter implements EnemyShip {
    private final static Sound CRASH_SOUND

    Mode mode = Mode.ACTIVE
    private Animation<Sprite> animation
    private Animation<Sprite> animationExplosion
    private Animation<Sprite> animationNormal
    private float animationStateTime = 0.0f
    final Rectangle position = []
    final AssetType type = AssetType.ASTEROID
    private AssetName fighterAsset
    private AssetName explosionAsset
    private float explosionAdjustX = 0
    private float explosionAdjustY = 0
    private int fireDelayMs
    private long lastFireMs = 0
    private MainCannon mainCannon
    def c_an
    def c_cm
    def c_mv
    static {
        CRASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
    }

    Fighter(AssetName fighterAsset, AssetName explosionAsset, MainCannon mainCannon) {
        this.fighterAsset = fighterAsset
        this.mainCannon = mainCannon
        this.explosionAsset = explosionAsset
        this.c_an = Conf.animation(fighterAsset)
        this.c_cm = Conf.cannonMain AssetType.ENEMY_SHIP
        this.c_mv = Conf.movement AssetType.ENEMY_SHIP
        this.explosionAdjustX = c_an.spriteHeight / 2 - c_an.spriteHeight / 2
        this.explosionAdjustY = c_an.spriteWith / 2 - c_an.spriteWith / 2
        this.animationNormal = AnimationFactory.create(fighterAsset, Animation.PlayMode.LOOP, type)
        this.animationExplosion = AnimationFactory.create(explosionAsset, Animation.PlayMode.NORMAL, type)
        updateFireDelay()
        reset()
    }

    private void updateFireDelay() {
        fireDelayMs = MathUtils.random c_cm.minDelayMs, c_cm.maxDelayMs
    }

    @Override
    void draw(Batch batch) {
        if (mode == Mode.INACTIVE) {
            return
        }

        // reached bottom of the screen ?
        if (position.y + c_an.spriteHeight < 0) {
            mode = Mode.INACTIVE
            return
        }

        Sprite sprite = animation.getKeyFrame animationStateTime
        sprite.setPosition position.x, position.y
        sprite.setOrigin sprite.width / 2 as float, sprite.height / 2 as float
        sprite.rotation = 180
        sprite.draw batch
        animationStateTime += Gdx.graphics.getDeltaTime()
        position.y -= c_mv.moveSpeed * Gdx.graphics.deltaTime

        if (TimeUtils.millis() - lastFireMs > fireDelayMs) {
            mainCannon.fire position.x + c_an.cannon.main.position.x as int,
                    position.y + c_an.cannon.main.position.y as int,
                    180,
                    c_cm.moveSpeed
            lastFireMs = TimeUtils.millis()
            updateFireDelay()
        }
    }

    @Override
    void reset() {
        mode = Mode.ACTIVE
        animation = animationNormal
        animationStateTime = 0.0f
        position.set MathUtils.random(0, Conf.SCR_WIDTH - c_an.spriteWith), Conf.SCR_HEIGHT, c_an.spriteWith, c_an.spriteHeight
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
