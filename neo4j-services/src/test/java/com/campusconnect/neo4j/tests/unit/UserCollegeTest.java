package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.da.CollegeDao;
import com.campusconnect.neo4j.da.UserDaoImpl;
import com.campusconnect.neo4j.resources.UserResource;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.types.College;
import com.campusconnect.neo4j.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

import static com.campusconnect.neo4j.tests.functional.base.DataBrewer.getFakeUser;

/**
 * Created by sn1 on 1/22/15.
 */
public class UserCollegeTest extends TestBase {

    College createdCollege;
    @Autowired
    private CollegeDao collegeDao;
    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private UserResource  userResource;

    @Test
    public void addUserToCollege(){
        final String collegeFirstName = faker.name().firstName();
        College college = new College(collegeFirstName + " College", collegeFirstName.charAt(0) + "."+"C");
        createdCollege = collegeDao.createCollege(college);

        User user = getFakeUser();
        User createdUser = userDaoImpl.createUser(user, null);

        System.err.println("college-id:" + createdCollege.getId() + " userId:" + user.getId());

//        userResource.approveCollegeAccess(createdUser.getId(), createdCollege.getId(), createdUser.getId(), AccessRoles.READ.toString());

    }

    @Test(dependsOnMethods = "addUserToCollege")
    public void getUsersOfCollege(){
        List<User> userList = collegeDao.getUsersForCollege(createdCollege.getId());
        System.out.println(userList);
    }
}
