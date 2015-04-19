package com.campusconnect.neo4j.types;

import java.util.List;

public class UsersPage {

	private int offset;
	private int size;

	List<User> users;

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

	public int getSize() {
		return size;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setUsers(List<User> Users) {
		this.users = Users;
	}

}
