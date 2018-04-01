package ru.vyarus.guice.persist.orient.examples.repository.basic

import ru.vyarus.guice.persist.orient.db.transaction.template.SpecificTxAction
import ru.vyarus.guice.persist.orient.examples.AbstractTest
import ru.vyarus.guice.persist.orient.examples.model.Model

import javax.inject.Inject

/**
 * @author Vyacheslav Rusakov
 * @since 08.11.2017
 */
class BeanExamplesTest extends AbstractTest {

    @Inject
    BeanExamples repository

    def "Check bean with repository methods"() {

        setup:
        context.doInTransaction({ db -> db.save(new Model(name: 'sample')) } as SpecificTxAction)

        when: "call method with implicit repository usage"
        repository.doSomething()
        then: "all ok"
        true

        when: "call public method"
        def res = repository.publicQuery()
        then: "query works"
        res.size() == 1
    }
}
