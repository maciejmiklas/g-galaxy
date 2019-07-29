package org.miklas.ggalaxy.core.path

import groovy.util.logging.Slf4j
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Point2D

@Slf4j
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
        path.addAll elements

        nextElement()
    }

    boolean nextElement() {
        if (currentElIdx == path.size()) {
            return false
        }
        startPoint.x = currentPoint.x
        startPoint.y = currentPoint.y
        currentEl = path.get currentElIdx++
        ti = 1 / startPoint.distance(currentEl.end) * conf.skippPixels
        tv = 0
        true
    }

    boolean goCloser() {
        int distance = currentPoint.distance currentEl.end
        if (distance < conf.distanceMargin) {
            return false
        }

        // P = (1−t)ˆ3*P1 + 3(1−t)ˆ2*t*P2 +3*(1−t)*t^2* P3 + t^3*P4
        currentPoint.x = (1 - tv).pow3 * startPoint.x + 3 * (1 - tv).pow2 * tv * currentEl.cp1.x + 3 * (1 - tv) * tv.pow2 * currentEl.cp2.x + tv.pow3 * currentEl.end.x
        currentPoint.y = (1 - tv).pow3 * startPoint.y + 3 * (1 - tv).pow2 * tv * currentEl.cp1.y + 3 * (1 - tv) * tv.pow2 * currentEl.cp2.y + tv.pow3 * currentEl.end.y
        tv += ti
        if (tv > 1) {
            log.debug("t > 1 - break Bazier Path")
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

        if (!nextElement()) {
            return false
        }

        if (goCloser()) {
            return true
        }
        return false
    }

}
