package com.github.jolice.system.login;

import com.github.jolice.system.login.chain.DefaultChainExecutor;
import com.github.jolice.system.login.chain.LoggingChainExecutor;
import com.github.jolice.system.login.chain.LoginChainExecutor;
import com.github.jolice.system.login.chain.LoginChainLink;
import com.github.jolice.system.player.PlayerProfileLoader;
import com.github.jolice.system.player.PlayerProfileRepository;
import com.github.jolice.system.punishment.PunishmentChecker;
import com.github.jolice.system.punishment.PunishmentLink;
import com.github.jolice.system.punishment.repository.PunishmentDataRepository;
import com.github.jolice.system.rank.Ranks;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoOptional;
import com.google.inject.name.Named;

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

