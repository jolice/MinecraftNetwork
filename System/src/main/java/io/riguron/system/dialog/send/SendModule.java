package io.riguron.system.dialog.send;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.riguron.messaging.MessagingService;
import io.riguron.server.repository.PlayerRepository;
import io.riguron.system.dialog.ignore.IgnoreRepository;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.dialog.PrivateMessageRepository;
import io.riguron.system.dialog.ignore.IgnoreRepository;
import io.riguron.system.player.PlayerProfileRepository;
import io.riguron.system.punishment.PunishmentChecker;

public class SendModule extends AbstractModule {

    @Provides
    @Singleton
    public CheckTarget checkTarget(IgnoreRepository ignoreRepository, FinalStage finalStage) {
        return new CheckTarget(ignoreRepository, finalStage);
    }

    @Provides
    @Singleton
    public FinalStage finalStage(MessagingService messagingService, PlayerProfileRepository repository, PrivateMessageRepository privateMessageRepository) {
        return new FinalStage(messagingService, repository, privateMessageRepository);
    }

    @Provides
    @Singleton
    public FindPlayerProfile sendToPlayer(PlayerProfileRepository repository, CheckTarget checkTarget) {
        return new FindPlayerProfile(repository, checkTarget);
    }

    @Provides
    @Singleton
    public FindOnlinePlayer findAndSend(PlayerRepository playerRepository, FindPlayerProfile findPlayerProfile) {
        return new FindOnlinePlayer(playerRepository, findPlayerProfile);
    }

    @Provides
    @Singleton
    public SendPrivateMessage sendPrivateMessage(PunishmentChecker punishmentChecker, FindOnlinePlayer findOnlinePlayer) {
        return new SendPrivateMessage(punishmentChecker, findOnlinePlayer);
    }



}
