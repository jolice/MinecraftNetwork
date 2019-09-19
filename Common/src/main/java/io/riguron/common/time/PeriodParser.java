package io.riguron.common.time;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PeriodParser {

    private static final String REGEX = "^[0-9]{1,2}[A-Za-z]{1,3}\\z";

    private Map<String, ChronoUnit> components = new HashMap<>();

    public PeriodParser() {
        this.putAll(ChronoUnit.DAYS, "d");
        this.putAll(ChronoUnit.WEEKS, "w");
        this.putAll(ChronoUnit.MONTHS, "m");
        this.putAll(ChronoUnit.YEARS, "y");
        this.putAll(ChronoUnit.SECONDS, "s");
        this.putAll(ChronoUnit.MINUTES, "min");
        this.putAll(ChronoUnit.HOURS, "h");
    }

    /**
     * Transforms list of temporal values to the single duration
     * by summing all of them. For example, the following string list:
     * <p>
     * ["1h", "23m", "25s"] will produce a Duration object holding time
     * duration equal to 1:23:25.
     *
     * @param arguments list of strings, each of which represents a single time duration.
     * @return sum of all duration values in the list, and list containing unresolved durations
     */
    public Optional<Result> parse(List<String> arguments) {
        Optional<Result> resultOptional = Optional.empty();
        Duration initial = Duration.ofNanos(0);
        List<String> undefinedValues = new ArrayList<>();
        for (String timeComponent : arguments) {

            if (timeComponent.matches(REGEX)) {

                int splitIndex = 0;

                while (Character.isDigit(timeComponent.charAt(splitIndex))) {
                    splitIndex++;
                }

                int timeValue = Integer.parseInt(timeComponent.substring(0, splitIndex));
                String dateValue = timeComponent.substring(splitIndex);

                if (timeValue > 0) {
                    ChronoUnit chronoUnit = components.getOrDefault(dateValue, ChronoUnit.FOREVER);
                    if (chronoUnit != ChronoUnit.FOREVER) {
                        Duration duration = Duration.of(chronoUnit.getDuration().getSeconds() * timeValue, ChronoUnit.SECONDS);
                        initial = initial.plus(duration);
                        continue;
                    }
                }
            }

            undefinedValues.add(timeComponent);
        }

        if (undefinedValues.size() < arguments.size()) {
            Result result = new Result(undefinedValues, initial);
            resultOptional = Optional.of(result);
        }

        return resultOptional;
    }

    private void putAll(ChronoUnit timeAddendum, String... values) {
        Arrays.stream(values).forEach(s -> {
            if (components.put(s, timeAddendum) != null) {
                throw new IllegalStateException(String.format("%s is already defined as a time component", s));
            }
        });
    }


    @Data
    @AllArgsConstructor
    public static class Result {

        private List<String> undefinedValues;
        private Duration duration;
    }
}
