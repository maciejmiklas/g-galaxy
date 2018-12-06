package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.AnimationFactory
import org.miklas.ggalaxy.core.common.Asset
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.event.EventBus
import org.miklas.ggalaxy.core.event.EventType

@PackageScope
abstract class Deployable implements Asset {
    Mode mode = Mode.INACTIVE
    final Rectangle position = []

    protected def c_an
    protected def c_ea
    protected Animation<Sprite> animation
    protected Animation<Sprite> animationExplosion
    protected Animation<Sprite> animationNormal
    protected float animationStateTime = 0.0f

    private float explosionAdjustX = 0
    private float explosionAdjustY = 0

    private final static Sound CRASH_SOUND
    static {
        CRASH_SOUND = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
    }

    Deployable(AssetName assetName) {
        this.c_an = Conf.animation assetName
        this.c_ea = Conf.enemyAsset assetName

        this.explosionAdjustX = c_an.spriteHeight
        this.explosionAdjustY = c_an.spriteWith

        AssetName explosionName = c_an.explosion
        this.animationNormal = AnimationFactory.create(assetName, Animation.PlayMode.LOOP, type)
        this.animationExplosion = AnimationFactory.create(explosionName, Animation.PlayMode.NORMAL, type)

        EventBus.event EventType.OBSTACLE_CREATED, this
    }

    @Override
    void hit(Asset other) {
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


    void deploy(int x, int y) {
        mode = Mode.ACTIVE
        animation = animationNormal
        animationStateTime = 0.0f
        position.set x, y, c_an.spriteWith, c_an.spriteHeight
    }

    enum Mode {
        INACTIVE,
        ACTIVE,
        EXPLODING
    }
}
