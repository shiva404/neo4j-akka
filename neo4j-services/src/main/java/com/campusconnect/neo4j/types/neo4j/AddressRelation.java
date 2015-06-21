package com.campusconnect.neo4j.types.neo4j;


import com.campusconnect.neo4j.util.Constants;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 2/23/15.
 */
@RelationshipEntity(type = Constants.ADDRESSES_RELATION)
public class AddressRelation {
    @GraphId
    Long id;
    @EndNode
    private Address address;
    @StartNode
    private User user;

    public AddressRelation() {
    }

    public AddressRelation(User user, Address address) {

        this.user = user;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
