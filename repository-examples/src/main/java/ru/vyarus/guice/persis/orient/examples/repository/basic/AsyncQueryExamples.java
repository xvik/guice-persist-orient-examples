package ru.vyarus.guice.persis.orient.examples.repository.basic;

import com.google.inject.ProvidedBy;
import com.google.inject.internal.DynamicSingletonProvider;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.command.OCommandResultListener;
import ru.vyarus.guice.persist.orient.repository.command.async.AsyncQuery;
import ru.vyarus.guice.persist.orient.repository.command.ext.listen.Listen;

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
}
