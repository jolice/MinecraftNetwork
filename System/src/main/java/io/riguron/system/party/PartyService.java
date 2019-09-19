package io.riguron.system.party;

import io.riguron.system.preferences.PlayerPreferences;
import lombok.RequiredArgsConstructor;
import io.riguron.messaging.message.party.PartyChat;
import io.riguron.messaging.message.party.PartyDisband;
import io.riguron.messaging.message.party.PartyInvite;
import io.riguron.system.message.PlayerMessaging;
import io.riguron.system.party.result.*;
import io.riguron.system.preferences.PlayerPreferences;
import io.riguron.system.preferences.PreferencesRepository;
import io.riguron.system.preferences.specification.PlayerNameSpecification;

import java.util.Set;

@RequiredArgsConstructor
public class PartyService {

    public static final int EXPIRATION_TIME = 60;

    private final PartyRepository partyRepository;
    private final PreferencesRepository preferencesRepository;
    private final PlayerMessaging playerMessaging;

    public CreateResult create(String playerName) {
        return partyRepository.create(playerName);
    }

    /**
     * Returns members that were invited to some other player's party.
     * The party owner is not included in that list.
     *
     * @param playerName party owner
     * @return members of party created by specified owner
     */
    public Set<String> getInvitedMembers(String playerName) {
        Set<String> allMembers = getAllMembers(playerName);
        allMembers.remove(playerName);
        return allMembers;
    }

    private Set<String> getAllMembers(String playerName) {
        return partyRepository.getPartyMembers(playerName);
    }

    public RemoveResult remove(String applicant, String playerName) {
        return partyRepository.remove(applicant, playerName);
    }

    public AcceptResult accept(String owner, String player) {
        return partyRepository.accept(owner, player);
    }

    /**
     * Attempts to disband a party and in case of success notifies
     * all members of disbanded party about party disbanding.
     *
     * @param playerName party owner
     * @return result of disbanding
     */
    public DisbandResult disband(String playerName) {
        Set<String> members = getInvitedMembers(playerName);
        DisbandResult result = partyRepository.disband(playerName);
        if (result.success()) {
            playerMessaging.distribute(
                    members, PartyDisband::new);
        }
        return result;
    }

    /**
     * Attempts to invite player into the party, considering invitee's
     * preferences, and notifies invitee about the invitation.
     *
     * @param owner  player who owns the party
     * @param player invitee
     * @return
     */
    public InviteResult invite(String owner, String player) {
        if (playerMessaging.isOnline(player)) {
            return preferencesRepository.findBy(
                    new PlayerNameSpecification(player))
                    .filter(PlayerPreferences::isPartyRequests)
                    .map(playerProfile -> {
                        InviteResult result = partyRepository.invite(owner, player, EXPIRATION_TIME);
                        if (result == InviteResult.INVITED) {
                            playerMessaging.sendTo(new PartyInvite(owner, player), player);
                        }
                        return result;
                    }).orElse(InviteResult.DISABLED);
        } else {
            return InviteResult.NOT_ONLINE;
        }


    }

    /**
     * Sends a specified message to all of party's members, including the owner.
     *
     * @param member a message author
     * @param text   text of the message
     * @return true if a player participates in a party, false otherwise.
     */
    public boolean partyChat(String member, String text) {
        return partyRepository.getPartyOwner(member)
                .map(owner -> {
                    playerMessaging.distribute(partyRepository.getPartyMembers(owner),
                            s -> new PartyChat(s, text, member)
                    );
                    return true;
                }).orElse(Boolean.FALSE);
    }

}
