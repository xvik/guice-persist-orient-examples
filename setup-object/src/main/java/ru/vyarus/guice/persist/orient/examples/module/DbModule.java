package ru.vyarus.guice.persist.orient.examples.module;

import com.google.inject.AbstractModule;
import ru.vyarus.guice.persist.orient.OrientModule;
import ru.vyarus.guice.persist.orient.db.data.DataInitializer;
import ru.vyarus.guice.persist.orient.examples.model.Sample;
import ru.vyarus.guice.persist.orient.examples.module.init.SampleDataInitializer;
import ru.vyarus.guice.persist.orient.support.PackageSchemeModule;

/**
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
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

        /**
         * Package scheme module will register custom SchemeInitializer, which will lookup all
         * classes inside provided package(s) and register all classes in database.
         * Schema registration is a "native" orient logic, but it's extended with special annotations
         * support (e.g. to create indexes, field constraints etc.)
         *
         * Also, there is an auto scan module, but it will be showed in next sample.
         */
        install(new PackageSchemeModule(Sample.class.getPackage().getName()));

        // (optional) bind custom data initializer to fill demo db with data
        bind(DataInitializer.class).to(SampleDataInitializer.class);
    }
}
