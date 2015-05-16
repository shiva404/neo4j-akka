package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.repositories.DepartmentRepository;
import com.campusconnect.neo4j.types.Department;
import com.campusconnect.neo4j.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by sn1 on 1/23/15.
 */
public class DepartmentDao {
    private Neo4jTemplate neo4jTemplate;
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDao(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
    }

    public Department createDepartment(Department department) {
        department.setId(UUID.randomUUID().toString());
        return departmentRepository.save(department);
    }

    public Department getDepartment(String departmentId) {
        return departmentRepository.findBySchemaPropertyValue("id", departmentId);
    }

    public List<User> getUsers(String departmentId) {
        departmentRepository.getUsers(departmentId);
        return null;
    }
}
