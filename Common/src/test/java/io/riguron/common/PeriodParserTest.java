package io.riguron.common;

import io.riguron.common.time.PeriodParser;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class PeriodParserTest {

    private PeriodParser periodParser;

    private String[] testValues = {"%dmin", "%dh", "%dd", "%dw", "%dm", "%dy"};

    private ChronoUnit[] chronoUnits = {ChronoUnit.MINUTES, ChronoUnit.HOURS, ChronoUnit.DAYS, ChronoUnit.WEEKS, ChronoUnit.MONTHS, ChronoUnit.YEARS};

    @Before
    public void setUp() {
        periodParser = new PeriodParser();
        assertEquals(testValues.length, chronoUnits.length);
    }

    @Test
    public void whenProvidedIncorrectValueThenNotParsed() {
        assertFailed("wrongVal1");
        assertFailed("1x");
        assertFailed("11x");
        assertFailed("x2");
    }

    @Test
    public void whenProvidedNegativeTimeValueThenFailed() {
        assertFailed("-1d");
        assertFailed("-2m");
    }

    @Test
    public void whenZeroSpecifiedThenFailed() {
        assertFailed("0d");
    }

    @Test
    public void whenProvideProperResultThenParsed() {
        assertExpectedResult(sec(ChronoUnit.YEARS, 2) + sec(ChronoUnit.DAYS, 99), "2y", "99d");
    }

    @Test
    public void whenProvideSingleValueThenParsedProperly() {
        for (int i = 1; i < 30; i++) {
            for (int j = 0; j < chronoUnits.length; j++) {
                assertExpectedResult(sumAll(i, j), format(i, j));
            }
        }
    }

    @Test
    public void whenProvideTwoArgumentsThenParsedProperly() {
        for (int outer = 1; outer < 30; outer++) {
            for (int i = 0; i < testValues.length; i++) {
                for (int j = i; j < testValues.length; j++) {
                    int rndVal = ThreadLocalRandom.current().nextInt(1, 30);
                    long expectedResult = sumAll(rndVal, i, j);
                    String[] strings = format(rndVal, i, j);
                    assertExpectedResult(expectedResult, strings);
                }
            }
        }
    }

    @Test
    public void whenProvideThreeArgumentsThenParsedProperly() {
        for (int outer = 1; outer < 30; outer++) {
            for (int i = 0; i < testValues.length; i++) {
                for (int j = i; j < testValues.length; j++) {
                    for (int k = j; k < testValues.length; k++) {
                        int rndVal = ThreadLocalRandom.current().nextInt(1, 30);
                        long expectedResult = sumAll(rndVal, i, j, k);
                        String[] strings = format(rndVal, i, j, k);
                        assertExpectedResult(expectedResult, strings);
                    }
                }
            }
        }
    }

    @Test
    public void whenTransLiteralThenParsedProperly() {
        assertExpectedResult(sec(ChronoUnit.HOURS, 2), "2h");
    }

    private String[] format(int val, int... indexes) {
        return Arrays.stream(indexes).mapToObj(value -> String.format(testValues[value], val)).toArray(String[]::new);
    }

    private long sumAll(int val, int... indexes) {
        return Arrays.stream(indexes).mapToObj(value -> chronoUnits[value]).mapToLong(value -> sec(value, val)).sum();
    }

    private void assertFailed(String... arguments) {
        assertFalse(periodParser.parse(Arrays.asList(arguments)).isPresent());
    }

    private void assertExpectedResult(long expectedResult, String... arguments) {
        Optional<PeriodParser.Result> resultOptional = periodParser.parse(Arrays.asList(arguments));
        assertTrue(resultOptional.isPresent());
        resultOptional.ifPresent(result -> {
            assertTrue(result.getUndefinedValues().isEmpty());
            Duration duration = result.getDuration();
            long resultInSeconds = duration.getSeconds();
            assertEquals(expectedResult, resultInSeconds);
        });
    }

    private long sec(ChronoUnit chronoUnit, int count) {
        return chronoUnit.getDuration().getSeconds() * count;
    }
}
