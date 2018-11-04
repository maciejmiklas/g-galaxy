animation {
    MAIN_SHIP_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Animation/'
        frames = 8
        imageWidth = 512
        imageHeight = 512
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.05f
    }

    SPACE_BOMB_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacebombs/'
        frames = 3
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.2f
    }

    SPACE_MINE_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.5f
    }

    SPACE_MINE_RED {
        path = 'assets/packs/Spaceship_art_pack_larger/Red/Spacemines/'
        frames = 2
        imageWidth = 256
        imageHeight = 256
        spriteWith = 64
        spriteHeight = 64
        frameDuration = 0.05f
    }
}

screen {
    width = 1280
    height = 760
}

move {
    speed = 200
    boost = 100
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