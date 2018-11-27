package org.miklas.ggalaxy.core


import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.Conf
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@PackageScope
class Game extends com.badlogic.gdx.Game {

    private ApplicationContext ctx

    @Override
    void create() {
        ctx = new AnnotationConfigApplicationContext(SpringConfig.class)
        println "Loaded config: $Conf.cfg"
        setScreen ctx.getBean(GameScreen.class)
    }

}
