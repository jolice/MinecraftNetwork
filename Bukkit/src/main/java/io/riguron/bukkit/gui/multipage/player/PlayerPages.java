package io.riguron.bukkit.gui.multipage.player;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.IntUnaryOperator;

@RequiredArgsConstructor
public class PlayerPages {

    private final Map<UUID, Integer> pages = new HashMap<>();

    public boolean hasSession(UUID uuid) {
        return pages.containsKey(uuid);
    }

    public void clear() {
        pages.clear();
    }

    public Set<UUID> getActiveSessions() {
        return pages.keySet();
    }

    public Set<UUID> rearrangeAfterRemoval(Integer removedPage) {
        final Iterator<Map.Entry<UUID, Integer>> iterator = pages.entrySet().iterator();
        Set<UUID> result = new HashSet<>();
        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            if (entry.getValue().equals(removedPage)) {
                result.add(entry.getKey());
                iterator.remove();
            } else if (entry.getValue() > removedPage) {
                entry.setValue(entry.getValue() - 1);
            }
        }
        return result;
    }

    public Integer getPlayerPage(UUID id) {
        return pages.get(id);
    }

    public int switchPage(UUID uuid, IntUnaryOperator operator) {
        int currentPage = pages.get(uuid);
        int nextPage = operator.applyAsInt(currentPage);
        pages.put(uuid, nextPage);
        return nextPage;
    }

    public void setPlayerPage(UUID id, Integer pageId) {
        pages.put(id, pageId);
    }

    public void removePlayer(UUID id) {
        pages.remove(id);
    }
}
