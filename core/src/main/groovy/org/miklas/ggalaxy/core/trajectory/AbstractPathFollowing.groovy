package org.miklas.ggalaxy.core.trajectory

import groovy.transform.PackageScope
import org.miklas.ggalaxy.core.common.Point2D

@PackageScope
abstract class AbstractPathFollowing implements PathFollowing {

    private final Point2D nextPoint = []
    final List<Point2D> path = new LinkedList<>()
    private boolean firstNext = true
    private int nodeIdx = 0

    @Override
    final void addNode(int x, int y) {
        path.add new Point2D(x: x, y: y)
    }

    final int distance(Point2D from, Point2D to) {
        Math.sqrt pow2(to.x - from.x) + pow2(to.y - from.y)
    }

    final int pow2(double val) {
        val * val
    }

    abstract boolean goCloser(Point2D currentPoint)

    abstract boolean nextNode(Point2D nextPoint, List<Point2D> path, int nodeIdx)

    abstract void initialize(Point2D nextPoint, List<Point2D> path)

    abstract int increaseNodes()

    @Override
    void reset() {
        nextPoint.reset()
        firstNext = true
        nodeIdx = 0
        initialize nextPoint, path
    }

    @Override
    final Point2D next() {
        return nextPoint
    }

    @Override
    final boolean hasNext() {
        if (firstNext) {
            if (2 * increaseNodes() >= path.size()) {
                return false
            }
            firstNext = false
            initialize nextPoint, path
            nodeIdx += increaseNodes()
        }
        if (goCloser(nextPoint)) {
            return true
        }
        if (nodeIdx + increaseNodes() >= path.size()) {
            return false
        }
        nodeIdx += increaseNodes()
        if (!nextNode(nextPoint, path, nodeIdx)) {
            return false
        }

        if (goCloser(nextPoint)) {
            return true
        }
        return false
    }

}
