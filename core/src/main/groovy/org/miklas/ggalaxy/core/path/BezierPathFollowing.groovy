package org.miklas.ggalaxy.core.path

import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Point2D

class BezierPathFollowing implements PathFollowing {

    final List<BezierElement> path = new LinkedList<>()


    private final def conf
    private double ti, tv

    final Point2D startPoint = []

    private final Point2D currentPoint = []
    private BezierElement currentEl
    private int currentElIdx = 0

    BezierPathFollowing(Point2D start, BezierElement... elements) {
        conf = Conf.cfg.pathFollowing.bazier
        currentPoint.x = start.x
        currentPoint.y = start.y
        startPoint.x = start.x
        startPoint.y = start.y
        path.addAll elements
        currentEl = path.get 0
        nextElement()
    }

    boolean nextElement() {
        ti = 1 / currentPoint.distance(currentEl.end)
        tv = 0
    }

    boolean goCloser() {
        int distance = currentPoint.distance currentEl.end
        if (distance < conf.distanceMargin) {
            return false
        }

        currentPoint.x = (1 - tv).pow3 * startPoint.x + 3 * tv * (1 - tv).pow2 * currentEl.cp1.x + 3 * tv.pow2 * (1 - tv) * currentEl.cp2.x + tv.pow3 * currentEl.end.x
        currentPoint.y = (1 - tv).pow3 * startPoint.y + 3 * tv * (1 - tv).pow2 * currentEl.cp1.y + 3 * tv.pow2 * (1 - tv) * currentEl.cp2.y + tv.pow3 * currentEl.end.x
        tv += ti
        if (tv > 1) {
            return false
        }
        return true
    }

    @Override
    void reset() {
        currentPoint.x = startPoint.x
        currentPoint.y = startPoint.y
        currentElIdx = 0
    }

    @Override
    final Point2D next() {
        return currentPoint
    }

    @Override
    final boolean hasNext() {
        if (goCloser()) {
            return true
        }
        currentEl = path.get currentElIdx++
        if (currentElIdx == path.size()) {
            return false
        }
        startPoint.x = currentPoint.x
        startPoint.y = currentPoint.y
        nextElement()

        if (goCloser()) {
            return true
        }
        return false
    }

}
