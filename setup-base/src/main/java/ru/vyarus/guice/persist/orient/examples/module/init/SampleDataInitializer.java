package ru.vyarus.guice.persist.orient.examples.module.init;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.db.data.DataInitializer;

/**
 * Init sample data if database table is empty.
 *
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
@Transactional
public class SampleDataInitializer implements DataInitializer {

    @Inject
    private PersistentContext<ODatabaseDocumentTx> context;

    @Override
    public void initializeData() {
        // Important! method is called without transaction to let implementation control transaction scope and type
        // (initializer may even use many (different?) transactions).
        // Unit of work must be defined before calling orient api:  the simplest way is to use @Transactional
        // on class or method

        final ODatabaseDocumentTx db = context.getConnection();
        if (db.countClass(ManualSchemeInitializer.CLASS_NAME) > 0) {
            // perform initialization only in case of empty table
            return;
        }

        // low level api used to insert records, but the same could be done with sql insert commands
        for (int i = 0; i < 10; i++) {
            final ODocument rec = db.newInstance(ManualSchemeInitializer.CLASS_NAME)
                    .field("name", "Sample" + i)
                    .field("amount", (int) (Math.random() * 200));
            rec.save();
        }
    }
}
