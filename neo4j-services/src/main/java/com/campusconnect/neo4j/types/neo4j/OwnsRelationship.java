package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.util.Constants;
import org.springframework.data.neo4j.annotation.RelationshipEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by sn1 on 2/25/15.
 */
@RelationshipEntity(type = Constants.OWNS_RELATION)
public class OwnsRelationship extends BookRelationship {

    private Long lentDate;

    public Long getLentDate() {
        return lentDate;
    }

    public void setLentDate(Long lentDate) {
        this.lentDate = lentDate;
    }

    private String borrowerId;

    private Integer contractPeriodInDays;

    private String dueDate;

    private String goodreadsStatus;

    private String userComment;

    private Set<String> historyEvents;

    public Set<String> getHistoryEvents() {

        if (null == historyEvents) {
            return new TreeSet<String>();
        }
        return historyEvents;
    }

    public void appendHistoryEvent(String historyEvent) {
        getHistoryEvents().add(historyEvent);
    }

    public OwnsRelationship() {
        super();
    }

    public OwnsRelationship(User user, Book book, Long createdDate,
                            String status, Long lastModifiedDate) {
        super(user, book, status, createdDate, lastModifiedDate);
    }

    public OwnsRelationship(User user, Book book, Long createdDate,
                            String status, Long lastModifiedDate, String goodreadsStatus) {
        super(user, book, status, createdDate, lastModifiedDate);
        this.goodreadsStatus = goodreadsStatus;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public void setContractPeriodInDays(Integer contractPeriodInDays) {

        this.contractPeriodInDays = contractPeriodInDays;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {

        this.borrowerId = borrowerId;
    }

    public Integer getContractPeriodInDays() {
        return contractPeriodInDays;
    }

    public void setContractPeriodInDays(int contractPeriodInDays) {
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getGoodreadsStatus() {
        return goodreadsStatus;
    }

    public void setGoodreadsStatus(String goodreadsStatus) {
        this.goodreadsStatus = goodreadsStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnsRelationship)) return false;

        OwnsRelationship that = (OwnsRelationship) o;

        if (contractPeriodInDays != that.contractPeriodInDays) return false;
        if (borrowerId != null ? !borrowerId.equals(that.borrowerId) : that.borrowerId != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (goodreadsStatus != null ? !goodreadsStatus.equals(that.goodreadsStatus) : that.goodreadsStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = borrowerId != null ? borrowerId.hashCode() : 0;
        result = 31 * result + contractPeriodInDays;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (goodreadsStatus != null ? goodreadsStatus.hashCode() : 0);
        return result;
    }

    public Map<String, String> getFieldsAsMap() {
        Map<String, String> fields = new HashMap<>();
        if (contractPeriodInDays != null)
            fields.put("contractPeriodInDays", String.valueOf(contractPeriodInDays));
        if (borrowerId != null)
            fields.put("borrowerId", borrowerId);
        if (dueDate != null)
            fields.put("dueDate", dueDate);
        if (goodreadsStatus != null)
            fields.put("goodreadsStatus", goodreadsStatus);
        return fields;
    }
}
