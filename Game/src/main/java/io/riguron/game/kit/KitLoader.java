package io.riguron.game.kit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.riguron.game.config.GameOptions;
import io.riguron.system.task.startup.PostLoadTask;

import java.util.List;

/**
 * Task responsible for loading kits into memory.
 */
@Slf4j
@RequiredArgsConstructor
public class KitLoader implements PostLoadTask {

    private final Kits kits;
    private final KitRepository kitRepository;
    private final GameOptions gameOptions;

    @Override
    public void run() {
        List<Kit> kitList = kitRepository.findKitsByGameName(gameOptions.getGameName());
        log.info("Loaded {} kits", kitList.size());
        kitList.forEach(kits::addKit);
    }
}
