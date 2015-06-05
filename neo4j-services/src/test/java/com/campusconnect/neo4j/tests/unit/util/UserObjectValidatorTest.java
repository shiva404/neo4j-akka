package com.campusconnect.neo4j.tests.unit.util;

import com.campusconnect.neo4j.types.web.User;
import com.campusconnect.neo4j.util.Validator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class UserObjectValidatorTest {

    @Test
    public void testValidatorWithNullUserObject() {
        User user = null;
        String expectedMessage = "User object is null";
        StringBuffer message = Validator.validateUserObject(user);
        assertEquals(message.toString(), expectedMessage);
    }

    @Test
    public void testValidatorUserObjectWithNoName() {
        User user = new User();
        user.setEmail("namics08@gmail.com");
        String expectedMessage = "User Name not set";
        StringBuffer message = Validator.validateUserObject(user);
        assertEquals(message.toString(), expectedMessage);
    }

    @Test
    public void testValidatorUserObjectWithNoEmail() {
        User user = new User();
        user.setName("Namitha Hugar");
        String expectedMessage = "Email not set";
        StringBuffer message = Validator.validateUserObject(user);
        assertEquals(message.toString(), expectedMessage);
    }

    @Test
    public void testValidatorUserObjectWithNoNameAndNoEmail() {
        User user = new User();
        String expectedMessage = "User Name, Email not set";
        StringBuffer message = Validator.validateUserObject(user);
        assertEquals(message.toString(), expectedMessage);
    }

    @Test
    public void testValidatorUserObjectWithRightData() {
        User user = new User();
        user.setName("Namitha Hugar");
        user.setEmail("namics08@gmail.com");
        String expectedMessage = null;
        StringBuffer message = Validator.validateUserObject(user);
        assertNull(message);
    }

}
