package me.riguron.system.party;

import lombok.RequiredArgsConstructor;
import me.riguron.server.redis.RedisTransactions;
import me.riguron.system.party.result.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class RedisPartyRepository implements PartyRepository {

    private static final String PARTY_OWNER = "party#owner:%s";

    private static final String PARTY_MEMBERS = "party#members:%s";

    private static final String PARTY_INVITATIONS = "party#invite:%s:%s";

    private final RedisTransactions redisTransactions;

    @Override
    public CreateResult create(String playerName) {
        return redisTransactions.runTransaction(jedis -> {
            String currentPartyOwner = String.format(PARTY_OWNER, playerName);
            jedis.watch(currentPartyOwner);
            if (jedis.exists(String.format(PARTY_MEMBERS, playerName))) {
                return CreateResult.DISBAND_FIRST;
            } else if (jedis.exists(currentPartyOwner)) {
                return CreateResult.ALREADY_IN_PARTY;
            } else {
                Transaction tx = jedis.multi();
                tx.sadd(String.format(PARTY_MEMBERS, playerName), playerName);
                return tx.exec() != null ? CreateResult.CREATED : CreateResult.ALREADY_IN_PARTY;
            }
        });
    }

    @Override
    public Set<String> getPartyMembers(String owner) {
        return redisTransactions.runTransaction(jedis -> {
            return getMembers(jedis, owner);
        });
    }

    @Override
    public InviteResult invite(String owner, String player, int expiration) {
        return redisTransactions.runTransaction(jedis -> {
            String membersKey = String.format(PARTY_MEMBERS, owner);
            jedis.watch(membersKey);

            if (!jedis.exists(membersKey)) {
                return InviteResult.NOT_AN_OWNER;
            }

            Transaction tx = jedis.multi();
            String key = String.format(PARTY_INVITATIONS, owner, player);
            tx.set(key, owner);
            tx.expire(key, expiration);
            return tx.exec() != null ? InviteResult.INVITED : InviteResult.NOT_AN_OWNER;
        });
    }

    @Override
    public AcceptResult accept(String owner, String player) {

        return redisTransactions.runTransaction(jedis -> {

            String targetPartyMembers = String.format(PARTY_MEMBERS, owner);

            jedis.watch(targetPartyMembers);

            String invitationKey = String.format(PARTY_INVITATIONS, owner, player);
            if (jedis.exists(invitationKey, targetPartyMembers) < 2) {
                return AcceptResult.NO_INVITATION;
            }

            if (jedis.exists(String.format(PARTY_MEMBERS, player), String.format(PARTY_OWNER, player)) > 0) {
                return AcceptResult.ALREADY_IN_PARTY;
            }

            Transaction tx = jedis.multi();
            tx.sadd(String.format(PARTY_MEMBERS, owner), player);
            tx.del(invitationKey);
            tx.set(String.format(PARTY_OWNER, player), owner);
            return tx.exec() != null ? AcceptResult.ACCEPTED : AcceptResult.WAS_DISBANDED;
        });
    }

    @Override
    public DisbandResult disband(String owner) {
        return redisTransactions.runTransaction(jedis -> {

            Set<String> members = getMembers(jedis, owner);

            if (members.isEmpty()) {
                return new DisbandResult(false, Collections.emptySet());
            }

            Transaction tx = jedis.multi();
            String[] toRemove =
                    members.stream()
                            .filter(s -> !s.equals(owner))
                            .map(x -> String.format(PARTY_OWNER, x))
                            .toArray(String[]::new);

            if (toRemove.length > 0) {
                tx.del(toRemove);
            }
            tx.del(String.format(PARTY_MEMBERS, owner));
            tx.exec();
            members.remove(owner);
            return new DisbandResult(true, members);
        });
    }


    @Override
    public RemoveResult remove(String applicant, String playerName) {
        return redisTransactions.runTransaction(jedis -> {
            String ownerKey = String.format(PARTY_OWNER, playerName);
            jedis.watch(ownerKey);

            String partyOwner = jedis.get(ownerKey);

            if (partyOwner == null) {
                return RemoveResult.NOT_PARTY_MEMBER;
            }


            if (!partyOwner.equalsIgnoreCase(applicant) && !applicant.equalsIgnoreCase(playerName)) {
                return RemoveResult.NOT_AN_OWNER;
            }

            Transaction tx = jedis.multi();
            Response<Long> removedCount = tx.srem(String.format(PARTY_MEMBERS, partyOwner), playerName);
            tx.del(ownerKey);

            List<Object> response = tx.exec();
            if (response != null) {
                return removedCount.get() > 0 ? RemoveResult.REMOVED : RemoveResult.NOT_PARTY_MEMBER;
            } else {
                return RemoveResult.NOT_PARTY_MEMBER;
            }
        });
    }

    @Override
    public Optional<String> getPartyOwner(String playerName) {
        return redisTransactions.runTransaction(jedis -> {
            return Optional.ofNullable(jedis.get(String.format(PARTY_OWNER, playerName)));
        });
    }

    private Set<String> getMembers(Jedis jedis, String owner) {
        return jedis.smembers(String.format(PARTY_MEMBERS, owner));
    }

}
