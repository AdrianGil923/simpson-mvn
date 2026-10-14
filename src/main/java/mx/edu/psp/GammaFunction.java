package mx.edu.psp;

/** Lanczos approximation for the gamma function (g=7, nine coefficients). */
public final class GammaFunction {
    private static final double[] COEFFICIENTS = {
        0.99999999999980993, 676.5203681218851, -1259.1392167224028,
        771.32342877765313, -176.61502916214059, 12.507343278686905,
        -0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7
    };

    private GammaFunction() { }

    /** Returns Gamma(z) for positive finite z using the Lanczos formula. */
    public static double value(double z) {
        if (!(z > 0.0) || !Double.isFinite(z)) {
            throw new IllegalArgumentException("Gamma argument must be positive and finite");
        }
        if (z < 0.5) {
            return Math.PI / (Math.sin(Math.PI * z) * value(1.0 - z));
        }
        double shifted = z - 1.0;
        double sum = COEFFICIENTS[0];
        for (int i = 1; i < COEFFICIENTS.length; i++) {
            sum += COEFFICIENTS[i] / (shifted + i);
        }
        double t = shifted + 7.5;
        return Math.sqrt(2.0 * Math.PI) * Math.pow(t, shifted + 0.5) * Math.exp(-t) * sum;
    }
}
