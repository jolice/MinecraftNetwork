package com.github.jolice.system.test;

import io.ebean.EbeanServer;
import io.ebean.test.LoggedSql;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
public abstract class RepositoryTest<T> {

    protected EbeanServer ebean;
    protected T repository;

    public RepositoryTest() {
        EbeanTesting ebeanTesting = new EbeanTesting();
        this.ebean = ebeanTesting.getEbeanServer();
        this.repository = createRepository(ebean);
    }

    protected abstract T createRepository(EbeanServer server);

    protected void runAndAssertQueries(int queries, Runnable runnable) {
        runAndAssertQueries(queries, () -> {
            runnable.run();
            return null;
        });
    }

    protected <R> R runAndAssertQueries(int queries, Supplier<R> supplier) {
        LoggedSql.start();
        R result = supplier.get();
        List<String> executedQueries = LoggedSql.stop();
        int size = (int) executedQueries.stream().filter(x -> !x.contains("-- bind")).count();
        if (size != queries) {
            log.info("*** Queries executed *** \n");
            for (int i = 0; i < executedQueries.size(); i++) {
                log.info(String.format("* %d. %s\n", i + 1, executedQueries.get(i)));
            }
            throw new AssertionError("Expected " + queries + " queries, but " + size + " were executed");
        }
        return result;
    }

}
