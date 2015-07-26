package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.BookDetails;

import java.util.List;

public class OwnedBookDetails extends BookDetails {

    private BorrowedBookDetails borrowedBookDetails;
    private List<User> lookingForUsers;
    private HistoryEventPage historyEventPage;

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
        return lookingForUsers;
    }

    public void setLookingForUsers(List<User> lookingForUsers) {
        this.lookingForUsers = lookingForUsers;
    }

    public HistoryEventPage getHistoryEventPage() {
        return historyEventPage;
    }

    public void setHistoryEventPage(HistoryEventPage historyEventPage) {
        this.historyEventPage = historyEventPage;
    }

    public OwnedBookDetails(BorrowedBookDetails borrowedBookDetails, List<User> lookingForUsers, HistoryEventPage historyEventPage, String status) {
        this.borrowedBookDetails = borrowedBookDetails;
        this.lookingForUsers = lookingForUsers;
        this.historyEventPage = historyEventPage;
        this.status = status;
    }

    public OwnedBookDetails() {
    }
}
