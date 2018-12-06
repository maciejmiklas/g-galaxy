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

    static def cannonMain(AssetName name) {
        cfg.cannon.main."$name"
    }

    static def movement(AssetName name) {
        cfg.movement."$name"
    }

    static def sprite(AssetName type) {
        cfg.sprite."$type"
    }

    static def enemyAsset(AssetName name) {
        cfg.enemy.asset."$name"
    }

    static def spaceShip() {
        cfg.spaceShip
    }
}