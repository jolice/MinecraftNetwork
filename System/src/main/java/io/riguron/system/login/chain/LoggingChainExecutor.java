package io.riguron.system.login.chain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Logging and benchmarking decorator for the main executor.
 */
@RequiredArgsConstructor
@Slf4j
public class LoggingChainExecutor implements LoginChainExecutor {

    private final LoginChainExecutor loginChainExecutor;

    @Override
    public void execute(UUID uuid, String name, Consumer<String> errorHandler) {
        long was = currentTime();
        try {
            loginChainExecutor.execute(uuid, name, errorHandler);
        } finally {
            log.info("Processed player {} in {} ms", name, currentTime() - was);
        }
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }
}
