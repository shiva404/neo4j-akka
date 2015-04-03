package com.campusconnect.neo4j.types;


import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 2/25/15.
 */
@RelationshipEntity(type = "OWNS")
public class OwnsRelationship extends BookRelation {
 
    public String getGoodreadsStatus() {
        return goodreadsStatus;
    }

    public void setGoodreadsStatus(String goodreadsStatus) {
        this.goodreadsStatus = goodreadsStatus;
    }

    private String goodreadsStatus;
    
    private String borrowerId;
    
    private String dueDate;
    private int contractPeriodInDays;

    public String getBorrowerId() {
        return borrowerId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getContractPeriodInDays() {
        return contractPeriodInDays;
    }

    public void setBorrowerId(String borrowerId) {

        this.borrowerId = borrowerId;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setContractPeriodInDays(int contractPeriodInDays) {
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public OwnsRelationship(User user, Book book, long createdDate, String status, long lastModifiedDate) {
        super(user, book, status, createdDate, lastModifiedDate);
    }

    public OwnsRelationship() {
        super();
    }


    public OwnsRelationship(User user, Book book, long createdDate, String status, long lastModifiedDate, String goodreadsStatus) {
        super(user, book, status, createdDate, lastModifiedDate);
        this.goodreadsStatus = goodreadsStatus;
    }

}
