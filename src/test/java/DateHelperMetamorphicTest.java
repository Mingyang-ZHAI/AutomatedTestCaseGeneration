
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class DateHelperMetamorphicTest {

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

    private Date parse(String pattern, String input) throws Exception {
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        return format.parse(input);
    }
    /**
     * Source test case S1: Basic parsing of the date string 2023-08-15
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S1_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-08-15");
        Date actual = parse("yyyy-MM-dd", "2023-08-15");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S1: Format variation using '/' instead of '-'
     * Input: 2023/08/15 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S1_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-08-15");
            Date actual = parse("yyyy/MM/dd", "2023/08/15");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S1: Added leading and trailing whitespace
     * Input: '   2023-08-15   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S1_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-08-15");
            Date actual = parse("yyyy-MM-dd", "   2023-08-15   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S1: Case insensitivity test
     * Input: 2023-08-15 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S1_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-08-15");
            Date actual = parse("yyyy-MM-dd", "2023-08-15");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    @Test
    public void test_S1_MR4() throws Exception {
        /**
         * MR4: Offset by one month
         * Source date: "2023-08-15"
         * Follow-up date: "2023-09-15"
         * Expectation: the follow-up date should be exactly one month ahead
         */
        Date expected = parse("yyyy-MM-dd", "2023-08-15");
        Date actual = parse("yyyy-MM-dd", "2023-09-15");

        // Assert that the dates are exactly 1 month apart in milliseconds (30 days assumed)
        long diff = actual.getTime() - expected.getTime();
        long expectedDiff = TimeUnit.DAYS.toMillis(31); // Aug 15 to Sep 15 is 31 days in 2023

        assertEquals(expectedDiff, diff, "Timestamps differ by more than 31 days");
    }
    /**
     * Source test case S2: Basic parsing of the date string 2000-01-01
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S2_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2000-01-01");
        Date actual = parse("yyyy-MM-dd", "2000-01-01");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S2: Format variation using '/' instead of '-'
     * Input: 2000/01/01 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S2_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2000-01-01");
            Date actual = parse("yyyy/MM/dd", "2000/01/01");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S2: Added leading and trailing whitespace
     * Input: '   2000-01-01   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S2_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2000-01-01");
            Date actual = parse("yyyy-MM-dd", "   2000-01-01   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S2: Case insensitivity test
     * Input: 2000-01-01 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S2_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2000-01-01");
            Date actual = parse("yyyy-MM-dd", "2000-01-01");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4: Offset by one month
     * Source date: "2000-01-01"
     * Follow-up date: "2000-02-01"
     */
    @Test
    public void test_S2_MR4() throws Exception {

        Date expected = parse("yyyy-MM-dd", "2000-01-01");
        Date actual = parse("yyyy-MM-dd", "2000-02-01");
        long diff = actual.getTime() - expected.getTime();
        long expectedDiff = TimeUnit.DAYS.toMillis(31);
        assertTrue(Math.abs(diff - expectedDiff) <= TimeUnit.DAYS.toMillis(1),
                "Timestamps differ by more than 1 day");
    }
    /**
     * Source test case S3: Basic parsing of the date string 1999-12-31
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S3_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "1999-12-31");
        Date actual = parse("yyyy-MM-dd", "1999-12-31");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S3: Format variation using '/' instead of '-'
     * Input: 1999/12/31 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S3_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "1999-12-31");
            Date actual = parse("yyyy/MM/dd", "1999/12/31");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S3: Added leading and trailing whitespace
     * Input: '   1999-12-31   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S3_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "1999-12-31");
            Date actual = parse("yyyy-MM-dd", "   1999-12-31   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S3: Case insensitivity test
     * Input: 1999-12-31 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S3_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "1999-12-31");
            Date actual = parse("yyyy-MM-dd", "1999-12-31");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4: Offset by one month
     * Source date: "1999-12-03"
     * Follow-up date: "2000-01-03"
     */
    @Test
    public void test_S3_MR4() throws Exception {

        Date expected = parse("yyyy-MM-dd", "1999-12-03");
        Date actual = parse("yyyy-MM-dd", "2000-01-03");
        long diff = actual.getTime() - expected.getTime();
        long expectedDiff = TimeUnit.DAYS.toMillis(31);
        assertTrue(Math.abs(diff - expectedDiff) <= TimeUnit.DAYS.toMillis(1),
                "Timestamps differ by more than 1 day");
    }
    /**
     * Source test case S4: Basic parsing of the date string 2024-02-29
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S4_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2024-02-29");
        Date actual = parse("yyyy-MM-dd", "2024-02-29");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S4: Format variation using '/' instead of '-'
     * Input: 2024/02/29 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S4_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2024-02-29");
            Date actual = parse("yyyy/MM/dd", "2024/02/29");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S4: Added leading and trailing whitespace
     * Input: '   2024-02-29   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S4_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2024-02-29");
            Date actual = parse("yyyy-MM-dd", "   2024-02-29   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S4: Case insensitivity test
     * Input: 2024-02-29 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S4_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2024-02-29");
            Date actual = parse("yyyy-MM-dd", "2024-02-29");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S4: Invalid format with missing digit
     * Input: 2022-02-9 should throw a ParseException.
     */
    @Test
    public void test_S4_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2022-02-09");
            Date actual = parse("yyyy-MM-dd", "2022-02-9");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }
    /**
     * Source test case S5: Basic parsing of the date string 2023-12-31
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S5_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-12-31");
        Date actual = parse("yyyy-MM-dd", "2023-12-31");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S5: Format variation using '/' instead of '-'
     * Input: 2023/12/31 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S5_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-12-31");
            Date actual = parse("yyyy/MM/dd", "2023/12/31");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S5: Added leading and trailing whitespace
     * Input: '   2023-12-31   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S5_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-12-31");
            Date actual = parse("yyyy-MM-dd", "   2023-12-31   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S5: Case insensitivity test
     * Input: 2023-12-31 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S5_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-12-31");
            Date actual = parse("yyyy-MM-dd", "2023-12-31");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S5: Invalid format with missing digit
     * Input: 2001-06-4 should throw a ParseException.
     */
    @Test
    public void test_S5_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2001-06-04");
            Date actual = parse("yyyy-MM-dd", "2001-06-4");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }
    /**
     * Source test case S6: Basic parsing of the date string 2023-01-01
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S6_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-01-01");
        Date actual = parse("yyyy-MM-dd", "2023-01-01");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S6: Format variation using '/' instead of '-'
     * Input: 2023/01/01 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S6_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-01-01");
            Date actual = parse("yyyy/MM/dd", "2023/01/01");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S6: Added leading and trailing whitespace
     * Input: '   2023-01-01   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S6_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-01-01");
            Date actual = parse("yyyy-MM-dd", "   2023-01-01   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S6: Case insensitivity test
     * Input: 2023-01-01 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S6_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-01-01");
            Date actual = parse("yyyy-MM-dd", "2023-01-01");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S6: Invalid format with missing digit
     * Input: 2023-10-7 should throw a ParseException.
     */
    @Test
    public void test_S6_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-10-07");
            Date actual = parse("yyyy-MM-dd", "2023-10-7");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }
    /**
     * Source test case S7: Basic parsing of the date string 2023-06-15
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S7_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-06-15");
        Date actual = parse("yyyy-MM-dd", "2023-06-15");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S7: Format variation using '/' instead of '-'
     * Input: 2023/06/15 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S7_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-06-15");
            Date actual = parse("yyyy/MM/dd", "2023/06/15");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S7: Added leading and trailing whitespace
     * Input: '   2023-06-15   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S7_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-06-15");
            Date actual = parse("yyyy-MM-dd", "   2023-06-15   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S7: Case insensitivity test
     * Input: 2023-06-15 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S7_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-06-15");
            Date actual = parse("yyyy-MM-dd", "2023-06-15");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S7: Invalid format with missing digit
     * Input: 2020-02-2 should throw a ParseException.
     */
    @Test
    public void test_S7_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2020-02-02");
            Date actual = parse("yyyy-MM-dd", "2020-02-2");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }
    /**
     * Source test case S8: Basic parsing of the date string 2023-09-30
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S8_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-09-30");
        Date actual = parse("yyyy-MM-dd", "2023-09-30");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S8: Format variation using '/' instead of '-'
     * Input: 2023/09/30 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S8_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-09-30");
            Date actual = parse("yyyy/MM/dd", "2023/09/30");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S8: Added leading and trailing whitespace
     * Input: '   2023-09-30   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S8_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-09-30");
            Date actual = parse("yyyy-MM-dd", "   2023-09-30   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S8: Case insensitivity test
     * Input: 2023-09-30 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S8_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-09-30");
            Date actual = parse("yyyy-MM-dd", "2023-09-30");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S8: Invalid format with missing digit
     * Input: 2024-01-5 should throw a ParseException.
     */
    @Test
    public void test_S8_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2024-01-05");
            Date actual = parse("yyyy-MM-dd", "2024-01-5");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }
    /**
     * Source test case S9: Basic parsing of the date string 2023-03-31
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S9_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-03-31");
        Date actual = parse("yyyy-MM-dd", "2023-03-31");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S9: Format variation using '/' instead of '-'
     * Input: 2023/03/31 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S9_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-03-31");
            Date actual = parse("yyyy/MM/dd", "2023/03/31");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S9: Added leading and trailing whitespace
     * Input: '   2023-03-31   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S9_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-03-31");
            Date actual = parse("yyyy-MM-dd", "   2023-03-31   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S9: Case insensitivity test
     * Input: 2023-03-31 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S9_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-03-31");
            Date actual = parse("yyyy-MM-dd", "2023-03-31");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S9: Invalid format with missing digit
     * Input: 2021-09-3 should throw a ParseException.
     */
    @Test
    public void test_S9_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2021-09-03");
            Date actual = parse("yyyy-MM-dd", "2021-09-3");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }
    /**
     * Source test case S10: Basic parsing of the date string 2023-04-30
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S10_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-04-30");
        Date actual = parse("yyyy-MM-dd", "2023-04-30");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S10: Format variation using '/' instead of '-'
     * Input: 2023/04/30 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S10_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-04-30");
            Date actual = parse("yyyy/MM/dd", "2023/04/30");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S10: Added leading and trailing whitespace
     * Input: '   2023-04-30   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S10_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-04-30");
            Date actual = parse("yyyy-MM-dd", "   2023-04-30   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S10: Case insensitivity test
     * Input: 2023-04-30 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S10_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-04-30");
            Date actual = parse("yyyy-MM-dd", "2023-04-30");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S10: Invalid format with missing digit
     * Input: 2018-11-2 should throw a ParseException.
     */
    @Test
    public void test_S10_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2018-11-02");
            Date actual = parse("yyyy-MM-dd", "2018-11-2");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }
    /**
     * Source test case S11: Basic parsing of the date string 2023-11-30
     * Pattern: yyyy-MM-dd
     * Ensures valid parsing for well-formed date strings.
     */
    @Test
    public void test_S11_Source() throws Exception {
        Date expected = parse("yyyy-MM-dd", "2023-11-30");
        Date actual = parse("yyyy-MM-dd", "2023-11-30");
        assertEquals(expected, actual);
    }
    /**
     * MR1 for S11: Format variation using '/' instead of '-'
     * Input: 2023/11/30 with pattern yyyy/MM/dd
     * Should yield same date object as original.
     */
    @Test
    public void test_S11_MR1() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-11-30");
            Date actual = parse("yyyy/MM/dd", "2023/11/30");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR2 for S11: Added leading and trailing whitespace
     * Input: '   2023-11-30   ' should be trimmed and parsed correctly.
     */
    @Test
    public void test_S11_MR2() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-11-30");
            Date actual = parse("yyyy-MM-dd", "   2023-11-30   ");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR3 for S11: Case insensitivity test
     * Input: 2023-11-30 (uppercased), should still parse correctly.
     */
    @Test
    public void test_S11_MR3() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2023-11-30");
            Date actual = parse("yyyy-MM-dd", "2023-11-30");
            assertEquals(expected, actual);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * MR4 for S11: Invalid format with missing digit
     * Input: 2025-12-1 should throw a ParseException.
     */
    @Test
    public void test_S11_MR4() throws Exception {
        try {
            Date expected = parse("yyyy-MM-dd", "2025-12-01");
            Date actual = parse("yyyy-MM-dd", "2025-12-1");
            assertEquals(expected.getTime(), actual.getTime(), "Timestamps differ");
        } catch (Exception e) {
            return;
        }
    }

}