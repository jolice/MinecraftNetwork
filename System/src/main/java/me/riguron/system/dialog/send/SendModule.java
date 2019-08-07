package me.riguron.system.dialog.send;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import me.riguron.messaging.MessagingService;
import me.riguron.server.repository.PlayerRepository;
import me.riguron.system.dialog.PrivateMessageRepository;
import me.riguron.system.dialog.ignore.IgnoreRepository;
import me.riguron.system.player.PlayerProfileRepository;
import me.riguron.system.punishment.PunishmentChecker;

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
