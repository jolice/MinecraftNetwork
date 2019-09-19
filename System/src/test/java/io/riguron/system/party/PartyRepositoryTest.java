package io.riguron.system.party;

import io.riguron.server.redis.RedisTransactions;
import io.riguron.system.party.result.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.JedisPool;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartyRepositoryTest {

    private static final String OWNER = "owner";

    private PartyRepository partyRepository;
    private RedisTransactions redisTransactions;


    @Before
    public void setup() {
        redisTransactions = new RedisTransactions(new JedisPool());
        partyRepository = new RedisPartyRepository(redisTransactions);
    }

    @Test
    public void create() {
        assertEquals(CreateResult.CREATED, partyRepository.create(OWNER));
    }


    @Test
    public void createWhenExists() {
        assertEquals(CreateResult.CREATED, partyRepository.create(OWNER));
        assertEquals(CreateResult.DISBAND_FIRST, partyRepository.create(OWNER));
    }

    @Test
    public void createWhenInOtherParty() {
        partyRepository.create(OWNER);
        partyRepository.invite(OWNER, "player", 60);
        partyRepository.accept(OWNER, "player");
        assertEquals(CreateResult.ALREADY_IN_PARTY, partyRepository.create("player"));
    }

    @Test
    public void getPartyMembers() {
        partyRepository.create(OWNER);
        for (int i = 0; i < 5; i++) {
            partyRepository.invite(OWNER, "p" + i, 60);
            partyRepository.accept(OWNER, "p" + i);
        }

        List<String> expectedMembers = Arrays.asList("p0", "p1", "p2", "p3", "p4", OWNER);
        Collections.sort(expectedMembers);
        List<String> actualMembers = new ArrayList<>(partyRepository.getPartyMembers(OWNER));
        Collections.sort(actualMembers);
        assertEquals(expectedMembers, actualMembers);

    }

    @Test
    public void acceptWithoutInvitation() {
        partyRepository.create(OWNER);
        assertEquals(AcceptResult.NO_INVITATION, partyRepository.accept(OWNER, "player"));
    }

    @Test
    public void acceptWhenPartyOwner() {
        assertEquals(CreateResult.CREATED, partyRepository.create(OWNER));
        assertEquals(CreateResult.CREATED, partyRepository.create("target_owner"));
        assertEquals(InviteResult.INVITED, partyRepository.invite("target_owner", OWNER, 500));
        assertEquals(AcceptResult.ALREADY_IN_PARTY, partyRepository.accept("target_owner", OWNER));
    }

    @Test
    public void acceptWhenPartyMember() {
        partyRepository.create(OWNER);
        partyRepository.invite(OWNER, "player", 10);
        partyRepository.accept(OWNER, "player");
        partyRepository.create("other");
        partyRepository.invite("other", "player", 10);
        AcceptResult accept = partyRepository.accept("other", "player");
        assertEquals(AcceptResult.ALREADY_IN_PARTY, accept);
    }

    @Test
    public void parallelDisbandAccept() throws InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 100; i++) {
            partyRepository.create(OWNER);
            partyRepository.invite(OWNER, "player", 60);
            final Future<?> f1 = executorService.submit(() -> {
                partyRepository.accept(OWNER, "player");
            });
            final Future<?> f2 = executorService.submit(() -> {
                partyRepository.disband(OWNER);
            });
            f1.get();
            f2.get();
            assertTrue(partyRepository.getPartyMembers(OWNER).isEmpty());
            cleanUp();
        }
        executorService.shutdown();
    }

    @Test
    public void disband() {
        partyRepository.create(OWNER);
        partyRepository.invite(OWNER, "a", 60);
        partyRepository.invite(OWNER, "b", 60);
        partyRepository.accept(OWNER, "a");
        partyRepository.accept(OWNER, "b");
        assertEquals(true, partyRepository.disband(OWNER).success());

        assertEquals(0, partyRepository.getPartyMembers(OWNER).size());
    }

    @Test
    public void whenDisbandAndNotOwning() {
        assertEquals(false, partyRepository.disband(OWNER).success());
    }

    @Test
    public void removeOwner() {
        testRemove(OWNER, RemoveResult.REMOVED, "toRemove", 2);
    }

    @Test
    public void removeSelfWhenNotOwner() {
        testRemove("toRemove", RemoveResult.REMOVED, "toRemove", 2);
    }

    @Test
    public void removeSelfWhenOwner() {
        testRemove(OWNER, RemoveResult.NOT_PARTY_MEMBER, OWNER, 3);
    }

    @Test
    public void removeOtherWhenNotOwner() {
        testRemove("other", RemoveResult.NOT_AN_OWNER, "toRemove", 3);
    }

    @Test
    public void removeWhenNotAMember() {
        testRemove("other1", RemoveResult.NOT_PARTY_MEMBER, "target", 3);
    }

    @Test
    public void removeWhenInexistentMember() {
        testRemove(OWNER, RemoveResult.NOT_PARTY_MEMBER, "someone", 3);
    }

    private void testRemove(String applicant, RemoveResult expectedResult, String playerName, int expectedMemberCount) {
        partyRepository.create(OWNER);
        partyRepository.invite(OWNER, "other", 60);
        partyRepository.invite(OWNER, "toRemove", 60);
        partyRepository.accept(OWNER, "toRemove");
        partyRepository.accept(OWNER, "other");
        assertEquals(expectedResult, partyRepository.remove(applicant, playerName));
        assertEquals(expectedMemberCount, partyRepository.getPartyMembers(OWNER).size());
    }

    @Test
    public void removeWhenNotInParty() {
        assertEquals(RemoveResult.NOT_PARTY_MEMBER, partyRepository.remove("someone", "somename"));
    }

    @Test
    public void whenPartyOwnerExistsThenReturned() {
        partyRepository.create(OWNER);
        partyRepository.invite(OWNER, "player", 60);
        partyRepository.accept(OWNER, "player");
        Optional<String> result = partyRepository.getPartyOwner("player");
        assertTrue(result.isPresent());
        assertEquals("owner", result.get());
    }

    @After
    public void cleanUp() {
        redisTransactions.runTransaction(BinaryJedis::flushAll);
    }


}

