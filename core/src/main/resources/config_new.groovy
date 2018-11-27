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
        explosion = EXPLOSION_BLUE
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
        explosion = EXPLOSION_BLUE
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
        explosion = EXPLOSION_GALAXY
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
        explosion = EXPLOSION_GALAXY
    }

    BOMB_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacebombs/'
        frames = 3
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.2f
        explosion = EXPLOSION_BLUE
    }

    MINE_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.5f
        explosion = EXPLOSION_BLUE
    }

    MINE_RED {
        path = 'assets/packs/Spaceship_art_pack_larger/Red/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.05f
        explosion = EXPLOSION_RED
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
        explosion = EXPLOSION_RED
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
        SHIP_CARGO              { moveSpeed = 400 minDelayMs = 1000 maxDelayMs = 2000 }
        SHIP_INTERCEPTOR_BLUE   { moveSpeed = 400 minDelayMs = 1000 maxDelayMs = 2000 }
        SHIP_INTERCEPTOR_RED    { moveSpeed = 400 minDelayMs = 1000 maxDelayMs = 2000 }
        SHIP_FALCON             { moveSpeed = 400 minDelayMs = 1000 maxDelayMs = 2000 }
    }
}

enemyDeploy {
    assets = [
            SHIP_CARGO           : Fighter,
            SHIP_INTERCEPTOR_BLUE: Fighter,
            SHIP_INTERCEPTOR_RED : Fighter,
            SHIP_FALCON          : Fighter,
            BOMB_BLUE            : Asteroid,
            MINE_BLUE            : Asteroid,
            MINE_RED             : Asteroid
    ]
    amount {
        SHIP_CARGO              { min = 0 max = 5 }
        SHIP_INTERCEPTOR_BLUE   { min = 0 max = 3 }
        SHIP_INTERCEPTOR_RED    { min = 0 max = 3 }
        SHIP_FALCON             { min = 0 max = 4 }
        BOMB_BLUE               { min = 1 max = 3 }
        MINE_BLUE               { min = 1 max = 4 }
        MINE_RED                { min = 1 max = 5 }

    }
    spawnMs = 500
    widthMargin = 10
    deployMargin = 50
}

movement {
    SHIP_CARGO              { speed = 100 }
    SHIP_INTERCEPTOR_BLUE   { speed = 200 }
    SHIP_INTERCEPTOR_RED    { speed = 250 }
    SHIP_FALCON             { speed = 300 }
    BOMB_BLUE               { speed = 100 }
    MINE_BLUE               { speed = 100 }
    MINE_RED                { speed = 100 }
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
        UP      { normal = 5    boost = 10 }
        DOWN    { normal = 3    boost = -5 }
        LEFT    { normal = 2    boost = 6 }
        RIGHT   { normal = -2   boost = -6 }
        NONE    { y = 4 x = 0 }
    }
}

environments {
    phone {}
    pc {}
    gpd {}
}