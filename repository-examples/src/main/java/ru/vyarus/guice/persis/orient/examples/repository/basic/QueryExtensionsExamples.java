package ru.vyarus.guice.persis.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import ru.vyarus.guice.persis.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.db.retry.Retry;
import ru.vyarus.guice.persist.orient.repository.command.ext.fetchplan.FetchPlan;
import ru.vyarus.guice.persist.orient.repository.command.ext.timeout.Timeout;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;

import java.util.List;

/**
 * @author Vyacheslav Rusakov
 * @since 03.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface QueryExtensionsExamples {

    // Fetch plan

    // define query fetch plan (or null to not use fetch plan)
    @Query("select from Model")
    Model selectBasketNoDefault(@FetchPlan String plan);

    // fetch plan with default value (used when null specified)
    @Query("select from Model")
    Model selectBasket(@FetchPlan("*:0") String plan);


    // Query timeout

    // wait for result for 200ms
    @Timeout(200)
    @Query("select from Model")
    List<Model> allTimed();


    // Retry. Due to various reasons, even simple update command could lead to ONeedRetryException exception, which
    // means that execution were failed but it could succeed next time. Retry annotation will intercept such exception
    // and retry query.
    // NOTE this is not repository extension and may be used anywhere (any guice bean could be annotated), but it must
    // be outside of transaction (because it re-executes entire transaction)

    @Retry(100)
    @Query("update model set name=?")
    void updateWithRetry(String name);
}
