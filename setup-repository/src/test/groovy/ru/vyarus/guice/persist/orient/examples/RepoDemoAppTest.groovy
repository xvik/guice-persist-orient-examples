package ru.vyarus.guice.persist.orient.examples

import spock.lang.Specification


/**
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
class RepoDemoAppTest extends Specification {

    def "Check app correct"() {

        when: "running sample"
        RepoDemoApp.main()

        then: "no exception"
        true
    }
}