package me.riguron.bungee.data;

import lombok.RequiredArgsConstructor;
import me.riguron.server.repository.GlobalOnlineRepository;

/**
 * Class responsible for fetching global online for the repository.
 */
@RequiredArgsConstructor
public class GlobalOnline {

    private final GlobalOnlineRepository repository;

    private volatile int online;

    /**
     * Returns cached online value.
     *
     * @return current global online
     */
    public int getGlobalOnline() {
        return online;
    }

    /**
     * Refreshes global online by fetching actual online value from the repository.
     */
    public void update() {
        this.online = repository.getGlobalOnline();
    }
}
