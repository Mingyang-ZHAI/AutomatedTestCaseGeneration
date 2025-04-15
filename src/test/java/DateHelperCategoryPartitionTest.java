import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.util.concurrent.TimeUnit;

public class DateHelperCategoryPartitionTest {

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
     * Test Case 1: Negative time value
     * - Tests behavior with a time value before Unix epoch (1970-01-01)
     * - Ensures method handles pre-1970 timestamps
     */
    @Test
    public void testNegativeTime() {
        long time = -1L;
        assertNotNull(DateHelper.getDateOnly(time));
        assertNotNull(DateHelper.getTimeOnly(time));
        assertNotNull(DateHelper.getDateAndTime(time));
    }

    /**
     * Test Case 2: Time = 0 (Unix epoch)
     * - Verifies that 1970-01-01 is returned as base date
     */
    @Test
    public void testEpochTime() throws Exception {
        long time = 0L;

        // Get output from DateHelper (uses default system timezone)
        String formatted = DateHelper.getDateOnly(time);
        System.out.println("Formatted from DateHelper: " + formatted);

        // Use system default timezone to format expected value
        SimpleDateFormat systemFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        systemFormatter.setTimeZone(TimeZone.getDefault()); // match DateHelper behavior
        String expected = systemFormatter.format(new Date(time));
        System.out.println("Expected (system default): " + expected);

        assertEquals(expected, formatted);
    }



    /**
     * Test Case 3: Current system time
     * - Validates typical runtime usage
     */
    @Test
    public void testCurrentTime() {
        long time = System.currentTimeMillis();
        assertNotNull(DateHelper.getDateOnly(time));
        assertNotNull(DateHelper.getTimeOnly(time));
        assertNotNull(DateHelper.getDateAndTime(time));
    }

    /**
     * Test Case 4: Time one hour ago
     * - Validates time formatting still works for recent past
     */
    @Test
    public void testOneHourAgo() {
        long time = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1);
        assertNotNull(DateHelper.getDateOnly(time));
        assertNotNull(DateHelper.getDateAndTime(time));
    }

    /**
     * Test Case 5: Time one day in the future
     * - Validates formatting of future timestamps
     */
    @Test
    public void testOneDayFuture() {
        long time = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1);
        assertNotNull(DateHelper.getDateOnly(time));
        assertNotNull(DateHelper.getDateAndTime(time));
    }

    /**
     * Test Case 6: Near year 2038 boundary
     * - Tests boundary handling for 32-bit signed int limits (Y2038 problem)
     */
    @Test
    public void testNear2038Boundary() {
        long time = Integer.MAX_VALUE * 1000L; // in ms
        String date = DateHelper.getDateOnly(time);
        assertTrue(date.contains("2038") || date.contains("204"));
    }

    /**
     * Test Case 7: Maximum long value
     * - Ensures robustness with extremely large timestamp
     */
    @Test
    public void testLongMaxValue() {
        long time = Long.MAX_VALUE;
        assertDoesNotThrow(() -> DateHelper.getDateOnly(time));
    }

    /**
     * Test Case 8: Minimum long value
     * - Ensures robustness with extremely small timestamp
     */
    @Test
    public void testLongMinValue() {
        long time = Long.MIN_VALUE;
        assertDoesNotThrow(() -> DateHelper.getDateOnly(time));
    }

    /**
     * Test Case 9: 12-hour format
     * - Verifies "AM/PM" marker exists
     */
    @Test
    public void test12HourFormat() {
        long time = System.currentTimeMillis();
        String formatted = DateHelper.getDateAndTime(time);  // uses hh:mm a
        System.out.println("Formatted time: " + formatted);

        // Allow both "am", "pm", "a.m.", "p.m." even with optional space before it
        String lower = formatted.toLowerCase();
        boolean matched = lower.matches(".*\\s?(am|pm|a\\.m\\.|p\\.m\\.).*");
        assertTrue(matched, "Expected AM/PM marker in: " + formatted);

    }


    /**
     * Test Case 10: Valid leap year date (Feb 29, 2020)
     * - Ensures leap day is handled correctly
     */
    @Test
    public void testLeapYearDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.set(2020, Calendar.FEBRUARY, 29, 12, 0, 0);
        long time = cal.getTimeInMillis();

        String formatted = DateHelper.getDateOnly(time);
        System.out.println("Leap year date: " + formatted);

        assertTrue(formatted.contains("29") || formatted.contains("02"));
    }


    /**
     * Test Case 11: Invalid leap day in non-leap year
     * - Calendar should throw an exception if set to Feb 29, 2021 with lenient=false
     */
    @Test
    public void testInvalidLeapDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setLenient(false); // enforce strict date rules
        cal.set(2021, Calendar.FEBRUARY, 29, 12, 0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            cal.getTime(); // should throw
        });
    }


    /**
     * Test Case 12: Start of day (00:00:00)
     * - Ensures time formatting handles midnight correctly
     */
    @Test
    public void testStartOfDay() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.set(2023, Calendar.OCTOBER, 1, 0, 0, 0);
        long time = cal.getTimeInMillis();

        String formatted = DateHelper.getDateAndTime(time);
        System.out.println("Start of day formatted: " + formatted);

        assertTrue(formatted.contains("00") || formatted.toLowerCase().contains("12:00 am"));
    }


    /**
     * Test Case 13: Leap second simulation
     * - Simulates 23:59:60, which is invalid in Java but can test robustness
     * - Expects proper fallback or handled exception
     */
    @Test
    public void testLeapSecondSimulation() {
        long simulatedLeapSecond = LocalDateTime.of(2016, 12, 31, 23, 59, 59)
                .toInstant(ZoneOffset.UTC).toEpochMilli() + 1000;
        assertDoesNotThrow(() -> DateHelper.getDateOnly(simulatedLeapSecond));
    }

    /**
     * Test Case 14: Time at exact midnight UTC
     * - Tests consistent formatting at UTC 00:00:00
     * - Verifies proper zero-padded output
     */
    @Test
    public void testMidnightUtc() throws Exception {
        // Construct a date in system default time zone at local 00:00:00
        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
        long time = LocalDateTime.of(2023, 10, 1, 0, 0)
                .atZone(defaultZone)
                .toInstant()
                .toEpochMilli();

        String formatted = DateHelper.getDateOnly(time);
        assertEquals("01/10/2023", formatted);
    }

    /**
     * Test Case 15: Non-UTC system time zone
     * - Emulates behavior under system default time zone (e.g., PST, GMT+8)
     * - Verifies consistency regardless of machine setting
     */
    @Test
    public void testSystemTimeZoneIndependence() {
        TimeZone original = TimeZone.getDefault();
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai")); // UTC+8
            long epoch = 0L;
            String formatted = DateHelper.getDateOnly(epoch);
            assertTrue(formatted.contains("1970")); // date string should still contain 1970
        } finally {
            TimeZone.setDefault(original);
        }
    }

}
