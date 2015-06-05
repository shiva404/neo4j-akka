package com.campusconnect.neo4j.types.web;

/**
 * Created by sn1 on 3/4/15.
 */
public class Field {
    private String name;
    private String value;

    public Field() {

    }

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
