package org.miklas.ggalaxy.core.formation


import org.codehaus.groovy.control.CompilerConfiguration
import org.miklas.ggalaxy.core.SpringConfig
import org.miklas.ggalaxy.core.common.Point2D

class FormationParser {

    static void main(String... x) {
        SpringConfig.initMeta()
        def parser = new FormationParser('/formation_001.groovy')
        print "OUT: ${parser.formationBuilder}"
    }

    private Binding binding;
    FormationBuilder formationBuilder = [];

    FormationParser(String scriptPath) {
        binding = [parser: this]

        CompilerConfiguration config = []
        config.scriptBaseClass = FormationScript.name

        GroovyShell shell = [this.class.classLoader, binding, config]
        def script = FormationParser.getResource(scriptPath)
        shell.evaluate new File(script.toURI())
    }

    static Point2D xy(int x, int y) {
        [x, y]
    }

    def element(String key) {
        formationBuilder.element key
    }

    def formation(String... keys) {
        formationBuilder.formation keys
    }

    static abstract class FormationScript extends Script {
        @Delegate
        @Lazy
        FormationParser parser = binding.parser
    }
}
