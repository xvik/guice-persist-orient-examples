package ru.vyarus.guice.persist.orient.examples.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.examples.model.Sample;

import java.util.List;

/**
 * @author Vyacheslav Rusakov
 * @since 12.06.2016
 */
@Singleton
@Transactional
public class SampleService {

    @Inject
    private PersistentContext<OObjectDatabaseTx> context;

    public long count() {
        // now we can use class directly instead of string name
        return context.getConnection().countClass(Sample.class);
    }

    public long count2() {
        // in case of projection or aggregate function, orient will return ODocument,
        // even if it will contain single field (a kind of ResultSet analogy from jdbc)
        final List<ODocument> res = context.getConnection().query(new OSQLSynchQuery<Object>(
                "select count(*) from Sample"
        ));
        return res.get(0).field("count");
    }

    public Sample selectLast() {
        // sample query
        final List<Sample> res = context.getConnection().query(new OSQLSynchQuery<Sample>(
                "select from Sample order by name desc limit 1"));
        return res.isEmpty() ? null : res.get(0);
    }

    public Sample findByName(String name) {
        // query with parameter
        final List<Sample> res = context.getConnection().query(new OSQLSynchQuery<Sample>(
                "select from Sample where name = ?"), name);
        return res.isEmpty() ? null : res.get(0);
    }

    public void replaceName(String name, String to) {
        // sql command
        context.getConnection().command(new OCommandSQL(
                "update Sample set name = ? where name = ?"))
                .execute(to, name);
    }

    public Sample detach(Sample sample) {
        // required to demonstrate importance of object detaching
        // (normally either all work with object must be under transaction or it must be detached after the query)
        return context.getConnection().detach(sample, true);
    }
}
