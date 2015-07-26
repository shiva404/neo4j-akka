package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.BookDetails;

import java.util.LinkedList;
import java.util.List;

public class OwnedBookDetails extends BookDetails {

    private BorrowedBookDetails borrowedBookDetails;
    private List<User> lookingForUsers;
    private HistoryEventPage historyEventPage;

    public List<GroupWithMembers> getGroupsWithMembers() {
        if(groupsWithMembers == null)
            groupsWithMembers = new LinkedList<>();
        return groupsWithMembers;
    }



    private List<GroupWithMembers> groupsWithMembers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public BorrowedBookDetails getBorrowedBookDetails() {
        return borrowedBookDetails;
    }

    public void setBorrowedBookDetails(BorrowedBookDetails borrowedBookDetails) {
        this.borrowedBookDetails = borrowedBookDetails;
    }

    public List<User> getLookingForUsers() {
        if(lookingForUsers == null)
            lookingForUsers = new LinkedList<>();
        return lookingForUsers;
    }


    public HistoryEventPage getHistoryEventPage() {
        return historyEventPage;
    }

    public void setHistoryEventPage(HistoryEventPage historyEventPage) {
        this.historyEventPage = historyEventPage;
    }

    public OwnedBookDetails() {
    }
}
