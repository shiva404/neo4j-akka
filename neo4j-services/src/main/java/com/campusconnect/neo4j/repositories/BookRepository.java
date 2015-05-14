package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.Book;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by I308260 on 2/17/2015.
 */
public interface BookRepository extends GraphRepository<Book> {
    @Query(value = "match (users:User {id: {0}})-[:READ]->(books:Book) return books")
    public List<Book> getBooks(String userId);}
