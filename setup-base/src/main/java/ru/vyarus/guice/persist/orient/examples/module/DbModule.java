package ru.vyarus.guice.persist.orient.examples.module;

import com.google.inject.AbstractModule;
import ru.vyarus.guice.persist.orient.OrientModule;
import ru.vyarus.guice.persist.orient.db.data.DataInitializer;
import ru.vyarus.guice.persist.orient.db.scheme.SchemeInitializer;
import ru.vyarus.guice.persist.orient.examples.module.init.ManualSchemeInitializer;
import ru.vyarus.guice.persist.orient.examples.module.init.SampleDataInitializer;

/**
 * Database module, configured for your needs.
 *
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
public class DbModule extends AbstractModule {

    private String url;
    private String user;
    private String password;

    public DbModule(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    protected void configure() {
        install(new OrientModule(url, user, password));

        // (optional) bind custom scheme initializer to create demo db scheme
        bind(SchemeInitializer.class).to(ManualSchemeInitializer.class);

        // (optional) bind custom data initializer to fill demo db with data
        bind(DataInitializer.class).to(SampleDataInitializer.class);
    }
}
