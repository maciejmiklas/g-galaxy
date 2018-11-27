package org.miklas.ggalaxy.core.common


import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import org.miklas.ggalaxy.core.event.EventBus
import org.miklas.ggalaxy.core.event.EventType
import org.springframework.stereotype.Component

@Component
class Booster extends Actor {

    private long boostStartMs = 0

    @Override
    void draw(Batch batch, float parentAlpha) {
        Keyboard.BOOST.onKeyJustPressed {
            if (boostStartMs > 0) {
                return
            }
            EventBus.event EventType.BOOST_START
            boostStartMs = System.currentTimeMillis()
        }

        // TODO calculate boost time
        if (boostStartMs > 0 && System.currentTimeMillis() > boostStartMs + 5000) {
            boostStartMs = 0
            EventBus.event EventType.BOOST_END
        }
    }
}
