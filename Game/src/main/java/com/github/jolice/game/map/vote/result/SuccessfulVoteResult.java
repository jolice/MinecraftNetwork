package com.github.jolice.game.map.vote.result;

import lombok.Value;

import java.util.Arrays;
import java.util.List;

@Value
public class SuccessfulVoteResult implements MapVotingResult {

    private int votes;
    private String mapName;

    @Override
    public List<String> description() {
        return Arrays.asList("map.vote.success", String.valueOf(votes), mapName);
    }
}
