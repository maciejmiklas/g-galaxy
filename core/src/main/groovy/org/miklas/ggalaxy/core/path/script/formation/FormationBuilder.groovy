package org.miklas.ggalaxy.core.path.script.formation

import groovy.transform.PackageScope
import groovy.transform.ToString

@PackageScope
@ToString(includeNames = true, includePackage = false)
class FormationBuilder {

    List<BazierBuilder> baziers = []
    String assetKey;
    String formationKey

    def asset(String key) {
        assert formationKey != null: 'Define formation first, e.g.: formation \'F1\' '
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
