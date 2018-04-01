package ru.vyarus.guice.persist.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.sql.query.OLiveResultListener;
import com.tinkerpop.blueprints.Vertex;
import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.repository.command.ext.elvar.ElVar;
import ru.vyarus.guice.persist.orient.repository.command.ext.listen.Listen;
import ru.vyarus.guice.persist.orient.repository.command.live.LiveQuery;
import ru.vyarus.guice.persist.orient.repository.command.live.listener.mapper.LiveQueryListener;
import ru.vyarus.guice.persist.orient.repository.command.query.Query;

/**
 * Live queries are used to subscribe to database changes.
 *
 * @author Vyacheslav Rusakov
 * @see <a href="http://orientdb.com/docs/last/Live-Query.html">docs</a>
 * @since 07.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface LiveQueryExamples {

    // subscribe to live query
    // note that "live" may be omitted in query (as annotation already indicates live)
    // method returns subscription token? required fot unsubscription
    @LiveQuery("select from Model")
    int subscribe(@Listen OLiveResultListener listener);

    // unsubscribe does not support parameters https://github.com/orientechnologies/orientdb/issues/6706
    @Query("live unsubscribe ${token}")
    void unsubscribe(@ElVar("token") int token);

    // type-safe live listener with automatic result conversion
    @LiveQuery("select from Model")
    int subscribe(@Listen LiveQueryListener<Model> listener);

    // automatic listener result conversion to vertex
    @LiveQuery("select from VertexModel")
    int subscribeVertex(@Listen LiveQueryListener<Vertex> listener);

}
