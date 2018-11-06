package org.miklas.ggalaxy.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.Array

class AnimationFactory {

    private static final Map<String, Array<Sprite>> CACHE = new HashMap<>()

    static Animation<Sprite> createAnimation(Asset asset, Animation.PlayMode mode) {
        new Animation<>(asset.frameDuration, load(asset), mode)
    }

    static Array<Sprite> load(Asset asset) {
        if (CACHE.containsKey(asset)) {
            return CACHE.get(asset)
        }

        Array<Sprite> sprites = new Array<>(asset.frames)
        1.upto(asset.frames) {
            Texture texture = [Gdx.files.internal("${asset.path}/${asset.prefix}${it}.png")]
            Sprite sprite = [texture, asset.imageWidth, asset.imageHeight]
            sprite.setSize asset.spriteWith, asset.spriteHeight
            sprites.add sprite
        }

        CACHE.put(asset, sprites)
        sprites
    }

    enum Asset {
        MAIN_SHIP_BLUE,
        BOMB_BLUE,
        MINE_BLUE,
        MINE_RED,
        PROTON_STAR,
        EXPLOSION_BLUE

        String path
        int frames
        int imageWidth
        int imageHeight
        int spriteWith
        int spriteHeight
        float frameDuration
        String prefix

        Asset() {
            String name = name()
            path = Conf.ins.animation."$name".path
            frames = Conf.ins.animation."$name".frames
            imageWidth = Conf.ins.animation."$name".imageWidth
            imageHeight = Conf.ins.animation."$name".imageHeight
            spriteWith = Conf.ins.animation."$name".spriteWith
            spriteHeight = Conf.ins.animation."$name".spriteHeight
            frameDuration = Conf.ins.animation."$name".frameDuration
            prefix = Conf.ins.animation."$name".prefix ?: ''
        }
    }
}
