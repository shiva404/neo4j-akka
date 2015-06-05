package com.campusconnect.neo4j.types.web;


import java.util.List;

public class UsersPage {

    List<User> users;
    private int offset;
    private int size;

    public UsersPage() {
    }

    public UsersPage(int offset, int size, List<User> Users) {
        this.offset = offset;
        this.size = size;
        this.users = Users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> Users) {
        this.users = Users;
    }

}
