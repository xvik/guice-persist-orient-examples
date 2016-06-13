package ru.vyarus.guice.persist.orient.examples

import com.google.inject.Inject
import com.google.inject.persist.PersistService
import com.orientechnologies.orient.core.Orient
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph
import ru.vyarus.guice.persist.orient.db.PersistentContext
import ru.vyarus.guice.persist.orient.examples.support.TestGraphDbModule
import spock.guice.UseModules
import spock.lang.Specification

/**
 * Base class for app tests.
 *
 * @author Vyacheslav Rusakov
 * @since 14.06.2016
 */
@UseModules(TestGraphDbModule)
abstract class AbstractTest extends Specification {

    @Inject
    PersistService persistService
    @Inject
    PersistentContext<OrientBaseGraph> context

    void setup() {
        persistService.start()
    }

    void cleanup() {
        persistService.stop()
        // killing storage to drop database and grant fresh state between tests
        Orient.instance().closeAllStorages()
    }
}