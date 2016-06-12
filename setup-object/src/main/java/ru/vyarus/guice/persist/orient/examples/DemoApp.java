package ru.vyarus.guice.persist.orient.examples;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import ru.vyarus.guice.persist.orient.examples.model.Sample;
import ru.vyarus.guice.persist.orient.examples.module.DbModule;
import ru.vyarus.guice.persist.orient.examples.service.SampleService;

/**
 * Example of object database usage (using in-memory storage).
 *
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
public class DemoApp {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new DbModule("memory:sample", "admin", "admin"));
        final PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();

        try {
            final SampleService service = injector.getInstance(SampleService.class);

            System.out.println("Overall records: " + service.count());
            System.out.println("Overall records with query: " + service.count2());

            Sample sample = service.selectLast();
            // not detached entity will not contain data outside of transaction (its a proxy!)
            System.out.println("Last record (not detached): " + sample);
            sample = service.detach(sample);
            System.out.println("Last record (detached): " + sample);

            // without detaching name would be null
            service.replaceName(sample.getName(), "test");
            // pay attention to record @version property in console
            System.out.println("Renamed record: " + service.detach(service.findByName("test")));
            System.out.println("Records count is the same: " + service.count());
        } finally {
            // at the end service must be stopped
            persistService.stop();
        }
    }
}
