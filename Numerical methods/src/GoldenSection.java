import org.jfree.data.xy.XYSeries;
import java.util.ArrayList;
import java.util.List;
public class GoldenSection {
    private static final double PHI = (1 + Math.sqrt(5)) / 2; // Золотое сечение

    public static List<XYSeries> goldenSectionSearch(double a, double b, double E, targetFunction function, int index) {
        double x1 = b - (b - a) / PHI;
        double x2 = a + (b - a) / PHI;
        double fx1 = targetFunction.targetFunc(x1);
        double fx2 = targetFunction.targetFunc(x2);
        int iterationCount = 0;

        List<XYSeries> seriesList = new ArrayList<>();

        while (Math.abs(b - a) > E) {
            iterationCount++;

            // Создание новой серии данных для текущего интервала с уникальным именем
            XYSeries series = new XYSeries("Interval " + index + "-" + iterationCount);
            series.add(a, iterationCount);
            series.add(b, iterationCount);
            seriesList.add(series);

            if (iterationCount == 300) {
                break;
            } else if (fx1 >= fx2){
                a = x1;
                x1 = x2;
                fx1 = fx2;
                x2 = a + (b - a) / PHI;
                fx2 = targetFunction.targetFunc(x2);
            } else {
                b = x2;
                x2 = x1;
                fx2 = fx1;
                x1 = b - (b - a) / PHI;
                fx1 = targetFunction.targetFunc(x1);
            }
        }
        double minimumPoint = (a + b)/2;
        System.out.println("Золотого сечения");
        System.out.printf("При параметре точности = %.17f количество итераций: %d\n", E, iterationCount);
        System.out.printf("Точка минимума = %.17f; Минимальное значение функции = %.17f\n", minimumPoint, targetFunction.targetFunc(minimumPoint));
        System.out.printf("Количество вычисленных значений = %d\n", (int) iterationCount*2+1);
        // Добавление последнего интервала
        XYSeries lastSeries = new XYSeries("Interval " + index + "-" + (iterationCount + 1));
        lastSeries.add(a, iterationCount + 1);
        lastSeries.add(b, iterationCount + 1);
        seriesList.add(lastSeries);
        return seriesList;
    }
}
