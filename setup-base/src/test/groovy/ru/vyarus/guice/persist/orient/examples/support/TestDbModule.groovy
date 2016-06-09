package ru.vyarus.guice.persist.orient.examples.support

import com.google.inject.AbstractModule
import ru.vyarus.guice.persist.orient.examples.module.DbModule

/**
 * Guice module used for tests. Defines in-memory test db.
 *
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
class TestDbModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DbModule("memory:test", "admin", "admin"))
    }
}
