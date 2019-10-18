package org.miklas.ggalaxy.core.path

import org.miklas.ggalaxy.core.common.PointG
import org.miklas.ggalaxy.core.common.VectorG

interface PathFollowing extends Iterator<PointG> {
    void reset()

    int getCurrentElementIdx()

    VectorG getMoveDirection()
}