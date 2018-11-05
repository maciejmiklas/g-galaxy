package org.miklas.ggalaxy.core

class Conf {
    // TODO set different environments dynamically
    static final ConfigObject ins = new ConfigSlurper("phone").parse(Conf.class.getResource("/config.groovy") as URL)

    static final int SCR_WIDTH = ins.screen.width
    static final int SCR_HEIGHT = ins.screen.height
}

