package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.Array

class AnimationFactory {

    static final Map<String, Array<Sprite>> CACHE = new HashMap<>()

    static Animation<Sprite> create(AssetName asset, Animation.PlayMode mode, AssetType type) {
        new Animation<>(Conf.asset(asset).frameDuration, load(asset, type), mode)
    }

    static Array<Sprite> load(AssetName asset, AssetType type) {
        CACHE.computeIfAbsent("${asset}-${type}", {
            def c_an = Conf.asset asset
            Array<Sprite> sprites = new Array<>(c_an.frames)
            1.upto(c_an.frames) {
                def pref = c_an.prefix ?: ""
                Texture texture = [Gdx.files.internal("${c_an.path}/${pref}${it}.png")]
                Sprite sprite = [texture, c_an.imageWidth, c_an.imageHeight]
                sprite.setSize c_an.spriteWith, c_an.spriteHeight
                sprites.add sprite
            }
            sprites
        })
    }


}
