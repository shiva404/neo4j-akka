package com.campusconnect.neo4j.types;


import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 2/23/15.
 */
@RelationshipEntity(type = "addresses")
public class AddressRelation {
    @EndNode
    private Address address;
    
    @GraphId
    Long id;

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

    public Long getId() {
        return id;

    }

    public User getUser() {
        return user;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
