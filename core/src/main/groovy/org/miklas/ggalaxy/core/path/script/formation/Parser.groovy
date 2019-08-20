package org.miklas.ggalaxy.core.path.script.formation

import groovy.transform.PackageScope
import org.codehaus.groovy.control.CompilerConfiguration
import org.miklas.ggalaxy.core.SpringConfig
import org.miklas.ggalaxy.core.common.Point2D

@PackageScope
class Parser {

    static void main(String... x) {
        println("GO")
        SpringConfig.initMeta()
        new Parser()
    }

    private Binding binding;
    private FormationBuilder builder;

    Parser() {
        binding = [parser: this]

        CompilerConfiguration config = []
        config.scriptBaseClass = FormationScript.name

        GroovyShell shell = [this.class.classLoader, binding, config]
        def script = Parser.getResource('/formation_001.groovy')
        shell.evaluate new File(script.toURI())

        print "OUT: $builder"
    }

    static Point2D xy(int x, int y) {
        [x, y]
    }

    def formation(String key) {
        builder = new FormationBuilder(formationKey: key)
        builder
    }

    static abstract class FormationScript extends Script {
        @Delegate
        @Lazy
        Parser parser = binding.parser
    }
}
