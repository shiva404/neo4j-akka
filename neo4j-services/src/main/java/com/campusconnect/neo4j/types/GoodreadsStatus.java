package com.campusconnect.neo4j.types;

/**
 * Created by sn1 on 3/19/15.
 */
public enum GoodreadsStatus {
    READ("read"),
    TO_READ("to-read"),
    CURRENTLY_READING("currently-reading");

    private final String name;

    private GoodreadsStatus(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName != null) && name.equals(otherName);
    }

    @Override
    public String toString() {
        return name;
    }
}
