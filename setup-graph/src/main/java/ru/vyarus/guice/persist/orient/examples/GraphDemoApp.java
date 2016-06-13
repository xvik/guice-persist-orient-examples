package ru.vyarus.guice.persist.orient.examples;

import com.google.common.base.Joiner;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import ru.vyarus.guice.persist.orient.examples.module.GraphDbModule;
import ru.vyarus.guice.persist.orient.examples.service.SampleService;

import java.util.List;

/**
 * Example of graph database usage (using in-memory storage).
 *
 * @author Vyacheslav Rusakov
 * @since 13.06.2016
 */
public class GraphDemoApp {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new GraphDbModule("memory:sample", "admin", "admin"));
        final PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();

        try {
            final SampleService service = injector.getInstance(SampleService.class);

            System.out.println("Overall records: " + service.count());
            // if you commit edge type creation in scheme initializer you will see that edge was created automatically
            // but with warning
            System.out.println("Edge types: " + Joiner.on(", ").join(service.getEdgeClasses()));

            final Vertex sample = service.selectLast();
            System.out.println("Last record: " + sample);
            System.out.println("By the way, it's backed by document: " + ((OrientVertex) sample).getRecord().toJSON());

            service.replaceName(sample.<String>getProperty("name"), "test");
            // pay attention to record @version property in console
            final Vertex renamed = service.findByName("test");
            System.out.println("Renamed record: " + renamed + " name=" + renamed.getProperty("name"));
            System.out.println("Records count is the same: " + service.count());

            System.out.println();
            Vertex parent = service.getParent(renamed.<String>getProperty("name"));
            System.out.println("Parent record: " + parent + " with name " + parent.getProperty("name"));
            List<Vertex> children = service.getChildren(parent.<String>getProperty("name"));
            System.out.println("Parent children: " + Joiner.on(", ").join(children));

            // now add new child to node already having a child
            System.out.println();
            final Vertex child = service.addChild(parent, "Child!", 12);
            System.out.println("Added new element: " + child);
            System.out.println("Records count now is: " + service.count());

            children = service.getChildren(parent.<String>getProperty("name"));
            System.out.println("Parent children after addition: " + Joiner.on(", ").join(children));

            children = service.getChildren2(parent);
            System.out.println("Parent children after addition (using api): " + Joiner.on(", ").join(children));

        } finally {
            // at the end service must be stopped
            persistService.stop();
        }
    }
}
