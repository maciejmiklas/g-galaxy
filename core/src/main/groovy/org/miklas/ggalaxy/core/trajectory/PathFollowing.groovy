package org.miklas.ggalaxy.core.trajectory

import org.miklas.ggalaxy.core.common.Point2D

interface PathFollowing extends Iterator<Point2D> {

    void addNode(int x, int y)

    void reset()
}