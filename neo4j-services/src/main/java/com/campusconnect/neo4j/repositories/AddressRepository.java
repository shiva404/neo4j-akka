package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.Address;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by sn1 on 4/16/15.
 */
public interface AddressRepository extends GraphRepository<Address> {

    @Query(value = "match (user:User {id:{0}})-[:addresses]->(addresses:Address) return addresses")
    public List<Address> getAddressForUser(String userId);
}
