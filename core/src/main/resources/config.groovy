animation {
    SHIP_CARGO {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Animation/'
        frames = 8
        imageWidth = 512
        imageHeight = 512
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.05f
        cannon {
            main {
                position {
                    x = 30
                    y = 60
                }
            }
        }
        explosion = 'EXPLOSION_BLUE'
    }

    SHIP_INTERCEPTOR_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/spaceship_blue_animation'
        frames = 4
        imageWidth = 512
        imageHeight = 512
        spriteWith = 96
        spriteHeight = 96
        frameDuration = 0.05f
        cannon {
            main {
                position {
                    x = 30
                    y = 60
                }
            }
        }
        explosion = 'EXPLOSION_BLUE'
    }

    SHIP_INTERCEPTOR_RED {
        path = 'assets/packs/Spaceship_art_pack_larger/Red/Enemy_animation/'
        frames = 8
        imageWidth = 512
        imageHeight = 512
        spriteWith = 96
        spriteHeight = 96
        frameDuration = 0.05f
        cannon {
            main {
                position {
                    x = 30
                    y = 60
                }
            }
        }
        explosion = 'EXPLOSION_GALAXY'
    }

    SHIP_FALCON {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Small_ship_blue'
        frames = 5
        imageWidth = 640
        imageHeight = 640
        spriteWith = 96
        spriteHeight = 96
        frameDuration = 0.05f
        cannon {
            main {
                position {
                    x = 30
                    y = 60
                }
            }
        }
        explosion = 'EXPLOSION_GALAXY'
    }

    BOMB_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacebombs/'
        frames = 3
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.2f
        explosion = 'EXPLOSION_BLUE'
    }

    MINE_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.5f
        explosion = 'EXPLOSION_BLUE'
    }

    MINE_RED {
        path = 'assets/packs/Spaceship_art_pack_larger/Red/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.05f
        explosion = 'EXPLOSION_RED'
    }

    PROTON_STAR {
        path = 'assets/packs/Spaceship_art_pack_larger/Effects/Proton Star'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.2f
        prefix = 'p_Sprite_'
        explosion = 'EXPLOSION_RED'
    }

    EXPLOSION_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Effects/Blue Effects'
        frames = 16
        imageWidth = 256
        imageHeight = 256
        spriteWith = 256
        spriteHeight = 256
        frameDuration = 0.05f
        prefix = '1_'
    }

    EXPLOSION_RED {
        path = 'assets/packs/Spaceship_art_pack_larger/Effects/Red Explosion'
        frames = 16
        imageWidth = 256
        imageHeight = 256
        spriteWith = 256
        spriteHeight = 256
        frameDuration = 0.05f
        prefix = '1_'
    }

    EXPLOSION_GALAXY {
        path = 'assets/packs/Spaceship_art_pack_larger/Effects/Galaxy'
        frames = 16
        imageWidth = 256
        imageHeight = 256
        spriteWith = 256
        spriteHeight = 256
        frameDuration = 0.05f
        prefix = 'galaxy_'
    }
}

sprite {
    SHOT_RED {
        path = 'assets/packs/Spaceship_art_pack_larger/Red/bullet_red.png'
        imageWidth = 128
        imageHeight = 128
        spriteWith = 32
        spriteHeight = 32
    }
}

spaceShip {
    cannon {
        main {
            moveSpeed = 400
            delayMs = 500
        }
    }
}

cannon {
    main {
        SHIP_CARGO {
            shotSpeed = 400
            minDelayMs = 1000000
            maxDelayMs = 2000000
        }
        SHIP_INTERCEPTOR_BLUE {
            shotSpeed = 400
            minDelayMs = 1000000
            maxDelayMs = 2000000
        }
        SHIP_INTERCEPTOR_RED {
            shotSpeed = 400
            minDelayMs = 1000000
            maxDelayMs = 2000000
        }
        SHIP_FALCON {
            shotSpeed = 400
            minDelayMs = 1000000
            maxDelayMs = 2000000
        }
    }
}

enemy {
    asset {
        SHIP_CARGO {
            clazz = 'org.miklas.ggalaxy.core.enemy.Fighter'
            max = 8
            modeSpeed = 100
        }
        SHIP_INTERCEPTOR_BLUE {
            clazz = 'org.miklas.ggalaxy.core.enemy.Fighter'
            max = 3
            modeSpeed = 200
        }
        SHIP_INTERCEPTOR_RED {
            clazz = 'org.miklas.ggalaxy.core.enemy.Fighter'
            max = 5
            modeSpeed = 300
        }
        SHIP_FALCON {
            clazz = 'org.miklas.ggalaxy.core.enemy.Fighter'
            max = 4
            modeSpeed = 400
        }
        BOMB_BLUE {
            clazz = 'org.miklas.ggalaxy.core.enemy.Asteroid'
            max = 3
            modeSpeed = 50
        }
        MINE_BLUE {
            clazz = 'org.miklas.ggalaxy.core.enemy.Asteroid'
            max = 4
            modeSpeed = 100
        }
        MINE_RED {
            clazz = 'org.miklas.ggalaxy.core.enemy.Asteroid'
            max = 5
            modeSpeed = 150
        }
    }
    spawnMs = 200
    marginWidth = 10
    deployDistance = 50
    shuffleEnemiesMs = 60000
}

screen {
    width = 1280
    height = 760
}

key {
    moveSpeed {
        normal = 200
        boost = 300
    }
}

background {
    layerSpeedDiference = 2
    speed {
        UP {
            normal = 5
            boost = 10
        }
        DOWN {
            normal = 3
            boost = -5
        }
        LEFT {
            normal = 2
            boost = 6
        }
        RIGHT {
            normal = -2
            boost = -6
        }
        NONE {
            y = 4
            x = 0
        }
    }
}

environments {
    phone {}
    pc {}
    gpd {}
}