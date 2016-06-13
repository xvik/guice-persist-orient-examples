package ru.vyarus.guice.persist.orient.examples.module.init;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.db.data.DataInitializer;

import static ru.vyarus.guice.persist.orient.examples.module.init.ManualSchemeInitializer.CLASS_NAME;

/**
 * Init database data (when table is empty) using graph api.
 *
 * @author Vyacheslav Rusakov
 * @since 13.06.2016
 */
@Transactional
public class SampleDataInitializer implements DataInitializer {

    @Inject
    private PersistentContext<OrientBaseGraph> context;

    @Override
    public void initializeData() {
        final OrientBaseGraph db = context.getConnection();

        // init only empty database
        if (db.countVertices(CLASS_NAME) > 0) {
            return;
        }

        Vertex previous = null;
        for (int i = 0; i < 10; i++) {
            // note usage of "class:" prefix - it is required to reference registered class,
            // otherwise orient could try to register new class
            final Vertex node = db.addVertex("class:" + CLASS_NAME,
                    "name", "Sample" + i,
                    "amount", (int) (Math.random() * 200));
            if (previous != null) {
                // bottom-up connection: second Sample BelongsTo previous sample (graph is directed)
                node.addEdge("belongsTo", previous);
            }
            previous = node;
        }
    }
}
