package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.da.FBDao;
import com.campusconnect.neo4j.tests.TestBase;
import com.sun.jersey.api.spring.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 3/2/15.
 */
public class FBDaotest extends TestBase{
    @Autowired
    private FBDao fbDao;
    
    @Test
    public void testCallFbTest() {
        fbDao.getFriendsOfUser("yoyoy");
    }
    
}
