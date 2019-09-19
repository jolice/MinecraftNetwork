package io.riguron.system.shop;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import io.riguron.system.task.TaskFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This component is a part of shop system that is responsible for
 * storing active players' transactions that are waiting for the player's
 * confirmation.
 */
@RequiredArgsConstructor
public class ActivePurchases {

    private final PlayerPurchaseService playerPurchaseService;
    private final TaskFactory taskFactory;
    private final Map<UUID, Transaction> transactions = new ConcurrentHashMap<>();

    /**
     * Registers a transaction that will be (or not) confirmed by the player.
     *
     * @param playerId    ID of player that purchases an item
     * @param purchasable item being purchased
     * @param onSuccess   callback that will be executed in case of successful purchase
     */
    public void addPurchase(UUID playerId, Purchasable purchasable, Runnable onSuccess) {
        Transaction transaction = new Transaction(purchasable, onSuccess, playerId);
        this.transactions.put(playerId, transaction);
    }

    public void removeTransaction(UUID from) {
        transactions.remove(from);
    }

    /**
     * Confirms the purchase and removes the transaction.
     *
     * @param playerId ID of transaction owner
     */
    public void confirmPurchase(UUID playerId) {
        Transaction transaction = transactions.get(playerId);
        Purchasable purchasable = transaction.getPurchasable();
        taskFactory.newAsyncTask(() -> {
            playerPurchaseService.purchase(playerId, purchasable);
            transactions.remove(playerId);
            taskFactory.newSyncTask(() -> transaction.getOnSuccess().run()).execute();
        }).execute();

    }

    @Value
    private static class Transaction {

        private Purchasable purchasable;
        private Runnable onSuccess;
        private UUID playerId;

    }
}
