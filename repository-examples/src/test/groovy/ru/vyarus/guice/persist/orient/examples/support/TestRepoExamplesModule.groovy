package ru.vyarus.guice.persist.orient.examples.support

import com.google.inject.AbstractModule
import ru.vyarus.guice.persist.orient.examples.module.RepositoryExamplesModule

/**
 * Guice module used for tests. Defines in-memory test db.
 *
 * @author Vyacheslav Rusakov
 * @since 08.11.2017
 */
class TestRepoExamplesModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new RepositoryExamplesModule("memory:test", "admin", "admin"))
    }
}
