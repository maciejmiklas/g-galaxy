package org.miklas.ggalaxy.core.enemy


import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.MathUtils
import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.cannon.Cannon
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.AssetType
import org.miklas.ggalaxy.core.common.Conf
import org.springframework.beans.factory.annotation.Autowired

import static com.badlogic.gdx.utils.TimeUtils.millis

@PackageScope
class Fighter extends Enemy {

    @Autowired
    private Cannon mainCannon
    final AssetType type = AssetType.ENEMY_SHIP
    private int fireDelayMs
    private long lastFireMs = 0
    private def c_cm

    Fighter(AssetName assetName) {
        super(assetName)
        this.c_cm = Conf.cannonMain assetName
        this.fireDelayMs = millis() // do not fire immediately
        updateFireDelay()
    }

    private void updateFireDelay() {
        fireDelayMs = MathUtils.random c_cm.minDelayMs, c_cm.maxDelayMs
    }

    @Override
    protected preDraw(Sprite sprite, Batch batch, float parentAlpha) {

        /*
        def ms = millis()
        if (ms - lastFireMs > fireDelayMs) {
            mainCannon.fire position.x + c_an.cannon.main.position.x as int,
                    position.y + c_an.cannon.main.position.y as int,
                    180,
                    c_cm.shotSpeed
            lastFireMs = ms
            updateFireDelay()
        }*/
    }


}
