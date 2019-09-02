package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

class SpriteFactory {

    static final Map<String, Sprite> CACHE = new HashMap<>()

    static Sprite create(AssetName asset) {
        if (CACHE.containsKey(asset)) {
            return CACHE.get(asset)
        }

        def c_sp = Conf.sprite asset
        Texture texture = [Gdx.files.internal(c_sp.path)]
        Sprite sprite = [texture, c_sp.imageWidth, c_sp.imageHeight]
        sprite.setSize c_sp.spriteWith, c_sp.spriteHeight

        CACHE.put(asset, sprite)
        return sprite
    }
}
