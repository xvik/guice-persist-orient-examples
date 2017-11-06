package ru.vyarus.guice.persis.orient.examples.module;

import com.google.inject.AbstractModule;
import ru.vyarus.guice.persist.orient.OrientModule;
import ru.vyarus.guice.persist.orient.RepositoryModule;
import ru.vyarus.guice.persist.orient.db.data.DataInitializer;
import ru.vyarus.guice.persist.orient.support.AutoScanSchemeModule;
import ru.vyarus.guice.persist.orient.support.PackageSchemeModule;

/**
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
public class RepositoryExamplesModule extends AbstractModule {

    private String url;
    private String user;
    private String password;

    public RepositoryExamplesModule(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    protected void configure() {
        binder().disableCircularProxies();
        install(new OrientModule(url, user, password));

        /*
          Installs all entities annotated with @Persistent annotation
         */
        install(new AutoScanSchemeModule("ru.vyarus.guice.persist.orient.examples"));
        install(new RepositoryModule());
    }
}
