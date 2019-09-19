package io.riguron.system.party;

import io.riguron.system.preferences.PlayerPreferences;
import io.riguron.system.message.PlayerMessaging;
import io.riguron.system.party.result.DisbandResult;
import io.riguron.system.party.result.InviteResult;
import io.riguron.system.preferences.PlayerPreferences;
import io.riguron.system.preferences.PreferencesRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class PartyServiceTest {

    @Test
    public void disband() {
        PartyRepository repository = mock(PartyRepository.class);
        PreferencesRepository preferencesRepository = mock(PreferencesRepository.class);
        PlayerMessaging playerMessaging = mock(PlayerMessaging.class);
        PartyService partyService = new PartyService(
                repository, preferencesRepository, playerMessaging
        );

        when(repository.getPartyMembers(eq("owner"))).thenReturn(
                new HashSet<>(Arrays.asList("a", "b", "c", "owner"))
        );


        when(repository.disband(any())).thenReturn(new DisbandResult(true, new HashSet<>(
                Arrays.asList("a", "b", "c")
        )));


        assertEquals(true, partyService.disband("owner").success());
        verify(playerMessaging).distribute(eq(new HashSet<>(Arrays.asList("a", "b", "c"))), any());
    }

    @Test
    public void invite() {

        PreferencesRepository preferencesRepository = mock(PreferencesRepository.class);
        when(preferencesRepository.findBy(any())).thenAnswer(invocation -> {
            PlayerPreferences preferences = mock(PlayerPreferences.class);
            when(preferences.isPartyRequests()).thenReturn(true);
            return Optional.of(preferences);
        });

        PlayerMessaging playerMessaging = mock(PlayerMessaging.class);
        when(playerMessaging.isOnline(eq("target"))).thenReturn(true);

        PartyRepository partyRepository = mock(PartyRepository.class);
        when(partyRepository.invite(eq("owner"), eq("target"), anyInt()))
                .thenReturn(InviteResult.INVITED);

        PartyService partyService = new PartyService(partyRepository, preferencesRepository, playerMessaging);


        assertEquals(InviteResult.INVITED, partyService.invite("owner", "target"));
        verify(playerMessaging).sendTo(any(), eq("target"));
    }

    @Test
    public void chat() {
        PartyRepository partyRepository = mock(PartyRepository.class);
        PlayerMessaging playerMessaging = mock(PlayerMessaging.class);

        PartyService partyService = new PartyService(
                partyRepository,
                mock(PreferencesRepository.class),
                playerMessaging
        );

        when(partyRepository.getPartyOwner(eq("member"))).thenReturn(
                Optional.of("owner")
        );

        when(partyRepository.getPartyMembers("owner")).thenReturn(
                new HashSet<>(Arrays.asList("a", "b", "c", "owner")
                ));

        partyService.partyChat("member", "hello world");

        verify(playerMessaging).distribute(
                eq(new HashSet<>(Arrays.asList("a", "b", "c", "owner"))),
                any()
        );


    }


}