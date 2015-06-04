package com.campusconnect.neo4j.types.web;

import java.util.List;

public class GroupPage {

    List<com.campusconnect.neo4j.types.neo4j.Group> group;
    private int offset;

    private int size;

    public GroupPage() {
        super();
    }

    public GroupPage(List<com.campusconnect.neo4j.types.neo4j.Group> group, int offset, int size) {
        super();
        this.group = group;
        this.offset = offset;
        this.size = size;
    }

    public List<com.campusconnect.neo4j.types.neo4j.Group> getGroup() {
        return group;
    }

    public int getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }

    public void setGroup(List<com.campusconnect.neo4j.types.neo4j.Group> group) {
        this.group = group;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setSize(int size) {
        this.size = size;
    }


}

  