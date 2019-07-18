package org.miklas.ggalaxy.core

import com.badlogic.gdx.scenes.scene2d.Stage
import groovy.transform.PackageScope
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

@Configuration
@ComponentScan('org.miklas')
@PackageScope
class SpringConfig {

    @PostConstruct
    void init() {
        initMeta()
    }

    static void initMeta() {
        Stage.metaClass.leftShift = { delegate.addActor it; delegate }
        Number.metaClass {
            getPow2 = { delegate * delegate }
            getPow3 = { delegate * delegate * delegate }
        }

    }
}
