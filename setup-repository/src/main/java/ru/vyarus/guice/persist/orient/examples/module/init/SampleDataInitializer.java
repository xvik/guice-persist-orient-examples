package ru.vyarus.guice.persist.orient.examples.module.init;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.orientechnologies.orient.core.db.object.ODatabaseObject;
import ru.vyarus.guice.persist.orient.db.PersistentContext;
import ru.vyarus.guice.persist.orient.db.data.DataInitializer;
import ru.vyarus.guice.persist.orient.examples.model.Sample;

/**
 * Init sample data if database table is empty.
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
@Transactional
public class SampleDataInitializer implements DataInitializer {

    @Inject
    private PersistentContext<ODatabaseObject> context;

    @Override
    public void initializeData() {
        final ODatabaseObject db = context.getConnection();

        for (int i = 0; i < 10; i++) {
            /*
              Important notion on proxies:
              When you create object you can use simple pojo, but after save don't expect orient will update
              the same instance with id and version!
              Save method will return different object instance (actually proxy) which have new fields set.
             */
            db.save(new Sample("Sample" + i, (int) (Math.random() * 200)));
        }
    }
}
