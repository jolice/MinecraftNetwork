package me.riguron.system.party.result;

public enum AcceptResult implements Result {

    /**
     * Player tried to accept party request from himself, what is unacceptable
     */
    ACCEPT_SELF,

    /**
     * Party the player participated in was disbanded while accept request was being performed
     */
    WAS_DISBANDED,

    /**
     * Result indicating successful acceptance
     */
    ACCEPTED,

    /**
     * A player tried to accept invitation, but he didn't receive any party invitations
     */
    NO_INVITATION,

    /**
     * A player tried to accept party request, but he already participates in a party
     * and must leave it before joining another one
     */
    ALREADY_IN_PARTY;

    @Override
    public String path() {
        return "accept";
    }

}
