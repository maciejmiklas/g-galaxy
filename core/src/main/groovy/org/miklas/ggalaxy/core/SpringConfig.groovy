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
        Stage.metaClass.leftShift = { delegate.addActor it; delegate }
    }
}
