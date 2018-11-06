package org.miklas.ggalaxy.core.common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

class SpriteFactory {

    private static final Map<String, Sprite> CACHE = new HashMap<>()

    static Sprite create(Asset asset) {
        if (CACHE.containsKey(asset)) {
            return CACHE.get(asset)
        }

        Texture texture = [Gdx.files.internal(asset.path)]
        Sprite sprite = [texture, asset.imageWidth, asset.imageHeight]
        sprite.setSize asset.spriteWith, asset.spriteHeight

        CACHE.put(asset, sprite)
        sprite
    }

    enum Asset {
        SHOT_RED

        String path
        int imageWidth
        int imageHeight
        int spriteWith
        int spriteHeight

        Asset() {
            String name = name()
            path = Conf.ins.sprite."$name".path
            imageWidth = Conf.ins.sprite."$name".imageWidth
            imageHeight = Conf.ins.sprite."$name".imageHeight
            spriteWith = Conf.ins.sprite."$name".spriteWith
            spriteHeight = Conf.ins.sprite."$name".spriteHeight
        }
    }
}
