package ru.vyarus.guice.persist.orient.examples.service

import com.google.inject.Inject
import com.google.inject.ProvisionException
import ru.vyarus.guice.persist.orient.db.transaction.template.TxAction
import ru.vyarus.guice.persist.orient.examples.AbstractTest

/**
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
class TestNoTxService extends AbstractTest {

    @Inject
    NoTxService service

    def "Check method call without transaction"() {

        when: "calling method without transaction"
        service.doSomething()

        then: "it crashed"
        thrown(ProvisionException)
    }

    def "Check method call with transaction"() {

        when: "calling method with transaction"
        context.doInTransaction({
            service.doSomething()
        } as TxAction)

        then: "ok"
        true
    }
}