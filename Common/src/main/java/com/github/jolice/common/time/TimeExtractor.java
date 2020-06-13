package io.riguron.common.time;


import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TimeExtractor {

    private static final String TIME_FLAG = "-t";

    private static final int TIME_ARGUMENT_LIMIT = 3;

    private final PeriodParser periodParser;

    /**
     * Returns the duration - sum of all time values from the source list,
     * starting with index of $TIME_FLAG. Thus, the following list:
     * <p>
     * ["a", "b", "-t", "1h", "25m"]
     * <p>
     * will produce the duration equal to 1:25 (85 minutes).
     *
     * @param arguments input list
     * @return duration parsed from the list values, and unresolved (illegal) time values
     */
    public Result extractTime(List<String> arguments) {
        List<String> timeArguments = extractTimeArguments(arguments);
        Duration duration = Duration.ZERO;
        if (!timeArguments.isEmpty()) {
            Optional<PeriodParser.Result> period = periodParser.parse(timeArguments);
            if (period.isPresent()) {
                PeriodParser.Result result = period.get();
                duration = result.getDuration();
                List<String> undefinedValues = result.getUndefinedValues();
                if (!undefinedValues.isEmpty()) {
                    return new Result(duration, undefinedValues);
                }
            }
        }
        return new Result(duration, Collections.emptyList());
    }

    private List<String> extractTimeArguments(List<String> input) {
        List<String> timeArguments = new ArrayList<>();
        int timeIndex = input.indexOf(TIME_FLAG);
        if (timeIndex >= 0) {
            for (int i = timeIndex + 1; i < input.size() && i <= timeIndex + TIME_ARGUMENT_LIMIT; i++) {
                timeArguments.add(input.get(i));
            }
        }
        return timeArguments;
    }

    @Value
    public static class Result {

        private Duration duration;
        private List<String> undefinedValues;

    }

}
