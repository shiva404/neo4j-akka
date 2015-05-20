package com.campusconnect.neo4j.types;

import java.util.List;

public class UserIdsPage {

    List<String> userIds;
    private int offset;
    private int size;

    public UserIdsPage() {
    }

    public UserIdsPage(int offset, int size, List<String> UserIds) {
        this.offset = offset;
        this.size = size;
        this.userIds = UserIds;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> UserIds) {
        this.userIds = UserIds;
    }
}
