package mx.edu.psp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SimpsonIntegrationTest {
    private final SimpsonIntegration integration = new SimpsonIntegration();

    @Test void requiredAcademicCases() {
        assertEquals(0.35006, integration.integrate(1.1, 9).value(), 0.0000051);
        assertEquals(0.36757, integration.integrate(1.1812, 10).value(), 0.0000051);
        assertEquals(0.49500, integration.integrate(2.750, 30).value(), 0.0000051);
    }

    @Test void integralAtZeroIsZero() { assertEquals(0.0, integration.integrate(0.0, 7).value()); }
    @Test void convergesWithinRequestedTolerance() {
        var result = integration.integrate(1.1, 9);
        assertTrue(result.segments() > SimpsonIntegration.DEFAULT_INITIAL_SEGMENTS);
        assertTrue(result.difference() < SimpsonIntegration.DEFAULT_TOLERANCE);
    }
    @Test void rejectsInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> integration.integrate(-1, 9));
        assertThrows(IllegalArgumentException.class, () -> integration.integrate(Double.NaN, 9));
        assertThrows(IllegalArgumentException.class, () -> integration.integrate(1, 0));
        assertThrows(IllegalArgumentException.class, () -> integration.integrate(1, 9, 3, 1e-5));
        assertThrows(IllegalArgumentException.class, () -> integration.integrate(1, 9, 10, 0));
    }
    @Test void gammaMatchesKnownValues() {
        assertEquals(1.0, GammaFunction.value(1), 1e-14);
        assertEquals(Math.sqrt(Math.PI), GammaFunction.value(0.5), 1e-14);
        assertEquals(24.0, GammaFunction.value(5), 1e-12);
        assertThrows(IllegalArgumentException.class, () -> GammaFunction.value(0));
    }
}
