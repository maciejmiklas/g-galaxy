package org.miklas.ggalaxy.core.common

class Conf {
    // TODO set different environments dynamically
    static final ConfigObject cfg = new ConfigSlurper("phone").parse(Conf.class.getResource("/config.groovy") as URL)

    // 0 - 1280 on x axis
    static final int SCR_WIDTH = cfg.screen.width

    // 0 - 760 on y axis
    static final int SCR_HEIGHT = cfg.screen.height

    static def animation(AssetName name) {
        cfg.animation."$name"
    }

    static def cannonMain(AssetType type) {
        cfg.cannon.main."$type"
    }

    static def movement(AssetType type) {
        cfg.movement."$type"
    }

    static def sprite(AssetName type) {
        cfg.sprite."$type"
    }

    static def enemyDeploy() {
        cfg.enemyDeploy
    }

}

