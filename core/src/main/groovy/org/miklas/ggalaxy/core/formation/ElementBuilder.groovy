package org.miklas.ggalaxy.core.formation

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class ElementBuilder {

    List<BazierBuilder> baziers = []
    String assetKey;
    String formationKey

    def asset(String key) {
        assetKey = key

        def bazier = new BazierBuilder();
        baziers.add bazier
        def cmd = [:]
        cmd.bazier = { int x, int y -> bazier.start(x, y) }
        cmd
    }

    SyncPointBuilder syncPoint(String key) {
        new SyncPointBuilder(key);
    }

}
