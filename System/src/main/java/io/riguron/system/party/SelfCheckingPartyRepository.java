package io.riguron.system.party;

import lombok.RequiredArgsConstructor;
import io.riguron.system.party.result.*;

import java.util.Optional;
import java.util.Set;

/**
 * PartyRepository decorator that checks an equality of arguments of
 * some methods that must not accept same argument values.
 */
@RequiredArgsConstructor
public class SelfCheckingPartyRepository implements PartyRepository {

    private final PartyRepository delegate;

    @Override
    public CreateResult create(String owner) {
        return delegate.create(owner);
    }

    @Override
    public Set<String> getPartyMembers(String owner) {
        return delegate.getPartyMembers(owner);
    }

    @Override
    public DisbandResult disband(String owner) {
        return delegate.disband(owner);
    }

    @Override
    public InviteResult invite(String owner, String player, int expiration) {
        // A player can not invite himself.
        return owner.equalsIgnoreCase(player) ? InviteResult.INVITE_SELF : delegate.invite(owner, player, expiration);
    }

    @Override
    public AcceptResult accept(String owner, String player) {
        // A player can not accept party request from himself.
        return owner.equalsIgnoreCase(player) ? AcceptResult.ACCEPT_SELF : delegate.accept(owner, player);
    }

    @Override
    public RemoveResult remove(String caller, String playerName) {
        // Player can remove himself from party
        return delegate.remove(caller, playerName);
    }

    @Override
    public Optional<String> getPartyOwner(String playerName) {
        return delegate.getPartyOwner(playerName);
    }
}
