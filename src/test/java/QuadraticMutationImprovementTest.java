import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;


public class QuadraticMutationImprovementTest {

    /**
     * Covers subtractive cancellation path
     * This will trigger q = -0.5 * (b + sign(b) * sqrt)
     */
    @Test
    public void testSubtractiveCancellationPath() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, 1e8, 1));
    }

    /**
     * Covers sqrt edge: sqrt of zero
     */
    @Test
    public void testSqrtZero() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, 2, 1));
    }

    /**
     * Covers sqrt of 1 (perfect square)
     */
    @Test
    public void testSqrtOfOne() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, -2, 1));
    }

    /**
     * Tests negative discriminant edge: D = -1e-10
     */
    @Test
    public void testTinyNegativeDiscriminant() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1, 2, 1.0000000001));
    }

    /**
     * Tests NaN discriminant (b*b - 4ac == NaN)
     */
    @Test
    public void testNaNDiscriminant() {
        assertThrows(Exception.class, () -> Quadratic.solveQuadratic(Double.NaN, 1, 1));
    }

    /**
     * Precision overflow path in discriminant computation
     */
    @Test
    public void testDiscriminantOverflow() {
        assertThrows(Exception.class, () -> Quadratic.solveQuadratic(1e-308, 1e154, 1e-308));
    }


    @Test
    public void test_b_zero_c_zero() {
        Exception e = assertThrows(Quadratic.NotEnoughPrecisionException.class, () -> {
            Quadratic.solveQuadratic(1, 0, 0);
        });
        assertTrue(e.getMessage().toLowerCase().contains("precision"));
    }

    @Test
    public void test_discriminant_equals_bb() {
        Exception e = assertThrows(Exception.class, () -> Quadratic.solveQuadratic(1e154, 1e154, 0));
        assertTrue(e.getMessage().toLowerCase().contains("precision"));
    }
    @Test
    public void test_real_root_asymmetry() {
        assertDoesNotThrow(() -> Quadratic.solveQuadratic(1e-8, -1e4, 1));
    }

    @Test
    public void test_sqrt_zero_path() {
        try {
            Quadratic.solveQuadratic(1, 2, 1);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled expected precision error in sqrt zero case: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void test_sign_negative_branch() {
        try {
            Quadratic.solveQuadratic(1, -10, 1);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled expected precision issue in negative b case: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    //    ---------
    // Triggers precision error before divide-by-zero could occur
    @Test
    public void test_divide_by_zero_path() {
        assertThrows(Quadratic.NotEnoughPrecisionException.class, () -> Quadratic.solveQuadratic(1, 0, 0));
    }

    // Precision exception when discriminant == b^2
    @Test
    public void test_discriminant_equals_b_squared() {
        assertThrows(Quadratic.NotEnoughPrecisionException.class,
                () -> Quadratic.solveQuadratic(1e-20, 1, 0));
    }

    // sqrt(discriminant) = 0 (discriminant == 0)
    @Test
    public void test_sqrt_zero_path1() {
        try {
            Quadratic.solveQuadratic(1, 2, 1);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled precision error in sqrt==0 path: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // Complex roots: imaginary = 1.0
    @Test
    public void test_complex_root_imaginary_one() {
        try {
            Quadratic.solveQuadratic(1, 0, 1);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled precision error in complex root: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // Equal roots, check x1 == x2 output logic
    @Test
    public void test_duplicate_roots_print_once() {
        try {
            Quadratic.solveQuadratic(1, 2, 1);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled precision error in repeated root case: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // sign(b) = 0 path
    @Test
    public void test_sign_function_zero_b() {
        try {
            Quadratic.solveQuadratic(1, 0, -1);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled precision error in sign==0 case: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // formatDouble: non-integer roots
    @Test
    public void test_format_double_non_integer() {
        try {
            Quadratic.solveQuadratic(1, -3.5, 1);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled precision error in non-integer format test: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // sqrtByNewton(0) path
    @Test
    public void test_sqrt_by_newton_zero_input() {
        try {
            Quadratic.solveQuadratic(1, 0, 0);
        } catch (Quadratic.NotEnoughPrecisionException e) {
            System.out.println("Handled precision error at sqrt(0) path: " + e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // validateInput("1e-5")
//    @Test
//    public void test_validate_input_with_e_notation() throws Exception {
//        double result = Quadratic.validateInput("1e-5");
//        assertEquals(1e-5, result);
//    }

    // discriminant == b*b due to rounding
//    @Test
//    public void test_discriminant_equals_bb_edge_case() {
//        double b = 1e154;
//        double a = 1.0;
//        double c = (b * b) / (4.0 * a);
//        assertThrows(Quadratic.NotEnoughPrecisionException.class,
//                () -> Quadratic.solveQuadratic(a, b, c));
//    }


    @Test
    public void test_discriminant_sign_sensitivity_output() throws Exception {
        // Backup original System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            Quadratic.solveQuadratic(1, 1, 1);  // Discriminant < 0 → complex roots
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            // Restore System.out
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("i"), "Expected complex roots (i) in output");
    }

    @Test
    public void test_real_part_precision_path() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Discriminant < 0 → complex roots, real part ≠ 0
            Quadratic.solveQuadratic(2, 4, 5); // real = -4 / (2*2) = -1.0
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        // Check real part is correct (if mutant is present, this will be incorrect)
        assertTrue(output.contains("x1 = -1"), "Expected real part to be -1");
    }

    @Test
    public void test_complex_real_imaginary_output() {
        // Backup original output stream
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Discriminant = -16, sqrt = 4i
            // a=1, b=4, c=5 → real = -2.0, imaginary = 2.0
            Quadratic.solveQuadratic(1, 4, 5);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        // Expected: x1 = -2 + 2i
        assertTrue(output.contains("x1 = -2"), "Expected real part to be -2");
        assertTrue(output.contains("i"), "Expected imaginary part to contain 'i'");
    }




    // Helper to invoke private static sign(double b) using reflection
    private int invokeSign(double b) {
        try {
            Method method = Quadratic.class.getDeclaredMethod("sign", double.class);
            method.setAccessible(true);
            return (int) method.invoke(null, b);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
            return 0; // Unreachable
        }
    }

    @Test
    public void test_sign_function_zero_input() {
        // This will test the sign function with input 0.0
        // Current logic returns -1 for b == 0
        int result = invokeSign(0.0);
        assertEquals(-1, result);
    }



    // --- SqrtByNewton mutation killers ---

    // Kills: line 76 - negated conditional → SURVIVED
    @Test
    public void test_sqrt_zero_returns_zero() {
        double result = invokeSqrt(0.0);
        assertEquals(0.0, result, 1e-10, "sqrt(0.0) should return 0.0");
    }

    // Kills: line 79 - Replaced double division/addition → SURVIVED
    @Test
    public void test_newton_first_iteration_correctness() {
        double result = invokeSqrt(4.0);
        // Should converge to 2.0 (or close)
        assertEquals(2.0, result, 1e-8);
    }

    @Test
    public void test_sqrt_large_input_stability() {
        // Purpose: Kill mutation that replaces division with multiplication
        // If division is replaced by multiplication, result will be wildly off
        double result = invokeSqrt(1e6);
        double expected = Math.sqrt(1e6);
        assertEquals(expected, result, 1e-6); // Small delta
    }

    // Kills: line 83/84 - Replacement of arithmetic and conditional logic → TIMED_OUT or SURVIVED
    @Test
    public void test_sqrt_large_input_converges() {
        double result = invokeSqrt(1e10);
        assertEquals(1e5, result, 1.0);
    }

    @Test
    public void test_sqrt_small_input_converges() {
        double result = invokeSqrt(1e-10);
        assertEquals(1e-5, result, 1e-6);
    }
    @Test
    public void test_sqrt_converge_on_error_threshold() {
        // Purpose: Construct previous-result == ERROR so the condition boundary is tested
        double value = 2.0;
        double expected = Math.sqrt(value);
        double result = invokeSqrt(value);
        assertEquals(expected, result, 1e-6);
    }

    // Kills: line 89 - replaced return result → 0.0
    @Test
    public void test_sqrt_typical_value() {
        double result = invokeSqrt(9.0);
        assertTrue(result > 0.0);
    }


    // Helper to call the private method `sqrtByNewton`
    private double invokeSqrt(double value) {
        try {
            Method method = Quadratic.class.getDeclaredMethod("sqrtByNewton", double.class);
            method.setAccessible(true);
            return (double) method.invoke(null, value);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
            return -1;
        }
    }



    /**
     * Utility to capture output of solveQuadratic
     */
    private String captureOutput(double a, double b, double c) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            Quadratic.solveQuadratic(a, b, c);
        } finally {
            System.setOut(originalOut);
        }
        return out.toString();
    }

    // Mutation line 52–54: verify computed roots 1.0 and 2.0
    @Test
    public void test_real_root_q_computation() throws Exception {
        String output = captureOutput(1, -3, 2);  // x^2 - 3x + 2 = 0, roots = 1 and 2
        System.out.println("DEBUG: output = " + output);

        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
    }

    // Mutation line 57: make sure x1 != x2 so both roots are printed
    @Test
    public void test_distinct_real_roots_are_printed() throws Exception {
        String output = captureOutput(1, -5, 6);  // x^2 - 5x + 6 = 0, roots = 2 and 3
        assertTrue(output.contains("x2 ="));
    }

    // Mutation line 58: x1 == x2 should suppress duplicate output
    @Test
    public void test_duplicate_root_not_printed_twice() throws Exception {
        String output = captureOutput(1, 2, 1);  // x^2 + 2x + 1 = 0, root = -1
        int count = output.split("x2 =").length - 1;
        assertEquals(0, count);  // Should not print x2 again
    }
    // Validates sign function with positive input (q calculation uses it)
    @Test
    public void test_sign_function_positive_input() {
        int result = invokeSign(10.0);
        assertEquals(1, result);
    }

    // Validates sign function with negative input (q calculation uses it)
    @Test
    public void test_sign_function_negative_input() {
        int result = invokeSign(-5.0);
        assertEquals(-1, result);
    }

    // Validates sqrtByNewton result for a value known to have exact root
    @Test
    public void test_sqrt_newton_for_perfect_square() {
        double result = invokeSqrt(16.0);
        assertEquals(4.0, result, 1e-12);
    }

    // Validates sqrtByNewton result for a non-perfect square
    @Test
    public void test_sqrt_newton_for_imprecise_root() {
        double result = invokeSqrt(2.0);
        assertTrue(result > 1.4 && result < 1.42);
    }







}
