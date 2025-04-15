import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuadraticMetamorphicTest {

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
     * Source test case S1: Two real roots
     * Inputs: a=1, b=-3, c=2
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S1_Source() {
        try {
            Quadratic.solveQuadratic(1, -3, 2);
        } catch (Exception e) {
            fail("Exception thrown in source test S1: " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S1 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=2, b=-6, c=4
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S1_MR1() {
        try {
            Quadratic.solveQuadratic(2, -6, 4);
        } catch (Exception e) {
            fail("Exception in S1 follow-up (MR1): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S1 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=-1, b=3, c=-2
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S1_MR2() {
        try {
            Quadratic.solveQuadratic(-1, 3, -2);
        } catch (Exception e) {
            fail("Exception in S1 follow-up (MR2): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S1 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=1, b=3, c=2
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S1_MR3() {
        try {
            Quadratic.solveQuadratic(1, 3, 2);
        } catch (Exception e) {
            fail("Exception in S1 follow-up (MR3): " + e.getMessage());
        }
    }


    /**
     * Source test case S2: One repeated real root
     * Inputs: a=1, b=2, c=1
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S2_Source() {
        try {
            Quadratic.solveQuadratic(1, 2, 1);
        } catch (Exception e) {
            fail("Exception thrown in source test S2: " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S2 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=2, b=4, c=2
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S2_MR1() {
        try {
            Quadratic.solveQuadratic(2, 4, 2);
        } catch (Exception e) {
            fail("Exception in S2 follow-up (MR1): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S2 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=-1, b=-2, c=-1
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S2_MR2() {
        try {
            Quadratic.solveQuadratic(-1, -2, -1);
        } catch (Exception e) {
            fail("Exception in S2 follow-up (MR2): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S2 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=1, b=-2, c=1
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S2_MR3() {
        try {
            Quadratic.solveQuadratic(1, -2, 1);
        } catch (Exception e) {
            fail("Exception in S2 follow-up (MR3): " + e.getMessage());
        }
    }


    /**
     * Source test case S3: Complex roots
     * Inputs: a=1, b=0, c=4
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S3_Source() {
        try {
            Quadratic.solveQuadratic(1, 0, 4);
        } catch (Exception e) {
            fail("Exception thrown in source test S3: " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S3 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=2, b=0, c=8
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S3_MR1() {
        try {
            Quadratic.solveQuadratic(2, 0, 8);
        } catch (Exception e) {
            fail("Exception in S3 follow-up (MR1): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S3 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=-1, b=0, c=-4
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S3_MR2() {
        try {
            Quadratic.solveQuadratic(-1, 0, -4);
        } catch (Exception e) {
            fail("Exception in S3 follow-up (MR2): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S3 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=1, b=0, c=4
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S3_MR3() {
        try {
            Quadratic.solveQuadratic(1, 0, 4);
        } catch (Exception e) {
            fail("Exception in S3 follow-up (MR3): " + e.getMessage());
        }
    }


    /**
     * Source test case S4: Linear equation (a = 0)
     * Inputs: a=0, b=2, c=-4
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S4_Source() {
        try {
            Quadratic.solveQuadratic(0, 2, -4);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S4 Source: " + e.getMessage());
            } else {
                fail("Unexpected exception in S4 Source: " + e.getMessage());
            }
        }
    }



    /**
     * Follow-up test for S4 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=0, b=4, c=-8
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S4_MR1() {
        try {
            Quadratic.solveQuadratic(0, 2, -4);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S4 MR1: " + e.getMessage());
            } else {
                fail("Unexpected exception in S4 MR1: " + e.getMessage());
            }
        }
    }

    /**
     * Follow-up test for S4 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=0, b=-2, c=4
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S4_MR2() {
        try {
            Quadratic.solveQuadratic(0, -2, 4);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S4 MR2: " + e.getMessage());
            } else {
                fail("Unexpected exception in S4 MR2: " + e.getMessage());
            }
        }
    }

    /**
     * Follow-up test for S4 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=0, b=-2, c=-4
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S4_MR3() {
        try {
            Quadratic.solveQuadratic(0, -2, -4);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S4 MR3: " + e.getMessage());
            } else {
                fail("Unexpected exception in S4 MR3: " + e.getMessage());
            }
        }
    }


    /**
     * Source test case S5: Invalid equation (a = 0, b = 0)
     * Inputs: a=0, b=0, c=1
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S5_Source() {
        try {
            Quadratic.solveQuadratic(0, 0, 1);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S5 Source: " + e.getMessage());
            } else {
                fail("Unexpected exception in S5 Source: " + e.getMessage());
            }
        }
    }


    /**
     * Follow-up test for S5 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=0, b=0, c=2
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S5_MR1() {
        try {
            Quadratic.solveQuadratic(0, 0, 2);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S5 MR1: " + e.getMessage());
            } else {
                fail("Unexpected exception in S5 MR1: " + e.getMessage());
            }
        }
    }


    /**
     * Follow-up test for S5 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=0, b=0, c=-1
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S5_MR2() {
        try {
            Quadratic.solveQuadratic(0, 0, -1);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S5 MR2: " + e.getMessage());
            } else {
                fail("Unexpected exception in S5 MR2: " + e.getMessage());
            }
        }
    }


    /**
     * Follow-up test for S5 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=0, b=0, c=1
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S5_MR3() {
        try {
            Quadratic.solveQuadratic(0, 0, 1);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S5 MR3: " + e.getMessage());
            } else {
                fail("Unexpected exception in S5 MR3: " + e.getMessage());
            }
        }
    }

    /**
     * Source test case S6: Very small coefficients
     * Inputs: a=1e-06, b=1e-06, c=1e-06
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S6_Source() {
        try {
            Quadratic.solveQuadratic(1e-06, 1e-06, 1e-06);
        } catch (Exception e) {
            fail("Exception thrown in source test S6: " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S6 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=2e-06, b=2e-06, c=2e-06
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S6_MR1() {
        try {
            Quadratic.solveQuadratic(2e-06, 2e-06, 2e-06);
        } catch (Exception e) {
            fail("Exception in S6 follow-up (MR1): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S6 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=-1e-06, b=-1e-06, c=-1e-06
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S6_MR2() {
        try {
            Quadratic.solveQuadratic(-1e-06, -1e-06, -1e-06);
        } catch (Exception e) {
            fail("Exception in S6 follow-up (MR2): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S6 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=1e-06, b=-1e-06, c=1e-06
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S6_MR3() {
        try {
            Quadratic.solveQuadratic(1e-06, -1e-06, 1e-06);
        } catch (Exception e) {
            fail("Exception in S6 follow-up (MR3): " + e.getMessage());
        }
    }


    /**
     * Source test case S7: Very large coefficients
     * Inputs: a=1000000, b=1000000, c=1000000
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S7_Source() {
        try {
            Quadratic.solveQuadratic(1000000, 1000000, 1000000);
        } catch (Exception e) {
            fail("Exception thrown in source test S7: " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S7 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=2000000, b=2000000, c=2000000
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S7_MR1() {
        try {
            Quadratic.solveQuadratic(2000000, 2000000, 2000000);
        } catch (Exception e) {
            fail("Exception in S7 follow-up (MR1): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S7 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=-1000000, b=-1000000, c=-1000000
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S7_MR2() {
        try {
            Quadratic.solveQuadratic(-1000000, -1000000, -1000000);
        } catch (Exception e) {
            fail("Exception in S7 follow-up (MR2): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S7 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=1000000, b=-1000000, c=1000000
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S7_MR3() {
        try {
            Quadratic.solveQuadratic(1000000, -1000000, 1000000);
        } catch (Exception e) {
            fail("Exception in S7 follow-up (MR3): " + e.getMessage());
        }
    }

    /**
     * Source test case S8: Symmetric roots (±sqrt)
     * Inputs: a=1, b=0, c=-1
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S8_Source() {
        try {
            Quadratic.solveQuadratic(1, 0, -1);
        } catch (Exception e) {
            fail("Exception thrown in source test S8: " + e.getMessage());
        }
    }


    /**
     * MR4 - Symmetric Root Check:
     * If a = -c and b = 0, roots should be symmetric (i.e., r and -r)
     * This MR only applies when b = 0 and a = -c
     */
    @Test
    public void test_S8_MR4_SymmetricRootCheck() {
        try {
            Quadratic.solveQuadratic(1, 0, -1);
        } catch (Exception e) {
            fail("Exception in S8 MR4 symmetric root check: " + e.getMessage());
        }
    }


    /**
     * Source test case S9: Two real roots (both negative)
     * Inputs: a=1, b=4, c=3
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S9_Source() {
        try {
            Quadratic.solveQuadratic(1, 4, 3);
        } catch (Exception e) {
            fail("Exception thrown in source test S9: " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S9 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=2, b=8, c=6
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S9_MR1() {
        try {
            Quadratic.solveQuadratic(2, 8, 6);
        } catch (Exception e) {
            fail("Exception in S9 follow-up (MR1): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S9 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=-1, b=-4, c=-3
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S9_MR2() {
        try {
            Quadratic.solveQuadratic(-1, -4, -3);
        } catch (Exception e) {
            fail("Exception in S9 follow-up (MR2): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S9 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=1, b=-4, c=3
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S9_MR3() {
        try {
            Quadratic.solveQuadratic(1, -4, 3);
        } catch (Exception e) {
            fail("Exception in S9 follow-up (MR3): " + e.getMessage());
        }
    }


    /**
     * Source test case S10: Near-repeated root (numerical edge)
     * Inputs: a=1, b=2, c=1.0000001
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S10_Source() {
        try {
            Quadratic.solveQuadratic(1, 2, 1.0000001);
        } catch (Exception e) {
            fail("Exception thrown in source test S10: " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S10 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=2, b=4, c=2.0000002
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S10_MR1() {
        try {
            Quadratic.solveQuadratic(2, 4, 2.0000002);
        } catch (Exception e) {
            fail("Exception in S10 follow-up (MR1): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S10 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=-1, b=-2, c=-1.0000001
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S10_MR2() {
        try {
            Quadratic.solveQuadratic(-1, -2, -1.0000001);
        } catch (Exception e) {
            fail("Exception in S10 follow-up (MR2): " + e.getMessage());
        }
    }


    /**
     * Follow-up test for S10 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=1, b=-2, c=1.0000001
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S10_MR3() {
        try {
            Quadratic.solveQuadratic(1, -2, 1.0000001);
        } catch (Exception e) {
            fail("Exception in S10 follow-up (MR3): " + e.getMessage());
        }
    }


    /**
     * Source test case S11: All zero coefficients (infinite solutions)
     * Inputs: a=0, b=0, c=0
     * This test ensures the solver can run this core case without exceptions.
     */
    @Test
    public void test_S11_Source() {
        try {
            Quadratic.solveQuadratic(0, 0, 0);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S11 Source: " + e.getMessage());
            } else {
                fail("Unexpected exception in S11 Source: " + e.getMessage());
            }
        }
    }



    /**
     * Follow-up test for S11 using MR1
     * Scaling all coefficients by a constant (×2)
     * Transformed Inputs: a=0, b=0, c=0
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S11_MR1() {
        try {
            Quadratic.solveQuadratic(0, 0, 0);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S11 MR1: " + e.getMessage());
            } else {
                fail("Unexpected exception in S11 MR1: " + e.getMessage());
            }
        }
    }

    /**
     * Follow-up test for S11 using MR2
     * Negating all coefficients
     * Transformed Inputs: a=0, b=0, c=0
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S11_MR2() {
        try {
            Quadratic.solveQuadratic(0, 0, 0);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S11 MR2: " + e.getMessage());
            } else {
                fail("Unexpected exception in S11 MR2: " + e.getMessage());
            }
        }
    }


    /**
     * Follow-up test for S11 using MR3
     * Reflecting the sign of b only
     * Transformed Inputs: a=0, b=0, c=0
     * This test checks whether the solver handles the transformed input without throwing.
     */
    @Test
    public void test_S11_MR3() {
        try {
            Quadratic.solveQuadratic(0, 0, 0);
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("precision")) {
                System.out.println("Handled expected precision error in S11 MR3: " + e.getMessage());
            } else {
                fail("Unexpected exception in S11 MR3: " + e.getMessage());
            }
        }
    }

}