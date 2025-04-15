import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class QuadraticBranchCoverageTest {

    /**
     * Tests input that is a valid integer without decimal point.
     * Covers: if (input.indexOf('.') == -1)
     */
    @Test
    public void testIntegerInputWithoutDot() throws Exception {
        double result = Quadratic.validateInput("123");
        assertEquals(123.0, result);
    }

    /**
     * Tests input that is a valid decimal number.
     * Skips the integer append branch.
     */
    @Test
    public void testDecimalInput() throws Exception {
        double result = Quadratic.validateInput("0.25");
        assertEquals(0.25, result);
    }

    /**
     * Tests input in scientific notation.
     * This triggers decimal mismatch and validates e-notation check.
     * Expected to throw NotEnoughPrecisionException due to formatting mismatch.
     */
    @Test
    public void testScientificNotationTriggersPrecisionException() {
        assertThrows(Quadratic.NotEnoughPrecisionException.class, () -> {
            Quadratic.validateInput("1e999"); // Will overflow and mismatch decimal format
        });
    }

    /**
     * Tests a valid input that still causes formatted string mismatch.
     * Expected to throw due to decimal != formatted
     */
    @Test
    public void testFormattedMismatchTriggersPrecisionException() {
        assertThrows(Quadratic.NotEnoughPrecisionException.class, () -> {
            Quadratic.validateInput("123456789012345678901234567890.1"); // Too long to format safely
        });
    }

    /**
     * Tests input with trailing zeroes to verify equality condition.
     * Avoids throwing precision exception.
     */
    @Test
    public void testDecimalZeroPadInput() throws Exception {
        double result = Quadratic.validateInput("4.0");
        assertEquals(4.0, result);
    }

    /**
     * Tests input that includes decimal point but is actually an integer.
     * Ensures .0 is recognized and does not trigger exception.
     */
    @Test
    public void testDotIntegerInput() throws Exception {
        double result = Quadratic.validateInput("7.0");
        assertEquals(7.0, result);
    }

    /**
     * Covers partial branch in validateInput where:
     * - decimal.format(value) != formatted (true)
     * - value.toString().equals(input) (true)
     * So the entire if condition is false and exception is NOT thrown.
     */
    @Test
    public void testPartialMismatch_NoExceptionThrown() throws Exception {
        // input without dot but matches .toString
        // formatted != input + ".0"  → true
        // value.toString() == input → true
        double result = Quadratic.validateInput("1234567890");
        assertEquals(1234567890.0, result);
    }

    /**
     * Covers the branch in validateInput where:
     * - decimal.format(value).equals(formatted) == false
     * - value.toString().equals(input) == false
     * Expected to trigger exception.
     */
    @Test
    public void testDecimalFormatMismatch_AndToStringMismatch() {
        assertThrows(Quadratic.NotEnoughPrecisionException.class, () -> {
            // extremely large number in scientific notation
            // formatted value != input
            // toString != input
            Quadratic.validateInput("1e999");
        });
    }


    /**
     * Simulates input where 'a' is zero to trigger a == 0 branch (line 156).
     * Expected: should print warning and continue.
     */
    @Test
    public void testMain_aEqualsZeroTriggersBranch() {
        String input = "0\n1\n1\ny\n0\n1\n1\nn\n";
        runMainWithInput(input);
    }

    /**
     * Simulates full round of a=1, b=1, c=1 and prompt 'n' to exit.
     * Triggers !prompt.equals("y") branch (line 186).
     */
    @Test
    public void testMain_PromptExitTriggersBranch() {
        String input = "1\n1\n1\nn\n";
        runMainWithInput(input);
    }

    /**
     * Simulates full round where 'a' is non-zero, valid roots, and user chooses to continue with 'y'.
     * Triggers prompt.equals("y") false branch (line 186).
     */
    @Test
    public void testMain_PromptContinueBranch() {
        String input = "1\n-2\n1\ny\n1\n1\n1\nn\n";
        runMainWithInput(input);
    }

    // Helper method to simulate System.in and invoke main()
    private void runMainWithInput(String simulatedInput) {
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
            Quadratic.main(new String[0]);
        } finally {
            System.setIn(originalIn);
        }
    }


    /**
     * Covers the final uncovered branch in validateInput:
     * - decimal.format(value).equals(formatted) == true
     * - value.toString().equals(input) == false
     * Therefore, the overall condition is false, and no exception is thrown.
     */
    @Test
    public void testFormatEqualsButToStringMismatch_NoException() throws Exception {
        // Input with long decimal tail that formats cleanly but toString differs
        String input = "1.000000000000";
        double result = Quadratic.validateInput(input);

        // It should not throw; just confirm it's parsed as expected
        assertEquals(1.0, result);
    }


    /**
     * Final missing branch coverage: both decimal.format(value).equals(formatted)
     * and value.toString().equals(input) are true.
     * This triggers the last remaining branch: (false && false).
     */
    @Test
    public void testFormatAndToStringBothEqual_NoException() throws Exception {
        // Clean input that satisfies both equality checks
        String input = "4.0";
        double result = Quadratic.validateInput(input);
        assertEquals(4.0, result);
    }

    @Test
    public void testFinalMissingBranch_FalseAndFalse_NoException() throws Exception {
        // This input causes both conditions to evaluate to false
        String input = "1.0";
        double result = Quadratic.validateInput(input);
        assertEquals(1.0, result);
    }
    @Test
    public void testFinalBranchFormatEqualsAndToStringEquals() throws Exception {
        String input = "0.125";
        double result = Quadratic.validateInput(input);
        assertEquals(0.125, result);
    }



}
