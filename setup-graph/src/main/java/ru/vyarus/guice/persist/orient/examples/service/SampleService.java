package ru.vyarus.guice.persist.orient.examples.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientBaseGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import ru.vyarus.guice.persist.orient.db.PersistentContext;

import javax.inject.Singleton;
import java.util.List;

import static ru.vyarus.guice.persist.orient.examples.module.init.ManualSchemeInitializer.CLASS_NAME;

/**
 * @author Vyacheslav Rusakov
 * @since 13.06.2016
 */
@Singleton
@Transactional
public class SampleService {

    @Inject
    private PersistentContext<OrientBaseGraph> context;

    public long count() {
        return context.getConnection().countVertices(CLASS_NAME);
    }

    public Vertex selectLast() {
        // edge relation build in bottom-up fashion, so looking for node without incoming edge
        final List<Vertex> res = execute("select from " + CLASS_NAME + " where in('belongsTo').size() = 0");
        return res.size() == 0 ? null : res.get(0);
    }

    public Vertex findByName(String name) {
        // query with parameter
        final List<Vertex> res = execute("select from Sample where name = ?", name);
        return res.size() == 0 ? null : res.get(0);
    }

    public void replaceName(String name, String to) {
        context.getConnection().command(new OCommandSQL(
                "update " + CLASS_NAME + " set name = ? where name = ?"))
                .execute(to, name);
    }

    public Vertex getParent(String name) {
        // our connection is:  NODE1 <-- BelongsTo -- NODE2
        // where NODE1 is parent and NODE2 is child
        final List<Vertex> res = execute(
                "select expand(out('belongsTo')) from " + CLASS_NAME + " where name = ?", name);
        return res.size() == 0 ? null : res.get(0);
    }

    public List<Vertex> getChildren(String name) {
        return execute("select expand(in('belongsTo')) from " + CLASS_NAME + " where name = ?", name);
    }

    public List<Vertex> getChildren2(Vertex parent) {
        // in alternative to sql, graph could be traversed directly (very handy when we know root to start searching from)

        // important because entity itself is not aware of current transaction
        // (transactions are driven by @Transactional annotation and so scoped to method)
        context.getConnection().attach((OrientVertex) parent);

        List<Vertex> res = Lists.newArrayList(parent.getVertices(Direction.IN, "belongsTo"));
        // demo uses not proper transactions (normally you shouldn't work with objects outside of transaction)
        // without detaching not cached (Sample9) vertex will have null name (simply not loaded)
        for (Vertex r : res) {
            ((OrientVertex) r).detach();
        }
        return res;
    }

    public Vertex addChild(Vertex parent, String name, int amount) {
        final Vertex child = context.getConnection().addVertex("class:" + CLASS_NAME, "name", name, "amount", amount);
        child.addEdge("belongsTo", parent);
        return child;
    }

    private List<Vertex> execute(String command, Object... args) {
        // Graph api returns Iterable but not list, so applying manual conversion (for simplicity,
        // but its not a good idea for production)
        return Lists.newArrayList((Iterable<Vertex>) context.getConnection().command(new OSQLSynchQuery<Vertex>(
                command)).execute(args));
    }

    public List<String> getEdgeClasses() {
        // return registered tables for edges (extending base E type)
        return Lists.transform(
                Lists.newArrayList(context.getConnection().getEdgeBaseType().getAllBaseClasses()),
                new Function<OClass, String>() {
                    @Override
                    public String apply(OClass input) {
                        return input.getName() + " (cluster " + input.getDefaultClusterId() + ")";
                    }
                });
    }
}
