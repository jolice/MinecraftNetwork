package com.github.jolice.system.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;

import javax.sql.DataSource;

public class EbeanTesting {

    private EbeanServer ebeanServer;

    public EbeanTesting() {
        build();
    }

    private void build() {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setDdlGenerate(true);
        serverConfig.setDdlRun(true);


        serverConfig.setRegister(true);
        serverConfig.setDataSource(dataSource());
        ebeanServer = EbeanServerFactory.create(serverConfig);
    }


    private DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.h2.Driver");
        hikariConfig.setJdbcUrl("jdbc:h2:mem:test");
        return new HikariDataSource(hikariConfig);
    }


    public EbeanServer getEbeanServer() {
        return ebeanServer;
    }
}
