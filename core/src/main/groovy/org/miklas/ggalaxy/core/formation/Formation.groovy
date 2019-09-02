package org.miklas.ggalaxy.core.formation

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import groovy.transform.ImmutableBase
import groovy.transform.ToString
import org.miklas.ggalaxy.core.path.PathFollowing

@ImmutableBase
@ToString(includeNames = true, includePackage = false)
class Formation extends Actor {

    List<PathFollowing> paths = []

    boolean active = false

    @Override
    void draw(Batch batch, float parentAlpha) {
        if (!active) {
            init()
        }
    }

    void init() {

    }

}
