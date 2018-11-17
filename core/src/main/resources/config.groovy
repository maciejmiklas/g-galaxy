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
    }

    BOMB_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacebombs/'
        frames = 3
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.2f
    }

    MINE_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.5f
    }

    MINE_RED {
        path = 'assets/packs/Spaceship_art_pack_larger/Red/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.05f
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

cannon {
    main {
        SPACE_SHIP {
            moveSpeed = 400
            delayMs = 500
        }
        ENEMY_SHIP {
            moveSpeed = 400
            maxDelayMs = 50000
            minDelayMs = 50000
        }
    }
}

fighter {
    CLASS_A {
        ships = []
    }
}

enemyDeploy {
    spawnMs = 500
    widthMargin = 10
    deployMargin = 50
}

movement {
    ENEMY_SHIP {
        moveSpeed = 100
    }
    ASTEROID {
        moveSpeed = 200
    }
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