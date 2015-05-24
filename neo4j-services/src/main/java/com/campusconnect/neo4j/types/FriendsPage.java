package com.campusconnect.neo4j.types;

import java.util.List;

public class FriendsPage {
	 List<User> friends;
	    int offset;
	    int size;
	    
		public FriendsPage() {
			super();
		}
		public FriendsPage(int size, int offset, List<User> friends) {
			super();
			this.friends = friends;
			this.offset = offset;
			this.size = size;
		}
		public List<User> getFriends() {
			return friends;
		}
		public void setFriends(List<User> friends) {
			this.friends = friends;
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


}
