package io.riguron.common;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.riguron.common.shutdown.ShutdownHook;
import io.riguron.common.shutdown.ShutdownHooks;

import java.util.Set;

public class CommonModule extends AbstractModule {

    @Provides
    @Singleton
    public ShutdownHooks shutdownHooks(Set<ShutdownHook> hooks) {
        return new ShutdownHooks(hooks);
    }


}
