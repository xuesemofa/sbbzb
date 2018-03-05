package org.sbbzb.com.model;

import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class PeopleModel implements Serializable {
    private String uuid;
    private String name;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeopleModel() {
        super();
    }

    public PeopleModel(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    @Override
    public String toString() {
        return "PeopleModel{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
