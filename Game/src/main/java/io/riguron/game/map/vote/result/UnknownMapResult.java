package io.riguron.game.map.vote.result;

import lombok.Value;

import java.util.Arrays;
import java.util.List;

@Value
public class UnknownMapResult implements MapVotingResult {

    private String map;

    @Override
    public List<String> description() {
        return Arrays.asList("map.vote.unknown", map);
    }
}
