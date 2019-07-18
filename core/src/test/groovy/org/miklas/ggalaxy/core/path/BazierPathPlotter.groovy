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
import org.miklas.ggalaxy.core.common.Point2D

import javax.swing.*
import java.awt.*
import java.awt.geom.Ellipse2D

SwingUtilities.invokeLater {
    def example = new JFrame("Trajectory Plotter") {
        void init() {
            SpringConfig.initMeta()
            XYSeriesCollection dataset = new XYSeriesCollection()

            BezierPathFollowing path = new BezierPathFollowing(new Point2D(50, 50), new BezierElement(cp1: [1200, 100], cp2: [200, 600], end: [600, 600]))

            XYSeries xy = new XYSeries("xy")
            xy.add 1280, 760
            dataset.addSeries(xy)

            XYSeries points = new XYSeries("Points")
            points.add(path.startPoint.x, path.startPoint.y)
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