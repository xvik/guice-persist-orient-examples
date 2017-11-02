package ru.vyarus.guice.persist.orient.examples.model;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Sample entity. Database scheme will be mapped from entity fields.
 * Note that class is registered without package, so may be moved in code without problems.
 *
 * @author Vyacheslav Rusakov
 * @since 02.11.2017
 */
public class Sample {

    /**
     * Orient will map rid into this field (e.g. #12:1)
     */
    @Id
    private String id;
    /**
     * Will be used by orient for optimistic locking (increment value on each save to detect stale object saves).
     * This field is required when transactions used.
     */
    @Version
    private Long version;
    private String name;
    private int amount;

    /**
     * Empty constructor required.
     */
    public Sample() {
    }

    public Sample(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
