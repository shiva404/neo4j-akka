package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.repositories.CollegeRepository;
import com.campusconnect.neo4j.types.College;
import com.campusconnect.neo4j.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by sn1 on 1/22/15.
 */
public class CollegeDao {

    @Autowired
    private CollegeRepository collegeRepository;

    public CollegeDao() {
    }

    public CollegeDao(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
    }

    private Neo4jTemplate neo4jTemplate;

    public College createCollege(College college){
        college.setId(UUID.randomUUID().toString());
        return neo4jTemplate.save(college);
    }

    public College getCollege(String collegeId){
        return collegeRepository.findBySchemaPropertyValue("id", collegeId);
    }

    public List<User> getUsersForCollege(String collegeId) {
        return collegeRepository.getUsers(collegeId);
    }

}
