package io.riguron.system.login.chain;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Class responsible for executing login chain, i.e performing all steps
 * of the chain and handling exceptions.
 */
public interface LoginChainExecutor {

    /**
     * Dispatches the login chain.
     *
     * @param uuid         ID of player that joins the server
     * @param name         name of the player
     * @param errorHandler callback that accepts a error message from the chain
     */
    void execute(UUID uuid, String name, Consumer<String> errorHandler);

}
