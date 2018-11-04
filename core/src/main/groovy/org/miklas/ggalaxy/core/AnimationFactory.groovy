package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.Array

class AnimationFactory {

    static Animation<Sprite> createAnimation(Asset asset){
        new Animation<>(asset.frameDuration, load(asset), Animation.PlayMode.LOOP)
    }

    static Array<Sprite> load(Asset name) {
        Array<Sprite> sprites = new Array<>(name.frames)
        1.upto(name.frames) {
            Texture texture = [Gdx.files.internal("${name.path}/${it}.png")]
            Sprite sprite = [texture, name.imageWidth, name.imageHeight]
            sprite.setSize name.spriteWith, name.spriteHeight
            sprites.add sprite
        }
        sprites
    }

    enum Asset {
        MAIN_SHIP_BLUE,
        SPACE_BOMB_BLUE,
        SPACE_MINE_BLUE,
        SPACE_MINE_RED

        String path
        int frames
        int imageWidth
        int imageHeight
        int spriteWith
        int spriteHeight
        float frameDuration

        Asset() {
            String name = name()
            path = Conf.ins.animation."$name".path
            this.frames =  Conf.ins.animation."$name".frames
            this.imageWidth =  Conf.ins.animation."$name".imageWidth
            this.imageHeight =  Conf.ins.animation."$name".imageHeight
            this.spriteWith =  Conf.ins.animation."$name".spriteWith
            this.spriteHeight =  Conf.ins.animation."$name".spriteHeight
            this.frameDuration =  Conf.ins.animation."$name".frameDuration
        }
    }
}
