package io.riguron.game.timer;

import lombok.RequiredArgsConstructor;
import org.bukkit.Server;
import org.bukkit.Sound;
import io.riguron.system.task.timer.CountdownTask;

/**
 * Decorator that plays a sound when the countdown approaches to the zero.
 */
@RequiredArgsConstructor
public class SoundPlayingCountdown implements CountdownTask {

    private final CountdownTask delegate;
    private final Server server;

    @Override
    public void onTick(long tick) {
        if (tick == 10 || tick < 5) {
            server
                    .getOnlinePlayers()
                    .forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0F, 2.0F));
        }
        delegate.onTick(tick);
    }

    @Override
    public void complete() {
        delegate.complete();
    }
}
