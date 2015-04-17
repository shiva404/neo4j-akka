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
    @StartNode
    private User user;
    
    @EndNode
    private Address address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @GraphId
    Long id;

    public AddressRelation() {
    }

    public AddressRelation(User user, Address address) {

        this.user = user;
        this.address = address;
    }
}
