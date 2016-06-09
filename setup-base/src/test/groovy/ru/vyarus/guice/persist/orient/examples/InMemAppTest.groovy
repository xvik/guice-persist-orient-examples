package ru.vyarus.guice.persist.orient.examples

import spock.lang.Specification


/**
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
class InMemAppTest extends Specification {

    def "Check app correct"() {

        when: "running sample"
        InMemoryDbApp.main()

        then: "no exception"
        true
    }
}