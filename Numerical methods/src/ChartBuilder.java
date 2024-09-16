import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import javax.swing.JFrame;
import java.awt.Color;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.XYPlot;
import java.util.List;

public class ChartBuilder {

    public JFreeChart createChart(String title, List<XYSeries> seriesList) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (XYSeries series : seriesList) {
            dataset.addSeries(series);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "X",
                "Iterations",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        // Настройка рендерера
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for (int i = 0; i < seriesList.size(); i++) {
            renderer.setSeriesPaint(i, Color.RED); // Устанавливаем красный цвет для каждой серии
            renderer.setSeriesShapesVisible(i, false); // Отключаем отображение точек
        }

        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(renderer);

        return chart;
    }

    public JFreeChart createSingleSeriesChart(String title, XYSeries series) {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        return chart;
    }

    public void displayChart(JFreeChart chart) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}

