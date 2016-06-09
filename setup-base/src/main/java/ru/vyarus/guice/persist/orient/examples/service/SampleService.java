package ru.vyarus.guice.persist.orient.examples.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.examples.module.init.ManualSchemeInitializer;

import java.util.List;

/**
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
@Singleton
// annotation will implicitly start unit of work for each method
// without unit of work context.getConnection() will fail
@Transactional
public class SampleService {

    @Inject
    private PersistentContext<ODatabaseDocumentTx> context;

    public long count() {
        return context.getConnection().countClass(ManualSchemeInitializer.CLASS_NAME);
    }

    public ODocument selectLast() {
        // sample query
        final List<ODocument> res = context.getConnection().query(new OSQLSynchQuery<Object>(
                "select from " + ManualSchemeInitializer.CLASS_NAME + " order by name desc limit 1"));
        return res.isEmpty() ? null : res.get(0);
    }

    public ODocument findByName(String name) {
        // query with parameter
        final List<ODocument> res = context.getConnection().query(new OSQLSynchQuery<Object>(
                "select from " + ManualSchemeInitializer.CLASS_NAME + " where name = ?"), name);
        return res.isEmpty() ? null : res.get(0);
    }

    public void replaceName(String name, String to) {
        // sql command
        context.getConnection().command(new OCommandSQL(
                "update " + ManualSchemeInitializer.CLASS_NAME + " set name = ? where name = ?"))
                .execute(to, name);
    }
}
