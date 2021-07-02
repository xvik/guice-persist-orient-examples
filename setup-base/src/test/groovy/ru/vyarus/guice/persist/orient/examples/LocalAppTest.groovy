package ru.vyarus.guice.persist.orient.examples

import spock.lang.Specification

import java.nio.file.Files


/**
 * @author Vyacheslav Rusakov
 * @since 09.06.2016
 */
class LocalAppTest extends Specification {

    File dbdir

    void setup() {
        dbdir = Files.createTempDirectory('sampledb').toFile()
        // override path to controlled dir
        LocalDbApp.DB_PATH = dbdir.absolutePath + '/test'
    }

    void cleanup() {
        if (dbdir.exists()) {
            dbdir.deleteDir()
        }
    }

    def "Check app correct"() {

        when: "running sample"
        LocalDbApp.main()

        then: "no exception"
        true
    }
}