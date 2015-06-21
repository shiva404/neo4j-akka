package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.Book;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by I308260 on 2/17/2015.
 */
public interface BookRepository extends GraphRepository<Book> {
}
