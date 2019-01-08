package org.miklas.ggalaxy.core.trajectory


import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Point2D

class BezierPathFollowing extends AbstractPathFollowing {

    private static final MIN_POINTS = 3
    private final def conf
    private double ti, tv
    private Point2D goTo

    BezierPathFollowing() {
        conf = Conf.cfg.pathFollowing.bazier
    }

    @Override
    boolean nextNode(Point2D nextPoint, List<Point2D> path, int nodeIdx) {
        Point2D p0 = path nodeIdx
        Point2D p1 = path nodeIdx
        Point2D p2 = path nodeIdx
        ti = 1 / Math.abs(p0.x - p2.x)
        tv = 0
        goTo = p2
        return true
    }

    @Override
    boolean goCloser(Point2D nextPoint) {
        int distance = distance nextPoint, goTo
        if (distance < conf.distanceMargin) {
            return false
        }

        return true
    }

    @Override
    void initialize(Point2D nextPoint, List<Point2D> path) {

    }

    @Override
    int increaseNodes() {
        return MIN_POINTS
    }

    @Override
    void reset() {
        super.reset()
    }
}
