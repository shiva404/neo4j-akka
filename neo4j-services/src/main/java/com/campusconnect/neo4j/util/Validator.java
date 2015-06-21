package com.campusconnect.neo4j.util;

import com.campusconnect.neo4j.types.web.User;

public class Validator {

    public static StringBuffer getStringBuffer() {
        return new StringBuffer();
    }

    public static StringBuffer validateUserObject(User user) {
        StringBuffer returnMessage = null;


        if (null == user) {
            returnMessage = getStringBuffer();
            return returnMessage.append("User object is null");

        } else {
            boolean isInvalid = false;
            if (null == user.getName() || user.getName().isEmpty()) {
                returnMessage = getStringBuffer();
                returnMessage.append("User Name");
                isInvalid = true;
            }
            if (null == user.getEmail() || user.getEmail().isEmpty()) {
                if (isInvalid) {
                    returnMessage.append(", Email");
                } else {
                    returnMessage = getStringBuffer();
                    returnMessage.append("Email");
                    isInvalid = true;
                }
            }
            if (isInvalid)
                returnMessage.append(" not set");
        }
        return returnMessage;
    }

}
