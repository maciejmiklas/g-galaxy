package org.miklas.ggalaxy.core.enemy

import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.Asset

@PackageScope
interface Deployable extends Asset {

    void deploy(int x, int y)
}