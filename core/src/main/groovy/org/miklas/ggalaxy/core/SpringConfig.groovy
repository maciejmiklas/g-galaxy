package org.miklas.ggalaxy.core

import com.badlogic.gdx.scenes.scene2d.Stage
import org.miklas.ggalaxy.core.common.PointG
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

@Configuration
@ComponentScan('org.miklas')
class SpringConfig {
    static boolean initialized = false;

    @PostConstruct
    void init() {
        initMeta()
    }

    static void initMeta() {
        if (initialized) {
            return
        }
        Stage.metaClass.leftShift = { delegate.addActor it; delegate }
        Number.metaClass {
            getPow2 = { delegate * delegate }
            getPow3 = { delegate * delegate * delegate }
            getMs = { delegate }
            getSec = { delegate * 1000 }
            multiply = { PointG p -> new PointG(x: p.x * delegate, y: p.y * delegate) }
        }
        initialized = true
    }
}
