package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.da.CollegeDao;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.types.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 1/22/15.
 */
public class CollegeTest extends TestBase {

    @Autowired
    private CollegeDao collegeDao;

    private College createdCollege;

    @Test
    public void createCollege() {
        final String collegeFirstName = faker.name().firstName();
        College college = new College(collegeFirstName + " College", collegeFirstName.charAt(0) + "." + "C");
        createdCollege = collegeDao.createCollege(college);
        assert createdCollege != null;
    }

    @Test(dependsOnMethods = "createCollege")
    public void getCollege() {
        College resultCollege = collegeDao.getCollege(createdCollege.getId());
        assert resultCollege != null;
    }

}
