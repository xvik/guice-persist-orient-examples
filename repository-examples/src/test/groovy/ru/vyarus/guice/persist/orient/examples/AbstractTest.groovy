package ru.vyarus.guice.persist.orient.examples

import com.google.inject.Inject
import com.google.inject.persist.PersistService
import com.orientechnologies.orient.core.db.OrientDB
import com.orientechnologies.orient.core.db.object.ODatabaseObject
import ru.vyarus.guice.persist.orient.db.OrientDBFactory
import ru.vyarus.guice.persist.orient.db.PersistentContext
import ru.vyarus.guice.persist.orient.examples.support.TestRepoExamplesModule
import spock.guice.UseModules
import spock.lang.Specification

/**
 * Base class for app tests.
 *
 * @author Vyacheslav Rusakov
 * @since 08.11.2017
 */
@UseModules(TestRepoExamplesModule)
abstract class AbstractTest extends Specification {

    @Inject
    PersistService persistService
    @Inject
    PersistentContext<ODatabaseObject> context
    @Inject
    OrientDBFactory factory

    void setup() {
        persistService.start()
    }

    void cleanup() {
        persistService.stop()
        OrientDB db = factory.createOrientDB()
        if (db.exists(factory.getDbName())) {
            db.drop(factory.getDbName())
        }
        db.close()
    }
}