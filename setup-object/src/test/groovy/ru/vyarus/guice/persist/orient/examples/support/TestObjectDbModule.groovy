package ru.vyarus.guice.persist.orient.examples.support

import com.google.inject.AbstractModule
import ru.vyarus.guice.persist.orient.examples.module.ObjectDbModule

/**
 * Guice module used for tests. Defines in-memory test db.
 *
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
class TestObjectDbModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ObjectDbModule("memory:test", "admin", "admin"))
    }
}
