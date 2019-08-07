package me.riguron.system.login;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.name.Named;
import me.riguron.system.login.chain.DefaultChainExecutor;
import me.riguron.system.login.chain.LoggingChainExecutor;
import me.riguron.system.login.chain.LoginChainExecutor;
import me.riguron.system.login.chain.LoginChainLink;
import me.riguron.system.player.PlayerProfileLoader;
import me.riguron.system.player.PlayerProfileRepository;
import me.riguron.system.punishment.PunishmentChecker;
import me.riguron.system.punishment.PunishmentLink;
import me.riguron.system.punishment.repository.PunishmentDataRepository;
import me.riguron.system.rank.Ranks;

public class LoginProcessingModule extends AbstractModule {

    @Provides
    @Singleton
    @Named("ProfileLoader")
    public LoginChainLink playerProfileLoader(PlayerProfileRepository playerProfileRepository, Ranks ranks) {
        return new PlayerProfileLoader(playerProfileRepository, ranks);
    }

    @Provides
    @Named("Punishments")
    @Singleton
    public LoginChainLink punishmentLink(PunishmentChecker punishmentChecker, PunishmentDataRepository punishmentDataRepository, @Named("ProfileLoader") LoginChainLink profileLoader) {
        return new PunishmentLink(punishmentChecker, punishmentDataRepository).linkWith(profileLoader);
    }

    @ProvidesIntoOptional(ProvidesIntoOptional.Type.DEFAULT)
    @Singleton
    public LoginChainLink link(@Named("Punishments") LoginChainLink link) {
        return link;
    }

    @Provides
    @Singleton
    public LoginChainExecutor loginChainExecutor(LoginChainLink loginChainLink) {
        return new LoggingChainExecutor(new DefaultChainExecutor(loginChainLink));
    }


}

