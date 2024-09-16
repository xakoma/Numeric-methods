import org.jfree.data.xy.XYSeries;
import java.util.ArrayList;
import java.util.List;

public class dichotomyMethod {

    // Метод для выполнения дихотомии и создания серий данных
    public static List<XYSeries> performDichotomy(double a, double b, double E, targetFunction function, int index) {
        double d = E / 2;
        int N = 0; // количество итераций
        List<XYSeries> seriesList = new ArrayList<>();
        double m = 0;

        while ((b - a) > E) {
            N++;
            m = (a + b) / 2;
            double Y_x1 = targetFunction.targetFunc(m - d);  // Вызов целевой функции
            double Y_x2 = targetFunction.targetFunc(m + d);

            // Создание новой серии данных для текущего интервала с уникальным именем
            XYSeries series = new XYSeries("Interval " + index + "-" + N);
            series.add(a, N);
            series.add(b, N);
            seriesList.add(series);

            if (Y_x1 < Y_x2) {
                b = m - d;
            } else {
                a = m + d;
            }
        }

        // Добавление последнего интервала
        XYSeries lastSeries = new XYSeries("Interval " + index + "-" + (N + 1));
        lastSeries.add(a, N + 1);
        lastSeries.add(b, N + 1);
        seriesList.add(lastSeries);

        System.out.println("Метод дихотомии");
        System.out.printf("При параметре точности = %.17f количество итераций: %d\n", E, N);
        System.out.printf("Точка минимума = %.17f; Минимальное значение функции = %.17f\n", m, targetFunction.targetFunc(m));
        System.out.printf("Количество вычисленных значений = %d\n", (int) N*2+1);

        return seriesList;
    }
}

