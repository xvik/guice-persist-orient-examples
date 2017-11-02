package ru.vyarus.guice.persist.orient.examples;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import ru.vyarus.guice.persist.orient.examples.model.Sample;
import ru.vyarus.guice.persist.orient.examples.module.RepoDbModule;
import ru.vyarus.guice.persist.orient.examples.repository.SampleRepository;

import java.util.List;

/**
 * Example of object database usage (using in-memory storage).
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
public class RepoDemoApp {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new RepoDbModule("memory:sample", "admin", "admin"));
        final PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();

        try {
            final SampleRepository repository = injector.getInstance(SampleRepository.class);

            System.out.println("Overall records: " + repository.count());

            Sample sample = repository.first();
            // not detached entity will not contain data outside of transaction (its a proxy!)
            System.out.println("Last record (not detached): " + sample);
            // use list here just to show how multiple entities could be returned
            List<Sample> samples = repository.selectDetached();
            sample = samples.get(0);
            System.out.println("Detached record: " + sample);

            // without detaching name would be null
            int res = repository.updateName("test", sample.getName());
            System.out.println("Rename result: " + res);
            // pay attention to record @version property in console
            System.out.println("Renamed record: " + repository.findByNameDetached("test"));
            System.out.println("Records count is the same: " + repository.count());

            System.out.println("Document search: " + repository.findDocumentByName("test"));
        } finally {
            // at the end service must be stopped
            persistService.stop();
        }
    }
}
