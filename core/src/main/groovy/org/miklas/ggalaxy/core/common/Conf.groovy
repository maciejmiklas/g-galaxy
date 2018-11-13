package org.miklas.ggalaxy.core.common

class Conf {
    // TODO set different environments dynamically
    static final ConfigObject ins = new ConfigSlurper("phone").parse(Conf.class.getResource("/config.groovy") as URL)

    // 0 - 1280 on x axis
    static final int SCR_WIDTH = ins.screen.width

    // 0 - 760 on y axis
    static final int SCR_HEIGHT = ins.screen.height

    static def animation(AssetName name) {
        Conf.ins.animation."$name"
    }

    static def cannonMain(AssetType type){
        Conf.ins.cannon.main."$type"
    }

    static def movement(AssetType type){
        Conf.ins.movement."$type"
    }

    static def sprite(AssetName type){
        Conf.ins.sprite."$type"
    }

}

