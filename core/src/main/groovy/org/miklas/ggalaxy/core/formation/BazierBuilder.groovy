package org.miklas.ggalaxy.core.formation

import groovy.transform.ToString
import org.miklas.ggalaxy.core.common.Point
import org.miklas.ggalaxy.core.path.BezierElement

@ToString(includeNames = true, includePackage = false)
class BazierBuilder {
    Point start
    List<BezierElement> elements = []
    List<SyncPointBuilder> syncPoints = []
    List<Integer> delays = []

    def start(int sx, int sy) {
        start = new Point(sx, sy)
        def cmd = [:]

        cmd.flyto = { int cp1x, int cp1y, int cp2x, int cp2y, int endx, int endy -> elements.add new BezierElement(cp1: [cp1x, cp1y], cp2: [cp2x, cp2y], end: [endx, endy]); cmd }

        cmd.delay = { int ms -> delays.add ms; cmd }

        return cmd
    }
}
