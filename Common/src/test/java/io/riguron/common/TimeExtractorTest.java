package io.riguron.common;

import io.riguron.common.time.PeriodParser;
import io.riguron.common.time.TimeExtractor;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TimeExtractorTest {

    private PeriodParser periodParser = new PeriodParser();
    private TimeExtractor timeExtractor = new TimeExtractor(periodParser);

    @Test
    public void whenValidInputProvidedThenParsed() {
        TimeExtractor.Result result = timeExtractor.extractTime(Arrays.asList("name", "-t", "1h", "2min"));
        assertEquals(Duration.ofHours(1).plus(Duration.ofMinutes(2)), result.getDuration());
    }

    @Test
    public void whenNoNameProvidedThenCalculated() {
        TimeExtractor.Result result = timeExtractor.extractTime(Arrays.asList(
                "-t", "1h", "2min"
        ));
        assertEquals(Duration.ofHours(1).plus(Duration.ofMinutes(2)), result.getDuration());
    }

    @Test
    public void whenNoNameProvidedThenNotCalculated() {
        TimeExtractor.Result result = timeExtractor.extractTime(
                Arrays.asList("name", "-t", "1h", "2h", "2wrong")
        );
        assertEquals(Duration.ofHours(3), result.getDuration());
    }




}
