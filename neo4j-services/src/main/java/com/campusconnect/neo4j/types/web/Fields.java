package com.campusconnect.neo4j.types.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 3/4/15.
 */
public class Fields {
    private List<Field> fields;

    public List<Field> getFields() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
