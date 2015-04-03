package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.da.DepartmentDao;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.types.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 1/23/15.
 */
public class DepartmentTest extends TestBase {
    @Autowired
    private DepartmentDao departmentDao;

    private Department createdDept;

    @Test
    public void createDepartment(){
        Department department = new Department("Test Dept", "college_id");
        createdDept = departmentDao.createDepartment(department);
    }

    @Test(dependsOnMethods = "createDepartment")
    public void getDepartment(){
        Department departmentResult = departmentDao.getDepartment(createdDept.getId());
        assert departmentResult != null;
    }

}
