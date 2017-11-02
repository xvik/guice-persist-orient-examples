package ru.vyarus.guice.persist.orient.examples.support

import com.google.inject.AbstractModule
import ru.vyarus.guice.persist.orient.examples.module.RepoDbModule

/**
 * Guice module used for tests. Defines in-memory test db.
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
class TestRepoDbModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RepoDbModule("memory:test", "admin", "admin"))
    }
}
