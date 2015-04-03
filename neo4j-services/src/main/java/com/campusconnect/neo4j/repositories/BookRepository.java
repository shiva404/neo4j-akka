package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.Book;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by I308260 on 2/17/2015.
 */
public interface BookRepository extends GraphRepository<Book> {
   // match (users:User {id: "2cb59107-8d45-4e23-b023-3bffacc08798"})-[:OWNS]->(books:Book) return books
}
