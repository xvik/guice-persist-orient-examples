package ru.vyarus.guice.persist.orient.examples;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.PersistService;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.db.transaction.template.SpecificTxAction;
import ru.vyarus.guice.persist.orient.db.transaction.template.TxAction;
import ru.vyarus.guice.persist.orient.examples.module.DbModule;
import ru.vyarus.guice.persist.orient.examples.module.init.ManualSchemeInitializer;
import ru.vyarus.guice.persist.orient.examples.service.NoTxService;
import ru.vyarus.guice.persist.orient.examples.service.SampleService;

/**
 * Shows local database usage: db will be created on fs and so db state will survive between launches.
 * Difference with remote mode:
 * - does not requires server
 * - only one jvm will be able to work with db (while app launched)
 * - guice-persist-orient automatically create local db if it doesn't exist (could be switched off)
 *
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
public class LocalDbApp {

    public static String DB_PATH = "c:/Temp/sampledb";

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new DbModule("plocal:" + DB_PATH, "admin", "admin"));
        final PersistService persistService = injector.getInstance(PersistService.class);
        persistService.start();

        try {
            final SampleService service = injector.getInstance(SampleService.class);
            // run multiple times: each time count will increase
            System.out.println("Overall records: " + service.count());

            // as an sample we will use service without unit of work defined
            // so transaction must be defined manually
            final NoTxService noTxService = injector.getInstance(NoTxService.class);
            // normally you will simply inject this as PersistentContext<ODatabaseDocumentTx>
            final PersistentContext<ODatabaseDocumentTx> context = injector.getInstance(
                    Key.get(new TypeLiteral<PersistentContext<ODatabaseDocumentTx>>() {
                    }));

            context.doInTransaction(() -> {
                // unit of work defined
                noTxService.doSomething();
                return null;
            });


            // now do one more manual transaction but with provided db object
            final ODocument rec = context.doInTransaction(db -> {
                // service use @Transactional annotation for unit of work definition
                // in this case annotation will be ignored because unit of work is already defined
                final long cnt = service.count();

                // manually insert record
                final ODocument rec1 = db.newInstance(ManualSchemeInitializer.CLASS_NAME)
                        .field("name", "Sample" + cnt)
                        .field("amount", (int) (Math.random() * 200))
                        .save();
                // detaching object before let it leave transaction scope
                rec1.detach();
                return rec1;
            });

            System.out.println("Just inserted record: " + rec.toJSON());
        } finally {
            // at the end service must be stopped
            persistService.stop();
        }
    }
}
