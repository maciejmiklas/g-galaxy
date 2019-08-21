package org.miklas.ggalaxy.core.formation

import groovy.transform.ToString
import org.miklas.ggalaxy.core.common.Point2D
import org.miklas.ggalaxy.core.path.BezierElement

@ToString(includeNames = true, includePackage = false)
class BazierBuilder {
    String key
    Point2D start
    List<BezierElement> elements = []
    List<SyncPointBuilder> syncPoints = []
    List<Integer> delays = []

    BazierBuilder(String key) {
        this.key = key
    }

    def start(int x, int y) {
        start = new Point2D(x, y)
        def cmd = [:]

        cmd.flyto = { int cp1x, int cp1y, int cp2x, int cp2y, int endx, int endy -> elements.add new BezierElement(cp1: [cp1x, cp1y], cp2: [cp2x, cp2y], end: [endx, endy]); cmd }

        cmd.delay = { int ms -> delays.add ms; cmd }
        cmd
    }
}
