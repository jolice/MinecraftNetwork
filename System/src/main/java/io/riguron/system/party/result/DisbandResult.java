package io.riguron.system.party.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class DisbandResult implements Result {

    /**
     * Whether the party was disbanded successfully.
     */
    private final boolean success;

    /**
     * Members of the party that was disbanded, or empty
     * collection if it was not.
     */
    @Getter
    private final Set<String> members;

    @Override
    public String name() {
        return "disband";
    }

    @Override
    public String path() {
        return success ? "disbanded" : "no_owned_party";
    }

    public boolean success() {
        return success;
    }
}
