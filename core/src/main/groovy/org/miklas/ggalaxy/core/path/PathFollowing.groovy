package org.miklas.ggalaxy.core.path

import org.miklas.ggalaxy.core.common.Point2D

interface PathFollowing extends Iterator<Point2D> {

    void reset()
}