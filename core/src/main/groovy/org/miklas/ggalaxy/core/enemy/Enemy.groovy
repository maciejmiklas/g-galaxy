package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.*
import org.miklas.ggalaxy.core.event.EventBus
import org.miklas.ggalaxy.core.event.EventType
import org.miklas.ggalaxy.core.path.PathFollowing

@PackageScope
abstract class Enemy implements Asset {
    Mode mode = Mode.INACTIVE
    Point position = []

    final def c_an
    final def c_ea
    Animation<Sprite> animation
    final Animation<Sprite> animationExplosion
    final Animation<Sprite> animationNormal
    float animationStateTime = 0.0f
    final PathFollowing pathFollowing

    float explosionAdjustX = 0
    float explosionAdjustY = 0
    final Sound crashSound

    Enemy(AssetName assetName, PathFollowing pathFollowing) {
        this.crashSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))
        this.pathFollowing = pathFollowing

        this.c_an = Conf.asset assetName
        this.c_ea = Conf.enemyAsset assetName
        AssetName explosionName = c_an.explosion

        def c_ex = Conf.asset explosionName
        this.explosionAdjustX = c_ex.spriteHeight / 2 - c_an.spriteHeight / 2
        this.explosionAdjustY = c_ex.spriteWith / 2 - c_an.spriteWith / 2

        this.animationNormal = AnimationFactory.create(assetName, Animation.PlayMode.LOOP, type)
        this.animationExplosion = AnimationFactory.create(explosionName, Animation.PlayMode.NORMAL, type)

        EventBus.event EventType.OBSTACLE_CREATED, this
    }

    @Override
    void hit(Asset other) {
        mode = Mode.EXPLODING
        crashSound.play()
        animationStateTime = 0.0f
        position.x -= explosionAdjustX
        position.y -= explosionAdjustY
        animation = animationExplosion
    }

    @Override
    boolean checkCollision(Asset other) {
        mode == Mode.ACTIVE && (other.getType() == AssetType.SHOT || other.getType() == AssetType.SPACE_SHIP) && position.overlaps(other.position)
    }

    @Override
    final void draw(Batch batch, float parentAlpha) {
        if (!shouldDraw()) {
            return
        }

        if (mode == Mode.ACTIVE) {
            position << pathFollowing.next()
        }

        Sprite sprite = animation.getKeyFrame animationStateTime
        sprite.setPosition position.x, position.y
        sprite.setOrigin sprite.width / 2 as float, sprite.height / 2 as float
        sprite.rotation = 90

        preDraw sprite, batch, parentAlpha

        sprite.draw batch

        animationStateTime += Gdx.graphics.getDeltaTime()
    }


    int random(int min, int max) {
        (int) (Math.random() * (max - min)) + min
    }

    void preDraw(Sprite sprite, Batch batch, float parentAlpha) {

    }

    boolean shouldDraw() {
        if (mode == Mode.INACTIVE) {
            return false
        }

        if (!pathFollowing.hasNext()) {
            return false
        }

        // reached bottom of the screen ?
        if (position.y + c_an.spriteHeight < 0) {
            mode = Mode.INACTIVE
            return false
        }
        true
    }

    void deploy() {
        mode = Mode.ACTIVE
        animation = animationNormal
        animationStateTime = 0.0f
        pathFollowing.reset()
    }

    enum Mode {
        INACTIVE,
        ACTIVE,
        EXPLODING
    }

    enum MovingPattern {
        STRAIGHT,
        SINUS
    }
}
