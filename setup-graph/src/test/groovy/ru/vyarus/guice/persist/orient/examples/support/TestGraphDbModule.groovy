package ru.vyarus.guice.persist.orient.examples.support

import com.google.inject.AbstractModule
import ru.vyarus.guice.persist.orient.examples.module.GraphDbModule

/**
 * Guice module used for tests. Defines in-memory test db.
 *
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
class TestGraphDbModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new GraphDbModule("memory:test", "admin", "admin"))
    }
}
