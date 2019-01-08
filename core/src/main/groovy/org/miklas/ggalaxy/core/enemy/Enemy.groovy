package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.*
import org.miklas.ggalaxy.core.event.EventBus
import org.miklas.ggalaxy.core.event.EventType

@PackageScope
abstract class Enemy implements Asset {
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
    private Sound crashSound

    Enemy(AssetName assetName) {
        this.crashSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"))

        this.c_an = Conf.animation assetName
        this.c_ea = Conf.enemyAsset assetName
        AssetName explosionName = c_an.explosion

        def c_ex = Conf.animation explosionName
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

        Sprite sprite = animation.getKeyFrame animationStateTime
        sprite.setPosition position.x, position.y
        sprite.setOrigin sprite.width / 2 as float, sprite.height / 2 as float
        sprite.rotation = 180

        preDraw sprite, batch, parentAlpha

        sprite.draw batch

        animationStateTime += Gdx.graphics.getDeltaTime()
        "move_${c_ea.movingPattern.toLowerCase()}"()
    }

    private void move_straight() {
        position.y -= c_ea.modeSpeed * Gdx.graphics.deltaTime
    }

    int sinLength = random(10, 100)
    int sinAmp = random(5, 20)

    private void move_sinus() {
        position.x += sinAmp * Math.sin(position.y / sinLength)
        position.y -= c_ea.modeSpeed * Gdx.graphics.deltaTime
    }


    int random(int min, int max) {
        return (int) (Math.random() * (max - min)) + min
    }


    protected preDraw(Sprite sprite, Batch batch, float parentAlpha) {

    }

    protected boolean shouldDraw() {
        if (mode == Mode.INACTIVE) {
            return false
        }

        // reached bottom of the screen ?
        if (position.y + c_an.spriteHeight < 0) {
            mode = Mode.INACTIVE
            return false
        }
        true
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

    enum MovingPattern {
        STRAIGHT,
        SINUS
    }
}
