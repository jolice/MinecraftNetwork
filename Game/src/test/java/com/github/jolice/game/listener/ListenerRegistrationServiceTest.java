package com.github.jolice.game.listener;

import com.github.jolice.game.core.GameState;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ListenerRegistrationServiceTest {

    private PluginManager pluginManager;
    private ListenerUnregister listenerUnregister;

    private void runTest(Consumer<ListenerRegistrationService> action, List<ListenerRegistration> registrations) {
        this.pluginManager = mock(PluginManager.class);
        Plugin plugin = mock(Plugin.class);
        this.listenerUnregister = mock(ListenerUnregister.class);
        ListenerRegistrationService listenerRegistrationService = new ListenerRegistrationService(plugin, pluginManager, registrations, listenerUnregister);
        action.accept(listenerRegistrationService);
    }

    @Test
    public void whenOnlyOneState() {

        List<ListenerRegistration> registrations = new ArrayList<>();
        registrations.add(newRegistration(GameState.WAITING));
        registrations.add(newRegistration(GameState.WAITING));

        runTest(listenerRegistrationService -> {
            listenerRegistrationService.gameStateChanged(GameState.WAITING);

            verify(pluginManager, times(2)).registerEvents(any(), any());
            verifyZeroInteractions(listenerUnregister);

        }, registrations);

    }

    @Test
    public void whenMultipleStates() {


        ListenerRegistration w1 = newRegistration(GameState.WAITING);
        ListenerRegistration w2 = newRegistration(GameState.WAITING);
        ListenerRegistration a1 = newRegistration(GameState.ACTIVE);

        List<ListenerRegistration> registrations = new ArrayList<>(Arrays.asList(w1, w2, a1));

        runTest(listenerRegistrationService -> {

            // Register waiting and nothing more

            listenerRegistrationService.gameStateChanged(GameState.WAITING);

            verify(pluginManager).registerEvents(eq(w1.getListener()), any());
            verify(pluginManager).registerEvents(eq(w2.getListener()), any());

            verifyZeroInteractions(listenerUnregister);
            reset(listenerUnregister, pluginManager);

            // Register active

            listenerRegistrationService.gameStateChanged(GameState.ACTIVE);
            verify(pluginManager).registerEvents(eq(a1.getListener()), any());

            // unregister old
            verify(listenerUnregister).unregister(eq(w1.getListener()));
            verify(listenerUnregister).unregister(eq(w2.getListener()));


        }, registrations);
    }

    @Test
    public void whenNextStateContainsAlreadyRegistered() {

        ListenerRegistration w1 = newRegistration(GameState.WAITING);
        ListenerRegistration wa2 = newRegistration(GameState.WAITING, GameState.ACTIVE);
        ListenerRegistration a1 = newRegistration(GameState.ACTIVE);

        List<ListenerRegistration> registrations = new ArrayList<>(Arrays.asList(w1, wa2, a1));

        runTest(listenerRegistrationService -> {

            listenerRegistrationService.gameStateChanged(GameState.WAITING);

            verify(pluginManager).registerEvents(eq(w1.getListener()), any());
            verify(pluginManager).registerEvents(eq(wa2.getListener()), any());

            verifyZeroInteractions(listenerUnregister);
            reset(listenerUnregister, pluginManager);

            listenerRegistrationService.gameStateChanged(GameState.ACTIVE);

            verify(pluginManager).registerEvents(eq(a1.getListener()), any());
            verify(listenerUnregister, times(1)).unregister(eq(w1.getListener()));
            verify(listenerUnregister, times(0)).unregister(eq(wa2.getListener()));

        }, registrations);

    }

    private ListenerRegistration newRegistration(GameState... states) {
        Listener listener = mock(Listener.class);
        when(listener.toString()).thenReturn("MockListener, states=" + Arrays.toString(states));
        return new ListenerRegistration(listener, Arrays.asList(states));
    }
}