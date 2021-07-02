package ru.vyarus.guice.persist.orient.examples

import com.google.inject.Inject
import com.google.inject.persist.PersistService
import com.orientechnologies.orient.core.db.object.ODatabaseObject
import ru.vyarus.guice.persist.orient.db.PersistentContext
import ru.vyarus.guice.persist.orient.examples.support.TestObjectDbModule
import spock.guice.UseModules
import spock.lang.Specification

/**
 * Base class for app tests.
 *
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
@UseModules(TestObjectDbModule)
abstract class AbstractTest extends Specification {

    @Inject
    PersistService persistService
    @Inject
    PersistentContext<ODatabaseObject> context

    void setup() {
        persistService.start()
    }

    void cleanup() {
        persistService.stop()
    }
}