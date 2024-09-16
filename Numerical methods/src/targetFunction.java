public class targetFunction {
    public static double targetFunc(double x) {
        double R1 = Math.tan((2 * Math.pow(x, 4) - 5 * x - 6) / 8);
        double R2 = Math.atan((7*Math.pow(x,2) - 11*x + 1 - Math.sqrt(2)) / (-7*Math.pow(x, 2) + 11*x + Math.sqrt(2)));
        return R1 + R2;
    }
}
