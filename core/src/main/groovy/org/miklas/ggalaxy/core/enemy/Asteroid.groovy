package org.miklas.ggalaxy.core.enemy

import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.common.AssetType
import org.miklas.ggalaxy.core.path.PathFollowing

@PackageScope
class Asteroid extends Enemy {

    final AssetType type = AssetType.ASTEROID

    Asteroid(AssetName assetName, PathFollowing pathFollowing) {
        super(assetName, pathFollowing)
    }
}
