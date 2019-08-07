package me.riguron.system.shop;

import me.riguron.system.task.Task;
import me.riguron.system.task.TaskFactory;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ActivePurchasesTest {

    @Test
    public void whenAddThenConfirm() {

        TaskFactory taskFactory = mock(TaskFactory.class);

        when(taskFactory.newAsyncTask(any())).thenAnswer(mockTask());
        when(taskFactory.newSyncTask(any())).thenAnswer(mockTask());

        PlayerPurchaseService purchaseService = mock(PlayerPurchaseService.class);

        UUID uuid = UUID.randomUUID();
        Purchasable purchasable = mock(Purchasable.class);
        Runnable runnable = mock(Runnable.class);

        ActivePurchases activePurchases = new ActivePurchases(purchaseService, taskFactory);

        activePurchases.addPurchase(uuid, purchasable, runnable);

        activePurchases.confirmPurchase(uuid);
        verify(purchaseService).purchase(eq(uuid), eq(purchasable));
        verify(runnable).run();

    }

    private Answer<Task> mockTask() {
        return invocation -> {
                    Runnable runnable = invocation.getArgument(0);
                    return runnable::run;
                };
    }
}