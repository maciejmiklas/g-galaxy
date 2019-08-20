package org.miklas.ggalaxy.core.path.script.formation

import groovy.transform.PackageScope
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@PackageScope
@TupleConstructor
@ToString(includeNames = true, includePackage = false)
class SyncPointBuilder {

    String key;
    int ms;

    void wait(int ms) {
        this.ms = ms
    }

}
