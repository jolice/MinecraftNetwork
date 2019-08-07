package me.riguron.bukkit.task.timer;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.junit.Test;
import me.riguron.bukkit.task.BukkitTimerFactory;
import me.riguron.system.task.TimerFactory;
import me.riguron.system.task.timer.CountdownTask;
import me.riguron.system.task.timer.TaskOptions;
import me.riguron.system.task.timer.Timer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.doAnswer;

public class BukkitTimerFactoryTest {

    private static final Object LOCK = new Object();

    private TimerThread timerThread;

    @Test(timeout = 5000L)
    public void doTestTimer() {

        Plugin plugin = mock(Plugin.class);
        BukkitScheduler bukkitScheduler = mock(BukkitScheduler.class);

        TimerFactory timerFactory = new BukkitTimerFactory(plugin, bukkitScheduler);

        CountdownTask countdownTask = mock(CountdownTask.class);

        when(bukkitScheduler.runTaskTimer(any(), any(Runnable.class), anyLong(), anyLong()))
                .thenAnswer(invocationOnMock -> {
                    timerThread = new TimerThread();
                    timerThread.runnable = invocationOnMock.getArgument(1);
                    timerThread.setDaemon(false);
                    timerThread.start();
                    final BukkitTask mock = mock(BukkitTask.class);
                    when(mock.getTaskId()).thenReturn(10);
                    return mock;
                });


        doAnswer(invocationOnMock -> {
            timerThread.reinUp();
            return null;
        }).when(bukkitScheduler).cancelTask(anyInt());

        Timer timer = timerFactory.newTimer(countdownTask, new TaskOptions(100));

        synchronized (LOCK) {
            timer.start();
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        verify(countdownTask, times(100)).onTick(anyLong());
        verify(countdownTask).complete();
    }

    static class TimerThread extends Thread {

        private volatile Runnable runnable;
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                synchronized (LOCK) {
                    runnable.run();
                }
            }
        }

        private void reinUp() {
            synchronized (LOCK) {
                LOCK.notifyAll();
                running = false;
            }
        }

    }
}
