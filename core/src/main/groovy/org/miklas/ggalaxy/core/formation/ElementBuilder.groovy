package org.miklas.ggalaxy.core.formation

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class ElementBuilder {

    BazierBuilder bazier
    String assetKey
    String formationKey

    def asset(String key) {
        assetKey = key

        def cmd = [:]
        cmd.bazier = { int x, int y -> setBazier(x, y) }
        return cmd
    }

    def setBazier(int x, int y) {
        assert bazier == null: "Bazier already set for formation: " + formationKey
        bazier = new BazierBuilder()
        return bazier.start(x, y)
    }

    SyncPointBuilder syncPoint(String key) {
        new SyncPointBuilder(key);
    }

}
