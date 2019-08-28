package org.miklas.ggalaxy.core.path

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.Point2D

@Slf4j
@ToString(includeNames = true, includePackage = false)
class BezierPathFollowing implements PathFollowing {

    final List<BezierElement> path = new LinkedList<>()

    final def conf
    double ti, tv

    final Point2D startPoint = []
    final Point2D currentPoint = []
    BezierElement currentEl
    int currentElIdx = -1

    BezierPathFollowing(Point2D start, BezierElement... elements) {
        conf = Conf.cfg.pathFollowing.bazier
        currentPoint << start
        path.addAll elements

        nextElement()
    }

    boolean nextElement() {
        currentElIdx++
        if (currentElIdx == path.size()) {
            return false
        }
        startPoint << currentPoint
        currentEl = path.get currentElIdx
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
        currentPoint << (1 - tv).pow3 * startPoint + 3 * (1 - tv).pow2 * tv * currentEl.cp1 + 3 * (1 - tv) * tv.pow2 * currentEl.cp2 + tv.pow3 * currentEl.end
        tv += ti
        if (tv > 1) {
            log.debug("t > 1 - break Bazier Path")
            return false
        }
        return true
    }

    @Override
    void reset() {
        currentPoint << startPoint
        currentElIdx = -1
    }

    @Override
    int getCurrentElementIdx() {
        currentElIdx
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
