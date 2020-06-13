package io.riguron.bungee.data;

import lombok.RequiredArgsConstructor;
import io.riguron.system.task.FixedRepeatingAction;

@RequiredArgsConstructor
public class GlobalOnlineUpdater implements FixedRepeatingAction {

    private final GlobalOnline globalOnline;

    @Override
    public void run() {
        globalOnline.update();
    }

    @Override
    public int getInterval() {
        return 1;
    }
}
