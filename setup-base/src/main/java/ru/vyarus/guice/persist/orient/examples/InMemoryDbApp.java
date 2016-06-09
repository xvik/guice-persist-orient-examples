package ru.vyarus.guice.persist.orient.examples;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.orientechnologies.orient.core.record.impl.ODocument;
import ru.vyarus.guice.persist.orient.examples.module.DbModule;
import ru.vyarus.guice.persist.orient.examples.service.SampleService;

/**
 * Shows in-memory database usage. In-memory database is self-destructed after application close.
 *
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
public class InMemoryDbApp {

    public static void main(String[] args) {
        // admin/admin is default user, created for each new db
        final Injector injector = Guice.createInjector(new DbModule("memory:sample", "admin", "admin"));

        // to initialize db context we must manually call start
        final PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();

        try {
            final SampleService service = injector.getInstance(SampleService.class);

            System.out.println("Overall records: " + service.count());

            final ODocument rec = service.selectLast();
            System.out.println("Last record: " + rec.toJSON());

            service.replaceName(rec.<String>field("name"), "test");
            // pay attention to record @version property in console
            System.out.println("Renamed record: " + service.findByName("test").toJSON());
            System.out.println("Records count is the same: " + service.count());
        } finally {
            // at the end service must be stopped
            persistService.stop();
        }
    }
}
