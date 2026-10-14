package mx.edu.psp;

/** Adaptive composite Simpson 1/3 integration of the Student t density on [0,x]. */
public final class SimpsonIntegration {
    public static final double DEFAULT_TOLERANCE = 1.0e-5;
    public static final int DEFAULT_INITIAL_SEGMENTS = 10;
    private static final int MAX_SEGMENTS = 1 << 22;

    /** Result includes the approximation, segment count, and final refinement difference. */
    public record Result(double value, int segments, double difference) { }

    public Result integrate(double x, int dof) {
        return integrate(x, dof, DEFAULT_INITIAL_SEGMENTS, DEFAULT_TOLERANCE);
    }

    public Result integrate(double x, int dof, int initialSegments, double tolerance) {
        if (!Double.isFinite(x) || x < 0.0) throw new IllegalArgumentException("x must be finite and nonnegative");
        if (dof <= 0) throw new IllegalArgumentException("dof must be a positive integer");
        if (initialSegments < 2 || initialSegments % 2 != 0) throw new IllegalArgumentException("initial segments must be a positive even integer");
        if (!(tolerance > 0.0) || !Double.isFinite(tolerance)) throw new IllegalArgumentException("tolerance must be positive and finite");
        if (x == 0.0) return new Result(0.0, initialSegments, 0.0);

        int n = initialSegments;
        double previous = simpson(x, dof, n);
        while (n <= MAX_SEGMENTS / 2) {
            n *= 2;
            double current = simpson(x, dof, n);
            double difference = Math.abs(current - previous);
            if (difference < tolerance) return new Result(current, n, difference);
            previous = current;
        }
        throw new ArithmeticException("Simpson integration did not converge before segment limit " + MAX_SEGMENTS);
    }

    private double simpson(double x, int dof, int n) {
        double h = x / n;
        double weighted = density(0.0, dof) + density(x, dof);
        for (int i = 1; i < n; i++) weighted += (i % 2 == 0 ? 2.0 : 4.0) * density(i * h, dof);
        return weighted * h / 3.0;
    }

    private double density(double value, int dof) {
        double halfDof = dof / 2.0;
        double halfPlus = (dof + 1.0) / 2.0;
        double normalization = Math.exp(Math.log(GammaFunction.value(halfPlus))
            - 0.5 * Math.log(dof * Math.PI) - Math.log(GammaFunction.value(halfDof)));
        return normalization * Math.pow(1.0 + value * value / dof, -halfPlus);
    }
}
