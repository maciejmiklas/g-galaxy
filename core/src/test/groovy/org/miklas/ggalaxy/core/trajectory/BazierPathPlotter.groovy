package org.miklas.ggalaxy.core.trajectory

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.miklas.ggalaxy.core.common.Conf

import javax.swing.*
import java.awt.*
import java.awt.geom.Ellipse2D

SwingUtilities.invokeLater {
    def example = new JFrame("Trajectory Plotter") {
        void init() {
            XYSeriesCollection dataset = new XYSeriesCollection()

            BezierPathFollowing path = []
            path.addNode 100, 760
            path.addNode 200, 730
            path.addNode 400, 735
            path.addNode 500, 670
            path.addNode 900, 700


            XYSeries xy = new XYSeries("xy")
            xy.add 1280, 760
            dataset.addSeries(xy)

            XYSeries points = new XYSeries("Points")
            path.path.each { points.add(it.x, it.y) }
            dataset.addSeries(points)

            XYSeries trajectory = new XYSeries("Trajectory")
            path.each { trajectory.add(it.x, it.y) }
            dataset.addSeries(trajectory)

            JFreeChart chart = ChartFactory.createScatterPlot(
                    "Bazier Path",
                    "X", "Y", dataset, PlotOrientation.VERTICAL, true, false, false)

            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(new Color(0, 55, 80))
            def renderer = plot.getRenderer(0)
            renderer.setSeriesShape(0, new Ellipse2D.Double(0, 0, 10, 10))
            renderer.setSeriesShape(1, new Ellipse2D.Double(0, 0, 2, 2))
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