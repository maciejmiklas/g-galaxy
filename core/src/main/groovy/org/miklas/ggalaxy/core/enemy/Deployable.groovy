package org.miklas.ggalaxy.core.enemy

import groovy.transform.PackageScope

@PackageScope
interface Deployable {

    void deploy(int x, int y)
}