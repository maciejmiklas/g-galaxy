package org.miklas.ggalaxy.core.formation

import groovy.transform.ToString
import org.codehaus.groovy.control.CompilerConfiguration
import org.miklas.ggalaxy.core.SpringConfig
import org.miklas.ggalaxy.core.path.BezierPathFollowing

@ToString(includeNames = true, includePackage = false)
class FormationBuilder {
    Map<String, ElementBuilder> elementBuilders = [:]
    List<String> formationKeys = []
    Binding binding;

    static void main(String... x) {
        SpringConfig.initMeta()
        def formation = new FormationBuilder('/formation_001.groovy').build()
        print "OUT: ${formation}"
    }

    Formation build() {
        Formation formation = []
        formation.paths << elementBuilders.values().collect {
            new BezierPathFollowing(it.bazier.start, *it.bazier.elements)
        }
        formation
    }

    FormationBuilder(String scriptPath) {
        binding = [parser: this]

        CompilerConfiguration config = []
        config.scriptBaseClass = FormationScript.name

        GroovyShell shell = [this.class.classLoader, binding, config]
        def script = FormationBuilder.getResource(scriptPath)
        shell.evaluate new File(script.toURI())
    }

    ElementBuilder element(String key) {
        def builder = new ElementBuilder(formationKey: key)
        elementBuilders.put key, builder
        builder
    }

    def formation(String... keys) {
        formationKeys.addAll keys
    }

    static abstract class FormationScript extends Script {
        @Delegate
        @Lazy
        FormationBuilder parser = binding.parser
    }
}
