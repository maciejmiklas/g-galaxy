package org.miklas.ggalaxy.core.formation

import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class FormationBuilder {
    Map<String, ElementBuilder> elementBuilders = [:]
    List<String> formationKeys = []

    ElementBuilder element(String key) {
        def builder = new ElementBuilder(formationKey: key)
        elementBuilders.put key, builder
        builder
    }

    def formation(String ... keys){
        formationKeys.addAll keys
    }

}
