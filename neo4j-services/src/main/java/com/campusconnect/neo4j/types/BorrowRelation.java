package com.campusconnect.neo4j.types;


import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 2/26/15.
 */
@RelationshipEntity(type = "BORROWED")
public class BorrowRelation extends BookRelation{
    
    private long dueDate;
    private String ownerUserId;
    private String additionalComments;
    private long borrowDate;
    private int contractPeriodInDays;

    public BorrowRelation(User user, Book book, String status, long dueDate, String ownerUserId, String additionalComments, long borrowDate, int contractPeriodInDays) {
        super(user, book, status);
        this.dueDate = dueDate;
        this.ownerUserId = ownerUserId;
        this.additionalComments = additionalComments;
        this.borrowDate = borrowDate;
        this.contractPeriodInDays = contractPeriodInDays;
    }
    
    public BorrowRelation() {

    }

    public BorrowRelation(User user, Book book, String status) {
        super(user, book, status);
    }

    public long getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(long borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getContractPeriodInDays() {
        return contractPeriodInDays;
    }

    public void setContractPeriodInDays(int contractPeriodInDays) {
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }

}
