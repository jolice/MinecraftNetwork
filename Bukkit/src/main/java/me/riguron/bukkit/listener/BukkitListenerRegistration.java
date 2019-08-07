package me.riguron.bukkit.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;
import me.riguron.system.listener.ListenerRegistration;

@RequiredArgsConstructor
public class BukkitListenerRegistration implements ListenerRegistration<Listener> {

    private final RegisterListener registerListener;

    @Override
    public void registerListener(Listener listener) {
        registerListener.register(listener);
    }
}
