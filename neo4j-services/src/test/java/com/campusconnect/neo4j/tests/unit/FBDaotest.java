package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.akka.facebook.api.GetUserProfile;
import com.campusconnect.neo4j.da.FBDao;
import com.campusconnect.neo4j.tests.TestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 3/2/15.
 */
public class FBDaotest extends TestBase {
    @Autowired
    private FBDao fbDao;

    @Autowired
    private GetUserProfile getUserProfile;

    @Test
    public void testCallFbTest() {
        fbDao.getFriendsOfUser("yoyoy");
    }

    @Test
    public void getUserProfile() {
        getUserProfile.getUserProfile("10203245092523128", "CAAFqffkms2oBABlkCVeyvC9MJwRjxuKffk4UfiADRnZB7Uc4yFEGGHbI79UrzZBVmrhOYR8BrJU826c26qEBxqJM1uXqlgsNNWDTkrMtlqbmu0IOh7RRWN6vdvIVMrjL4BqYqnYG4rW1bCsXnxsinJ0QXQp0M45a1SnqNosiaZBg2J66DQCADkZCa6O44WQWg9HSGTFXppIJ9OEi5ScRNFKWtsEQbUUZD");
    }
}