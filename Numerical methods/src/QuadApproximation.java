
import org.jfree.data.xy.XYSeries;
import java.util.ArrayList;
import java.util.List;

public class QuadApproximation {


    public static List<XYSeries> quadApproximation(double a, double b, double E, targetFunction function, int index) {
        double x1 = a;
        double x2 = (a + b) / 2;
        double x3 = b;
        int maxIterations = 300;

        List<XYSeries> seriesList = new ArrayList<>();
        int iterationCount = 0;

        while (Math.abs(b - a) > E && iterationCount < maxIterations) {
            iterationCount++;

            // Вычисляем значения функции в текущих точках
            double fx1 = targetFunction.targetFunc(x1);
            double fx2 = targetFunction.targetFunc(x2);
            double fx3 = targetFunction.targetFunc(x3);

            // Рассчитываем коэффициенты полинома
            double a0 = fx1;
            double a1 = (fx2 - fx1) / (x2 - x1);
            double a2 = (1 / (x3 - x2)) * ((fx3 - fx1) / (x3 - x1) - (fx2 - fx1) / (x2 - x1));

            // Находим стационарную точку
            double xStar = (x2 + x1) / 2 - (a1 / (2 * a2));

            // Вычисляем значение функции в стационарной точке
            double fxStar = targetFunction.targetFunc(xStar);

            // Определяем следующий интервал
            if (xStar < x1 || xStar > x3) {
                xStar = (x1 + x3) / 2; // Если xStar выходит за пределы интервала, берем среднюю точку
            }

            // Обновляем x1, x2 и x3 в зависимости от значения функции
            if (fxStar < fx1 && fxStar < fx2 && fxStar < fx3) {
                if (fxStar < fx2) {
                    if (fxStar < fx1) {
                        x3 = x2;
                        x2 = x1;
                        x1 = xStar;
                    } else {
                        x3 = x2;
                        x2 = xStar;
                    }
                } else {
                    x3 = xStar;
                }
            } else if (fxStar < fx2) {
                x3 = x2;
                x2 = xStar;
            }

            // Создание серии данных для текущего интервала
            XYSeries series = new XYSeries("Interval " + index + "-" + iterationCount);
            series.add(x1, iterationCount);
            series.add(x2, iterationCount);
            series.add(x3, iterationCount);
            seriesList.add(series);

            // Обновляем значения функции для новых точек
            fx1 = targetFunction.targetFunc(x1);
            fx2 = targetFunction.targetFunc(x2);
            fx3 = targetFunction.targetFunc(x3);

            a = Math.min(Math.min(x1, x2), x3);
            b = Math.max(Math.max(x1, x2), x3);
        }


        double minimumPoint = 0.5 * (x1 + x3);
        System.out.println("Метод последовательной квадратичной аппроксимации");
        System.out.printf("При параметре точности = %.17f количество итераций: %d\n", E, iterationCount);
        System.out.printf("Точка минимума = %.17f; Минимальное значение функции = %.17f\n", minimumPoint, function.targetFunc(minimumPoint));
        System.out.printf("Количество вычисленных значений = %d\n", iterationCount * 3);

        return seriesList;
    }
}
