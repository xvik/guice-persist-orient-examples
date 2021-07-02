package ru.vyarus.guice.persist.orient.examples.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.examples.module.init.ManualSchemeInitializer;

/**
 * Sample service without defined transaction scope. It must be called under already defined unit of work.
 *
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
@Singleton
public class NoTxService {

    @Inject
    private PersistentContext<ODatabaseDocument> context;

    public void doSomething() {
        // it doesn't matter what method actually do
        // the most important part is context.getConnection(), which will fail if method
        // called without defined transaction
        context.getConnection().countClass(ManualSchemeInitializer.CLASS_NAME);
    }
}
