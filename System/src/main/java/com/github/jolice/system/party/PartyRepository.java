package com.github.jolice.system.party;

import com.github.jolice.system.party.result.*;
import io.riguron.system.party.result.*;

import java.util.Optional;
import java.util.Set;

public interface PartyRepository {

    CreateResult create(String owner);

    /**
     * Returns names of all members of the party owned by specified player,
     * including owner's name, or empty list if there is no such a party.
     *
     * @param owner owner of the party
     * @return members of the party including owner
     */
    Set<String> getPartyMembers(String owner);

    DisbandResult disband(String owner);

    /**
     * Invites a specified player to the others' party.
     *
     * @param owner      player in whose party the target player is invited
     * @param player     player who is invited to owner's party.
     * @param expiration TTL of invitation. After this time invitation will be removed from the system.¬
     * @return result of the party.
     */
    InviteResult invite(String owner, String player, int expiration);

    AcceptResult accept(String owner, String player);

    /**
     * Requests a removal of the player from the party.
     *
     * @param applicant  a player on whose behalf this method is invoked.
     * @param playerName the player that is requested to be removed from the party.
     * @return result of removal
     */
    RemoveResult remove(String applicant, String playerName);

    /**
     * Fetches owner of the party that the target player participates in.
     *
     * @param playerName name of the player participating in the party
     * @return name of the player that owns playerName's party, or empty optional if he is not a member of a party
     */
    Optional<String> getPartyOwner(String playerName);


}
