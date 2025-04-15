
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class QuadraticCategoryPartitionTest {

    private static long startTime;

    @BeforeAll
    public static void startTimer() {
        startTime = System.nanoTime();
    }

    @AfterAll
    public static void endTimer() {
        long endTime = System.nanoTime();
        long durationInMillis = (endTime - startTime) / 1_000_000;
        System.out.println(">>> Total execution time: " + durationInMillis + " ms");
    }
    /**
     * Test 1: Two distinct real roots (discriminant > 0)
     */
    @Test
    public void testTwoRealRoots() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, -5, 6));
    }

    /**
     * Test 2: Repeated real root (discriminant = 0)
     */
    @Test
    public void testRepeatedRoot() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, -4, 4));
    }

    /**
     * Test 3: Complex roots (discriminant < 0)
     */
    @Test
    public void testComplexRoots() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, 2, 5));
    }

    /**
     * Test 4: Linear equation (a = 0, b ≠ 0)
     */
    @Test
    public void testLinearEquation() {
        Exception e = assertThrows(Exception.class, () ->
                Quadratic.solveQuadratic(0, 3, -9));
        assertTrue(e.getMessage().toLowerCase().contains("precision"));
    }

    /**
     * Test 5: Invalid input (a = 0, b = 0, c != 0)
     */
    @Test
    public void testInvalidInput() {
        try {
            Quadratic.solveQuadratic(0, 0, 7);
        } catch (Exception e) {
            assertTrue(e.getMessage().toLowerCase().contains("precision"));
        }
    }

    /**
     * Test 6: All zero coefficients (a = b = c = 0)
     */
    @Test
    public void testAllZeros() {
        try {
            Quadratic.solveQuadratic(0, 0, 0);
        } catch (Exception e) {
            assertTrue(e.getMessage().toLowerCase().contains("precision"));
        }
    }

    /**
     * Test 7: Very small coefficients
     */
    @Test
    public void testSmallCoefficients() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1e-8, 1e-8, 1e-8));
    }

    /**
     * Test 8: Very large coefficients
     */
    @Test
    public void testLargeCoefficients() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1e8, -1e8, 1e8));
    }

    /**
     * Test 9: Symmetric root case (a = -c, b = 0)
     */
    @Test
    public void testSymmetricRootCase() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, 0, -1));
    }

    /**
     * Test 10: Edge discriminant close to zero
     */
    @Test
    public void testEdgeDiscriminant() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, 2, 1.0000001));
    }

    /**
     * Test 11: Negative zero coefficient
     */
    @Test
    public void testNegativeZeroCoefficient() {
        Exception e = assertThrows(Exception.class, () ->
                Quadratic.solveQuadratic(-0.0, 4, -5));
        assertTrue(e.getMessage().toLowerCase().contains("precision"));
    }

    /**
     * Test 12: NaN as coefficient
     */
    @Test
    public void testNaNCoefficient() {
        assertThrows(Exception.class, () -> Quadratic.solveQuadratic(Double.NaN, 1, 1));
    }


}
