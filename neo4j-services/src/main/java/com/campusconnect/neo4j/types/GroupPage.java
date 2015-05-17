package com.campusconnect.neo4j.types;

import java.util.List;

public class GroupPage {
<<<<<<< HEAD
	List<Group> group;
	private int offset;

	private int size;

	public GroupPage() {
		super();
	}

	public GroupPage(List<Group> group, int offset, int size) {
		super();
		this.group = group;
		this.offset = offset;
		this.size = size;
	}

	public List<Group> getGroup() {
		return group;
	}

	public int getOffset() {
		return offset;
	}

	public int getSize() {
		return size;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
=======
    List<Group> group;
    private int offset;

    private int size;

    public GroupPage(List<Group> group, int offset, int size) {
        super();
        this.group = group;
        this.offset = offset;
        this.size = size;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
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

>>>>>>> b3bc9102013625131e2d02ebbb1c797f689d1e57

}
