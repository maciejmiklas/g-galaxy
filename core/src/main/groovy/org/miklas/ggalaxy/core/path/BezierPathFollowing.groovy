package org.miklas.ggalaxy.core.path

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.common.PointG
import org.miklas.ggalaxy.core.common.VectorG

@Slf4j
@ToString(includeNames = true, includePackage = false)
class BezierPathFollowing implements PathFollowing {

    final List<BezierElement> path = new LinkedList<>()

    final def conf
    double ti, tv

    final PointG start
    final PointG currentStart = []
    final PointG currentPos = []
    BezierElement currentEl
    int currentElIdx = -1
    VectorG moveDirection = []

    BezierPathFollowing(PointG start, BezierElement... elements) {
        conf = Conf.cfg.pathFollowing.bazier
        currentPos << start
        path.addAll elements
        this.start = start
        nextElement()
    }

    boolean nextElement() {
        currentElIdx++
        if (currentElIdx >= path.size()) {
           // sleep(300000)
            return false
        }
        currentStart << currentPos
        currentEl = path.get currentElIdx
        ti = 1 / currentStart.distance(currentEl.end) * conf.density
        tv = 0
        return true
    }

    @Override
    VectorG getMoveDirection() {
        PointG startPos = currentPos.clone()
        double startTv = tv

        VectorG vector = [startPos, currentPos]
        while (vector.length < conf.directionVectorLength && goCloser());

        if (vector.length >= conf.minDirectionVectorLength) {
            moveDirection << vector
        }

        println ">>> ${moveDirection}"
        tv = startTv
        currentPos << startPos
        return moveDirection
    }

    boolean goCloser() {
        sleep(10)
        int distance = currentPos.distance currentEl.end
        if (distance < conf.distanceMargin) {
            return false
        }

        // P = (1−t)ˆ3*P1 + 3(1−t)ˆ2*t*P2 +3*(1−t)*t^2* P3 + t^3*P4
        currentPos << (1 - tv).pow3 * currentStart + 3 * (1 - tv).pow2 * tv * currentEl.cp1 + 3 * (1 - tv) *
                tv.pow2 * currentEl.cp2 + tv.pow3 * currentEl.end
        tv += ti
        if (tv > 1) {
            log.debug("tv({}) > 1 - break Bazier Path", tv)
            return false
        }
        return true
    }

    @Override
    void reset() {
        currentPos << start
        currentElIdx = -1
    }

    @Override
    int getCurrentElementIdx() {
        currentElIdx
    }

    @Override
    final PointG next() {
        currentPos
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
