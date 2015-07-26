package com.campusconnect.neo4j.util;

import com.campusconnect.neo4j.exceptions.AuthorizationException;
import com.campusconnect.neo4j.exceptions.InvalidDataException;
import com.campusconnect.neo4j.exceptions.Neo4jException;
import com.campusconnect.neo4j.types.neo4j.BorrowRelationship;
import com.campusconnect.neo4j.types.neo4j.OwnsRelationship;
import com.campusconnect.neo4j.types.neo4j.UserGroupRelationship;
import com.campusconnect.neo4j.types.web.Field;
import com.campusconnect.neo4j.types.web.Fields;
import com.campusconnect.neo4j.types.web.User;

import static com.campusconnect.neo4j.util.ErrorCodes.INVALID_ARGUMENTS;
import static com.campusconnect.neo4j.util.ErrorCodes.MISSING_Q_PARAM;

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

    public static void validateBookListingApiParams(String listingType, String bookIdType) {
        if (listingType == null) {
            throw new InvalidDataException(MISSING_Q_PARAM, "Listing type is missing");
        } else if (!bookIdType.equals(Constants.ID) && !bookIdType.equals(Constants.GR_ID)) {
            throw new InvalidDataException(INVALID_ARGUMENTS, "idType is invalid");
        }
    }
    
    
 public static void checkUserisAdmin(UserGroupRelationship createdByRelationShip) {
		
    	if(null==createdByRelationShip)
    	{
    		throw new AuthorizationException(ErrorCodes.UNAUTHORIZED, "invalid credentials");
    	}
    	else if(!createdByRelationShip.getRole().equalsIgnoreCase(Constants.GROUP_ADMIN_ROLE))
    	{
    		throw new AuthorizationException(ErrorCodes.UNAUTHORIZED, "you do not have admin permissions");
    	}
	}

public static void checkBookReturnPreConditions(
		BorrowRelationship borrowRelationship, OwnsRelationship ownsRelationship) {
			
	if (null==borrowRelationship || null == ownsRelationship || !(borrowRelationship.getStatus().toUpperCase().equals(Constants.BORROW_SUCCESS) && ownsRelationship.getStatus().equals(Constants.LENT)))
	{
		throw new Neo4jException(ErrorCodes.INCONSISTANT_DATA, "Data is not consistant please contact admin");
	}
}

public static void validateUserFields(Fields fields) {
	
		//  this.email = email;
		//  this.favorites = favorites;
		//  
		//  this.goodreadsAccessToken = goodreadsAccessToken;
		//  this.goodreadsAccessTokenSecret = goodreadsAccessTokenSecret;
		//  this.goodreadsAuthStatus = goodreadsAuthStatus;
		//
		//
		//
		//  this.name = name;
		// 
		//  this.phone = phone;
		//  this.gender = gender;
		//  this.profileImageUrl = profileImageUrl;
		//  this.workDesignation = workDesignation;
		//  this.workLocation = workLocation;
	
	 for (Field field : fields.getFields()) {
		 
		 switch(field.getName()){
		 case "email":
//			 if(validateEmail())
//			 {
//
//			 }
			 
		 }
	 }
	
}
}
