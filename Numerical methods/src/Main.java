import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import javax.swing.JFrame;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double[] e = {1e-2, 1e-4, 1e-7, 1e-17}; // параметры точности
        targetFunction function = new targetFunction(); // Создание целевой функции

        // Создаем объект ChartBuilder
        ChartBuilder chartBuilder = new ChartBuilder();

        for (int i = 0; i < e.length; i++) {
            double E = e[i];

            /*(МЕТОД ДИХОТОМИИ)*/

            //Получаем список серий данных для текущей точности с уникальным индексом
            List<XYSeries> seriesListDichotomy = dichotomyMethod.performDichotomy(0, 1, E, function, i);

            // Создаем набор данных для текущего графика
            XYSeriesCollection datasetDechotomy = new XYSeriesCollection();
            for (XYSeries series : seriesListDichotomy) {
                datasetDechotomy.addSeries(series);
            }

            // Создание и отображение графика для текущей точности
            JFreeChart chartDichotomy = chartBuilder.createChart(
                    "МЕТОД ДИХОТОМИИ\nГрафик изменения интервала неопределенности (E = " + E + ")",
                    seriesListDichotomy
            );
            chartBuilder.displayChart(chartDichotomy);

            /* (МЕТОД ЗОЛОТОГО СЕЧЕНИЯ */

            // Получаем список серий данных для текущей точности с уникальным индексом
            List<XYSeries> seriesListGoldenSection = GoldenSection.goldenSectionSearch(0, 1, E, function, i);

            // Создаем набор данных для текущего графика
            XYSeriesCollection datasetGoldenSection = new XYSeriesCollection();
            for (XYSeries series : seriesListGoldenSection) {
                datasetGoldenSection.addSeries(series);
            }

            // Создание и отображение графика для текущей точности
            JFreeChart chartGoldenSection = chartBuilder.createChart(
                    "МЕТОД ЗОЛОТОГО СЕЧЕНИЯ\nГрафик изменения интервала неопределенности (E = " + E + ")",
                    seriesListGoldenSection
            );
            chartBuilder.displayChart(chartGoldenSection);

            /*МЕТОД ПОСЛЕДОВАТЕЛЬНОЙ КВАДРАТИЧНОЙ АППРОКСИМАЦИИ*/

            //Получаем список серий данных для текущей точности с уникальным индексом
            List<XYSeries> seriesListQuadApp = QuadApproximation.quadApproximation(0, 1, E, function, i);

            // Создаем набор данных для текущего графика
            XYSeriesCollection datasetQuadApp = new XYSeriesCollection();
            for (XYSeries series : seriesListQuadApp) {
                datasetQuadApp.addSeries(series);
            }

            // Создание и отображение графика для текущей точности
            JFreeChart chartQuadApp = chartBuilder.createChart(
                    "МЕТОД ПОСЛЕДОВАТЕЛЬНОЙ КВАДРАТИЧНОЙ АППРОКСИМАЦИИ\nГрафик изменения интервала неопределенности (E = " + E + ")",
                    seriesListQuadApp
            );
            chartBuilder.displayChart(chartQuadApp);

        }

        // Построение графика целевой функции
        XYSeries targetSeries = new XYSeries("Target Function");
        for (double x = 0; x <= 1; x += 0.01) {
            targetSeries.add(x, targetFunction.targetFunc(x));  // Вызов функции из другого файла
        }
        // Создание графика целевой функции
        JFreeChart targetChart = chartBuilder.createSingleSeriesChart(
                "Target Function",
                targetSeries
        );

        // Отображение графика целевой функции
        chartBuilder.displayChart(targetChart);
    }
}


