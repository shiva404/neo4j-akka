package com.campusconnect.neo4j.util;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/10/15
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {

    public static final String OWNS_RELATION = "OWNS";
    public static final String BORROWED_RELATION = "BORROWED";
    public static final String CURRENTLY_READING_RELATION = "CURRENTLY_READING";
    public static final String ADDRESSES_RELATION = "addresses";
    public static final String GOODREADS_REC_RELATION = "GR_REC";
    public static final String READ_RELATION = "READ";
    public static final String REMINDER_RELATION = "REMINDER_ABOUT";
    public static final String ACTIVITY_RELATION = "ACTIVITY";
    public static final String USER_GROUP_RELATION = "USER_ACCESS";
    public static final String NOTIFICATION_RELATION = "NOTIFICATION";
    public static final String CONNECTED_RELATION = "CONNECTED";
    public static final String WISHLIST_RELATION = "WISH";
    public static final String WISHLIST_WITH_REC = "WISH_REC";

    public static final String FRIEND_REQ_SENT = "FRIEND_REQ_SENT";
    public static final String FRIEND_REQ_APPROVAL_PENDING = "FRIEND_REQ_APPROVAL_PENDING";
    public static final String ADDRESS_TYPE_HOME = "HOME";
    public static final String ADDRESS_TYPE_WORK = "WORK";
    public static final String ADDRESS_TYPE_OTHER = "OTHER";
    public static final String FRIEND_REQUEST_SENT_NOTIFICATION_TYPE = "FRIEND_REQUEST_SENT";
    public static final String FRIEND_REQUEST_ACCEPTED_NOTIFICATION_TYPE = "FRIEND_REQUEST_ACCEPTED";
    public static final String GROUP_DELETED_NOTIFICATION_TYPE = "Group Deleted";

    public static final String AVAILABLE = "AVAILABLE";
    public static final String PRIVATE = "PRIVATE";
    //    public static final String
    public static final String OWNS = OWNS_RELATION;
    public static final String WISHLIST = WISHLIST_RELATION;
    public static final String READ = READ_RELATION;
    public static final String CURRENTLY_READING = CURRENTLY_READING_RELATION;
    public static final String LENT = "LENT";
    public static final String BORROWED = "BORROWED";
    public static final String BORROW_LOCK = "BORROW_LOCK";
    public static final String BORROW_IN_PROGRESS = "BORROW_IN_PROGRESS";


    public static final String ALL = "ALL";

    public static final String ID = "id";
    public static final String GR_ID = "grId";

    public static final String SELF = "SELF";

    public static final String BORROW_AGREED = "agreed";
    public static final String BORROW_SUCCESS = "success";
    public static final String BORROW_REJECT = "reject";

    public static final String RETURN_INIT = "init";
    public static final String RETURN_AGREED = "agreed";
    public static final String RETURN_SUCCESS = "success";

    public static final String ADDRESS_DELETED_EVENT = "ADDRESS_DELETED";

    public static final String FRIEND_REQUEST_CANCEL_DELETE = "FRIEND_REQUEST_CANCEL_DELETE";
    public static final String FRIEND_UNFRIEND_DELETE = "FRIEND_REQUEST_CANCEL_DELETE";

    public static final String STATUS = "status";

    public static final String ACCESS_TOKEN_QPARAM = "accessToken";
    public static final String IN_PROGRESS_GREADS_STATUS = "inProgress";
    public static final String FILTER_QPARAM = "filter";
    public static final String STATUS_QPARAM = STATUS;

    public static final String CREATED_BY_QPARAM = "createdBy";
    public static final String REMINDER_ABOUT_QPARM = "reminderAbout";
    public static final String FRESH_NOTIFICATION_TYPE = "fresh";
    public static final String SEARCH_QPARAM = "q";
    public static final String SIZE_QPARAM = "size";
    public static final String INCLUDE_FRIENDS_QPARAM = "includeFriends";

    public static final String LISTING_TYPE_QPARAM = "listingType";
    public static final String ID_TYPE_QPARAM = "idType";
    public static final String LOGGED_IN_USER_QPARAM = "loggedInUser";
    public static final String SHARE_PH_QPARAM = "sharePh";
    public static final String MESSAGE_QPARAM = "message";
    public static final String OWNER_USER_ID_QPARAM = "ownerUserId";
    public static final String BORROWER_ID_QPARAM = "borrowerId";

    public static final String REMINDER_CREATED_NOTIFICATION_TYPE = "REMINDER_CREATED";
	public static final String RECEIVED_REMINDER_TYPE = "Received";
	public static final String SENT_REMINDER_TYPE = "Sent";

	public static final String GROUP_ADMIN_ROLE = "admin";
	public static final String GROUP_MEMBER_ROLE = "member";
	public static final String GROUP_MEMBER_ADDED = "group memner added";
	public static final String GROUP_ADMIN_NOTIFICATION = "Group admin added";
	public static final String RETURN_INIT_NOTIFICATION_TYPE = "Book retun initiated";


}
