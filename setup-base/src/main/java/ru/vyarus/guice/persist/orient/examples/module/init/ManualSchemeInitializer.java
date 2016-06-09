package ru.vyarus.guice.persist.orient.examples.module.init;

import com.google.inject.Inject;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.metadata.schema.OType;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.db.scheme.SchemeInitializer;

/**
 * Scheme initializer will be called to init or update database schema.
 * Here we assume document api only usage to show low level schema api, but you can map schema directly from java
 * pojo (see object api demo with classpath scanning examples).
 *
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
public class ManualSchemeInitializer implements SchemeInitializer {

    public static final String CLASS_NAME = "Sample";

    // here document api used in generic, but it may be object or graph api class if required
    @Inject
    private PersistentContext<ODatabaseDocumentTx> context;

    @Override
    public void initialize() {
        // method is called under unit of work (with NOTX transaction mode,
        // because orient "DDL" must be executed in this mode).

        final ODatabaseDocumentTx db = context.getConnection();
        final OSchema schema = db.getMetadata().getSchema();

        // creating class only if it isn't already exists
        // this is very naive approach: normally existing class structure must also be
        // verified and updated if necessary
        if (schema.existsClass(CLASS_NAME)) {
            return;
        }

        final OClass sampleClass = schema.createClass(CLASS_NAME);
        sampleClass.createProperty("name", OType.STRING);
        sampleClass.createProperty("amount", OType.INTEGER);
    }
}
