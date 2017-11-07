package ru.vyarus.guice.persist.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.command.OCommandResultListener;
import com.tinkerpop.blueprints.Vertex;
import ru.vyarus.guice.persist.orient.examples.model.Model;
import ru.vyarus.guice.persist.orient.repository.command.async.AsyncQuery;
import ru.vyarus.guice.persist.orient.repository.command.async.listener.mapper.AsyncQueryListener;
import ru.vyarus.guice.persist.orient.repository.command.ext.listen.Listen;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Important note: async queries are not really async - method execution is blocked while listener is called
 * (and listener called at the same thread).
 * <p>
 * Async queries are useful to iterate on loaded results and manually stop loading of results at the middle
 * of response (dynamic filtering).
 *
 * @author Vyacheslav Rusakov
 * @see <a href="http://www.orientechnologies.com/docs/last/orientdb.wiki/Document-Database.html#asynchronous-query">
 * docs</a>
 * @since 03.11.2017
 */
@Transactional
@ProvidedBy(DynamicSingletonProvider.class)
public interface AsyncQueryExamples {

    // listener called at the same thread (method bocked until results processing)
    @AsyncQuery("select from Model")
    void select(@Listen OCommandResultListener listener);

    // non blocking
    @AsyncQuery(value = "select from Model", blocking = false)
    void selectNonBlocking(@Listen OCommandResultListener listener);

    // listener will convert document to model automatically
    @AsyncQuery("select from Model")
    void select(@Listen AsyncQueryListener<Model> listener);

    // projection would also work
    @AsyncQuery("select name from Model")
    void selectProjection(@Listen AsyncQueryListener<String> listener);

    // graph conversion supported
    @AsyncQuery("select from VertexModel")
    void selectVertex(@Listen AsyncQueryListener<Vertex> listener);

    // non blocking method may return future listener will be called in separate thread
    // later in code future may be used to obtain final result: List<Model> finalResult = future.get();
    @AsyncQuery(value = "select from Model", blocking = false)
    Future<List<Model>> selectNonBlock(@Listen AsyncQueryListener<Model> listener);
}
