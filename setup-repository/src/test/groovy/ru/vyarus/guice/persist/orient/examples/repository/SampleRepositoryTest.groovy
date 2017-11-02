package ru.vyarus.guice.persist.orient.examples.repository

import com.google.inject.Inject
import com.orientechnologies.orient.core.record.impl.ODocument
import ru.vyarus.guice.persist.orient.examples.AbstractTest
import ru.vyarus.guice.persist.orient.examples.model.Sample

/**
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
class SampleRepositoryTest extends AbstractTest {

    @Inject
    SampleRepository repository

    def "Check methods"() {

        when: "count query"
        long cnt = repository.count()
        then: "ok"
        cnt == 10

        when: "select first"
        def rec = repository.first()
        then: "found"
        rec

        when: "select all"
        def res = repository.selectDetached()
        then: "found"
        res.size() == 10

        when: "search by name"
        rec = res.first()
        rec = repository.findByNameDetached(rec.getName())
        then: "found"
        rec

        when: "replace name"
        res = repository.updateName("test", rec.getName())
        then: "renamed in db"
        res == 1


        when: "search document by name"
        rec = repository.findDocumentByName("test")
        then: "found"
        rec
        rec instanceof ODocument

    }
}