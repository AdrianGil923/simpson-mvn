package mx.edu.psp;

/** Coordinates calculation and user-facing output. */
public final class Logic {
    private final SimpsonIntegration integration = new SimpsonIntegration();

    public SimpsonIntegration.Result calculate(double x, int dof) {
        return integration.integrate(x, dof);
    }

    public String describe(double x, int dof) {
        SimpsonIntegration.Result result = calculate(x, dof);
        return String.format(java.util.Locale.ROOT,
            "Integral de t(0..%.6f, dof=%d) = %.10f (segmentos=%d, diferencia=%.3g)",
            x, dof, result.value(), result.segments(), result.difference());
    }
}
