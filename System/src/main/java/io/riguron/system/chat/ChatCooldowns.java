package io.riguron.system.chat;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Simple chat watcher that introduces chat cooldowns.
 */
@RequiredArgsConstructor
public class ChatCooldowns {

    private final int cooldown;
    private final Map<UUID, Integer> cooldowns = new HashMap<>();

    /**
     * Checks whether the player is on cooldown (i.e has to wait some time to
     * continue sending chat messages).
     *
     * @param player id of the player
     * @return optional containing time in seconds left until the end of the cooldown,
     * or empty optional if player is not on the cooldown and may chat at the moment.
     */
    public Optional<Integer> checkCooldown(UUID player) {
        return Optional.ofNullable(cooldowns.compute(player, (uuid, lastChatTime) -> {
            int now = currentTime();
            if (lastChatTime == null || now - lastChatTime >= cooldown) {
                return null;
            } else {

                return cooldown - (now - lastChatTime);
            }
        }));
    }

    /**
     * Removes cooldown data for player. May be useful for invalidating cooldown
     * cache, for example, when the player leaves.
     *
     * @param player id of the player to be invalidated
     */
    public void removePlayer(UUID player) {
        cooldowns.remove(player);
    }

    /**
     * This method must be called by the chat listener when players performs the chat action.
     * It sets player on cooldown, i.e player is not able to chat for N seconds after this method
     * is called.
     *
     * @param uuid id of the player that is being set cooling down
     */
    public void onChat(UUID uuid) {
        this.cooldowns.put(uuid, currentTime());
    }

    private int currentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
