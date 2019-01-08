package org.miklas.ggalaxy.core.trajectory


import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Point2D

class LinearPathFollowing extends AbstractPathFollowing {

    private final Point2D goTo = []
    private final def conf
    private double a, b

    LinearPathFollowing() {
        conf = Conf.cfg.pathFollowing.linear
    }

    @Override
    void initialize(Point2D nextPoint, List<Point2D> path) {
        Point2D point0 = path.get 0
        nextPoint.x = point0.x
        nextPoint.y = point0.y

        Point2D point1 = path.get 1
        goTo.x = point1.x
        goTo.y = point1.y
        updateAB(nextPoint)
    }

    @Override
    int increaseNodes() {
        return 1
    }

    @Override
    boolean nextNode(Point2D nextPoint, List<Point2D> path, int nodeIdx) {
        def to = path.get nodeIdx
        goTo.x = to.x
        goTo.y = to.y
        updateAB(nextPoint)
        return true
    }

    @Override
    boolean goCloser(Point2D nextPoint) {
        int distance = distance nextPoint, goTo
        if (distance < conf.distanceMargin) {
            return false
        }
        if (nextPoint.x > goTo.x) {
            nextPoint.x--
        } else {
            nextPoint.x++
        }
        nextPoint.y = a * nextPoint.x + b
        return true
    }

    private void updateAB(Point2D nextPoint) {
        a = (nextPoint.y - goTo.y) / (nextPoint.x - goTo.x)
        b = goTo.y - a * goTo.x
    }

}
