package io.riguron.system.party.result;

public enum InviteResult implements Result {

    /**
     * A player attempted to invite himself to the party.
     */
    INVITE_SELF,

    /**
     * Indicates successful invitation
     */
    INVITED,


    /**
     * A player attempted to invite another player to the party he
     * participates in, but he is not an owner of this party, therefore
     * is not permitted to invite new members.
     */
    NOT_AN_OWNER,

    /**
     * A player that was attempted to be invited in a party has disabled
     * party requests, so he can not be invited.
     */
    DISABLED,


    /**
     * A player that was attempted to be invited in a party is not online
     * at any of system proxies. Offline player can not be invited to parties.
     */
    NOT_ONLINE;

    @Override
    public String path() {
        return "invite";
    }

}
