package org.miklas.ggalaxy.core.path

import org.miklas.ggalaxy.core.common.Point

interface PathFollowing extends Iterator<Point> {
    void reset()
    int getCurrentElementIdx()
    Optional<Vector> getVector()
}