package com.github.jolice.system.party;

import com.github.jolice.system.party.result.AcceptResult;
import com.github.jolice.system.party.result.InviteResult;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SelfCheckingPartyRepositoryTest {

    @Test
    public void invite() {
        SelfCheckingPartyRepository repository =
                new SelfCheckingPartyRepository(mock(PartyRepository.class));
        Assert.assertEquals(InviteResult.INVITE_SELF, repository.invite("owner", "owner", 10));

    }

    @Test
    public void accept() {
        SelfCheckingPartyRepository repository =
                new SelfCheckingPartyRepository(mock(PartyRepository.class));
        Assert.assertEquals(AcceptResult.ACCEPT_SELF, repository.accept("owner", "owner"));

    }

}