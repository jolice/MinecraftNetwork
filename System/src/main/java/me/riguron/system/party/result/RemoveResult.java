package me.riguron.system.party.result;

public enum RemoveResult implements Result {

    /**
     * A player that was attempted to be removed actually
     * is not a party member.
     */
    NOT_PARTY_MEMBER,

    /**
     * Indicates successful removal.
     */
    REMOVED,

    /**
     * A player attempted to remove another player from the party,
     * but failed due to lack of permissions, since only a party owner
     * may kick members from the owned party.
     */
    NOT_AN_OWNER;

    @Override
    public String path() {
        return "remove";
    }
}
