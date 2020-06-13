package com.github.jolice.system.dialog.send;

import com.github.jolice.system.dialog.ignore.IgnoreRepository;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.github.jolice.system.punishment.PunishmentChecker;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.github.jolice.messaging.MessagingService;
import com.github.jolice.server.repository.PlayerRepository;
import com.github.jolice.system.dialog.PrivateMessageRepository;

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
