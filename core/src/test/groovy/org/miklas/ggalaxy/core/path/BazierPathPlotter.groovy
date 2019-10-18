package org.miklas.ggalaxy.core.path

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.miklas.ggalaxy.core.SpringConfig
import org.miklas.ggalaxy.core.common.Conf
import org.miklas.ggalaxy.core.formation.Formation
import org.miklas.ggalaxy.core.formation.FormationBuilder

import javax.swing.*
import java.awt.*
import java.awt.geom.Ellipse2D

SwingUtilities.invokeLater {
    def example = new JFrame("Trajectory Plotter") {
        void init() {
            SpringConfig.initMeta()
            XYSeriesCollection dataset = new XYSeriesCollection()

            Formation formation = new FormationBuilder('/formation_001.groovy').build()
            BezierPathFollowing path = formation.paths[0][0]
            /*
            BezierPathFollowing path = new BezierPathFollowing(
                    new Point2D(116, 94),
                    new BezierElement(cp1: [177, 18], cp2: [299, 136], end: [148, 437]),
                    new BezierElement(cp1: [77, 578], cp2: [625, 722], end: [534, 546]),
                    new BezierElement(cp1: [473, 427], cp2: [311, 128], end: [982, 193])
            )*/

            XYSeries xy = new XYSeries("xy")
            xy.add 1280, 760
            dataset.addSeries(xy)

            XYSeries points = new XYSeries("Points")
            points.add(path.currentStart.x, path.currentStart.y)
            path.path.each { points.add(it.end.x, it.end.y) }
            dataset.addSeries(points)

            XYSeries cp = new XYSeries("CP")
            path.path.each { cp.add(it.cp1.x, it.cp1.y) }
            path.path.each { cp.add(it.cp2.x, it.cp2.y) }
            dataset.addSeries(cp)

            XYSeries trajectory = new XYSeries("Path")
            path.each { trajectory.add(it.x, it.y) }
            dataset.addSeries(trajectory)

            JFreeChart chart = ChartFactory.createScatterPlot(
                    "Bazier Path",
                    "X", "Y", dataset, PlotOrientation.VERTICAL, true, false, false)

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(new Color(0, 55, 80))
            def renderer = plot.getRenderer(0)
            renderer.setSeriesShape(0, new Ellipse2D.Double(0, 0, 10, 10))
            renderer.setSeriesShape(1, new Ellipse2D.Double(0, 0, 10, 10))
            renderer.setSeriesShape(2, new Ellipse2D.Double(0, 0, 10, 10))
            renderer.setSeriesShape(3, new Ellipse2D.Double(0, 0, 1, 1))

            ChartPanel panel = new ChartPanel(chart)
            setContentPane(panel)
        }
    }
    example.init()
    example.setSize(Conf.SCR_WIDTH, Conf.SCR_HEIGHT)
    example.setLocationRelativeTo(null)
    example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    example.setVisible(true)
}