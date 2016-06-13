package ru.vyarus.guice.persist.orient.examples.module.init;

import com.google.inject.Inject;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientEdgeType;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.db.scheme.SchemeInitializer;

/**
 * Manual scheme initialization using graph api only.
 * Creates Sample vertex type and BelongsTo edge type.
 *
 * @author Vyacheslav Rusakov
 * @since 13.06.2016
 */
public class ManualSchemeInitializer implements SchemeInitializer {

    public static final String CLASS_NAME = "Sample";

    /**
     * It's better to use OrientBaseGraph to handle both possible case: OrientGraph or OrientGraphNoTx
     * (transactional and non transactional graphs).
     */
    @Inject
    private PersistentContext<OrientBaseGraph> context;

    @Override
    public void initialize() {

        final OrientBaseGraph db = context.getConnection();

        /**
         * Avoid creating scheme if table already exist.
         * Look method implementation: internally it use document api the same way as it was used in
         * document sample.
         */
        if (db.getVertexType(CLASS_NAME) != null) {
            return;
        }

        /**
         * In database scheme the only difference with other apis would be that
         * Sample class would extend "V" in scheme (which marks type as Vertex type).
         */
        final OrientVertexType vtype = db.createVertexType(CLASS_NAME);
        vtype.createProperty("name", OType.STRING);
        vtype.createProperty("amount", OType.INTEGER);

        // registration of graph type is not mandatory (orient will create it on-the-fly)
        // but with warning

        // also note that edge name is case-insensitive and so used as "belongsTo" everywhere in code
        db.createEdgeType("BelongsTo");
    }
}
