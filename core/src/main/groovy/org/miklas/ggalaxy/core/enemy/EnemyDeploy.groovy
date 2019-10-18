package org.miklas.ggalaxy.core.enemy

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import org.miklas.ggalaxy.core.common.AssetName
import org.miklas.ggalaxy.core.formation.Formation
import org.miklas.ggalaxy.core.formation.FormationBuilder
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
class EnemyDeploy extends Actor {

    long lastSpawnMs = 0;
    Formation formation;
    Fighter fighter

    @PostConstruct
    void init() {
        formation = new FormationBuilder('/formation_001.groovy').build()
        fighter = [AssetName.SHIP_FALCON, formation.paths[0][0]]
        fighter.deploy()
    }

    @Override
    void draw(Batch batch, float parentAlpha) {
        fighter.draw batch, parentAlpha
    }

    void spawn() {

    }
}
