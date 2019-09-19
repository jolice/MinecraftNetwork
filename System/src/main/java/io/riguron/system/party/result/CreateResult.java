package io.riguron.system.party.result;

public enum CreateResult implements Result {

    /**
     * Indicates successful creation
     */
    CREATED,

    /**
     * A player tried to create a party, but he already participates in another party and
     * must leave it in order to create a new one
     */
    ALREADY_IN_PARTY,

    /**
     * A player tried to create a party, but he is already an owner of a part and must
     * disband it first in order to create another one
     */
    DISBAND_FIRST;

    @Override
    public String path() {
        return "create";
    }

}
