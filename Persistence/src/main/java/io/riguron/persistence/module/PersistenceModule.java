package io.riguron.persistence.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.config.ServerConfig;
import io.ebeaninternal.server.core.DefaultContainer;
import io.riguron.common.shutdown.ShutdownHooks;
import io.riguron.config.properties.PropertiesFactory;
import io.riguron.persistence.ebean.EbeanPatch;
import io.riguron.persistence.entity.EntityGroup;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersistenceModule extends AbstractModule {

    private static final String DATABASE_PROPERTIES = "datasource.properties";

    static {
        EbeanPatch.apply();
    }

    @Provides
    @Singleton
    public DataSource dataSource(ShutdownHooks shutdownHooks, PropertiesFactory propertiesFactory) {
        HikariConfig hikariConfig = new HikariConfig(propertiesFactory.newPropertiesLoader(DATABASE_PROPERTIES).load());
        hikariConfig.setAutoCommit(false);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        shutdownHooks.addHook(hikariDataSource::close);
        return hikariDataSource;
    }

    @Provides
    @Singleton
    public EbeanServer ebeanServerFactory(ServerConfig serverConfig) {
        EbeanServer server = new DefaultContainer(serverConfig.getContainerConfig()).createServer(serverConfig);
        Ebean.register(server, true);
        return server;
    }

    @Provides
    @Singleton
    public ServerConfig serverConfig(DataSource dataSource, @Named("Entities") List<Class<?>> classes) {
        ServerConfig serverConfig = new ServerConfig();

        serverConfig.setDdlCreateOnly(true);
        serverConfig.setDdlGenerate(true);
        serverConfig.setDdlExtra(false);
        serverConfig.setDdlRun(true);
        serverConfig.setDefaultServer(true);
        serverConfig.setRegister(true);
        serverConfig.setDataSource(dataSource);
        serverConfig.setClasses(classes);
        return serverConfig;
    }



    @Singleton
    @Provides
    @Named("Entities")
    public List<Class<?>> entities(Set<EntityGroup> entityGroups) {
        return entityGroups.stream().flatMap(x -> x.getEntities().stream()).collect(Collectors.toList());
    }

}
