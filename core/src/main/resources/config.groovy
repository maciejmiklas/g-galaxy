animation {
    MAIN_SHIP_BLUE {
        path = 'assets/packs/Spaceship_art_pack_larger/Blue/Animation/'
        frames = 8
        imageWidth = 512
        imageHeight = 512
        spriteWith = 64
        spriteHeight = 64
    }
}

screen {
    width = 1280
    height = 760
}

background {
    layerSpeedDiference = 2
    speed {
        up {
            normal = 5
            boost = 10
        }
        down {
            normal = 3
            boost = -5
        }

        nokey {
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