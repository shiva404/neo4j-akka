package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.akka.goodreads.api.GetUser;
import com.campusconnect.neo4j.tests.TestBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 4/11/15.
 */
public class GoodreadsTest extends TestBase {
    @Autowired
    GetUser getUser;

    @Test
    public void getUserTest() {
        getUser.getUser("22748455", "KztlD3JKfZ1qJfeqQCEqfg", "nlcbU0zMdDUWs0QB032JfdUGOGWAJXCgy7GvEnoQ");

    }
}
