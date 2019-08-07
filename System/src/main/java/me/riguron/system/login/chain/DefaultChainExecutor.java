package me.riguron.system.login.chain;

import io.ebean.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.riguron.system.login.LoginDetails;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Default implementation of the executor
 */
@RequiredArgsConstructor
@Slf4j
public class DefaultChainExecutor implements LoginChainExecutor {

    private final LoginChainLink loginChain;

    @Override
    @Transactional
    public void execute(UUID uuid, String name, Consumer<String> errorHandler) {
        try {
            loginChain.proceed(new LoginDetails(uuid, name));
        } catch (LoginProcessingException e) {
            // Exception handling delegated to the client class
            errorHandler.accept("Failed to login, error description - " + e.getMessage());
        }
    }
}
