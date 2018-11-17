package org.miklas.ggalaxy.core.enemy

import groovy.transform.PackageScope

@PackageScope
interface EnemyFactory {

    NextEnemy next()

    class NextEnemy {
        DeployableAsset asset
        boolean newInstance
    }
}
