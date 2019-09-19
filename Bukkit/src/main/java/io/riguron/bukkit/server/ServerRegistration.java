package io.riguron.bukkit.server;

import io.riguron.system.task.startup.PostLoadTask;

/**
 * Task responsible for registering server at server repository.
 */
public interface ServerRegistration extends PostLoadTask {

    /**
     * Sends registration packet to the proxies.
     */
    void run();

}
