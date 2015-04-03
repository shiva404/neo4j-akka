package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.da.GroupDao;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.types.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 1/23/15.
 */
public class GroupTest extends TestBase {
    @Autowired
    private GroupDao groupDao;
    
    private Group createdGroup;
    
    @Test
    public void createTest(){
        Group group = new Group("groupName");
        createdGroup = groupDao.createGroup(group);
    }
    
    @Test(dependsOnMethods = "createTest")
    public void getGroup(){
        Group resultGroup = groupDao.getGroup(createdGroup.getId());
        assert resultGroup != null;
    }
}
