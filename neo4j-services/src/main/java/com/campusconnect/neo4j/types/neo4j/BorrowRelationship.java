package com.campusconnect.neo4j.types.neo4j;


import com.campusconnect.neo4j.util.Constants;
import org.springframework.data.neo4j.annotation.RelationshipEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sn1 on 2/26/15.
 */
@RelationshipEntity(type = Constants.BORROWED_RELATION)
public class BorrowRelationship extends BookRelationship {

    private Long dueDate;
    private String ownerUserId;
    private String additionalComments;
    private Long borrowDate;
    private Integer contractPeriodInDays;

    public BorrowRelationship(User user, Book book, String status, long dueDate, String ownerUserId, String additionalComments, long borrowDate, int contractPeriodInDays) {
        super(user, book, status);
        this.dueDate = dueDate;
        this.ownerUserId = ownerUserId;
        this.additionalComments = additionalComments;
        this.borrowDate = borrowDate;
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public BorrowRelationship() {

    }

    public BorrowRelationship(User user, Book book, String status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowRelationship)) return false;

        BorrowRelationship that = (BorrowRelationship) o;

        if (borrowDate != that.borrowDate) return false;
        if (contractPeriodInDays != that.contractPeriodInDays) return false;
        if (dueDate != that.dueDate) return false;
        if (additionalComments != null ? !additionalComments.equals(that.additionalComments) : that.additionalComments != null)
            return false;
        if (ownerUserId != null ? !ownerUserId.equals(that.ownerUserId) : that.ownerUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (dueDate ^ (dueDate >>> 32));
        result = 31 * result + (ownerUserId != null ? ownerUserId.hashCode() : 0);
        result = 31 * result + (additionalComments != null ? additionalComments.hashCode() : 0);
        result = 31 * result + (int) (borrowDate ^ (borrowDate >>> 32));
        result = 31 * result + contractPeriodInDays;
        return result;
    }

    public Map<String, String> getFieldsAsMap() {
        Map<String, String> fields = new HashMap<>();
        if (contractPeriodInDays != 0)
            fields.put("contractPeriodInDays", String.valueOf(contractPeriodInDays));
        if (dueDate != null)
            fields.put("dueDate", String.valueOf(dueDate));
        if (ownerUserId != null)
            fields.put("ownerUserId", ownerUserId);
        if (additionalComments != null)
            fields.put("additionalComments", additionalComments);
        if (borrowDate != null)
            fields.put("borrowDate", String.valueOf(borrowDate));

        return fields;

    }

}
