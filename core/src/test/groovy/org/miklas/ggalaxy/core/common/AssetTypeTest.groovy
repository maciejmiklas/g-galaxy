package org.miklas.ggalaxy.core.common;

import spock.lang.Specification;

class AssetTypeTest extends Specification {

    def "test isSchot - negative"() {
        when:
        AssetType.SHOT_FIGHTER

        then:
        !AssetType.SPACE_SHIP.isSchot()
        AssetType.SHOT_FIGHTER.isSchot()
        AssetType.SHOT_SPACE_SHIP.isSchot()
    }
}

