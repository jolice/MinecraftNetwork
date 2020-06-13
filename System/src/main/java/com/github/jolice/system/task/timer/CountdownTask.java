package com.github.jolice.system.task.timer;

public interface CountdownTask {

    /**
     * Represents countdown step. In case of the countdown from X to 0,
     * this method is called exactly X times.
     *
     * @param tick time left until the end of the countdown
     */
    void onTick(long tick);

    /**
     * Action performed when the countdown ends. This method is always triggered
     * once, when the countdown reaches zero.
     */
    void complete();
}
