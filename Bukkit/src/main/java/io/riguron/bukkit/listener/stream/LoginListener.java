package io.riguron.bukkit.listener.stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.riguron.system.login.chain.LoginChainExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

@RequiredArgsConstructor
@Slf4j
public class LoginListener implements Listener {

    private final LoginChainExecutor loginChainExecutor;

    @EventHandler
    public void preLogin(AsyncPlayerPreLoginEvent event) {
        loginChainExecutor.execute(event.getUniqueId(), event.getName(), s -> {
            event.setKickMessage(s);
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
        });
    }
}
