package com.campusconnect.neo4j.akka.facebook.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.facebook.api.GetUserProfileImage;
import com.campusconnect.neo4j.akka.facebook.task.UserProfileImageTask;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.neo4j.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sn1 on 4/2/15.
 */
public class UserProfileImageWorker extends UntypedActor {
    @Autowired
    private GetUserProfileImage getUserProfileImage;

    @Autowired
    private UserDao userDao;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof UserProfileImageTask) {
            UserProfileImageTask userProfileImageTask = (UserProfileImageTask) message;
            final User user = userProfileImageTask.getUser();
            String userProfileImageUrl = getUserProfileImage.getImageUrlForUser(user.getFbId(), userProfileImageTask.getAccessToken());
            user.setProfileImageUrl(userProfileImageUrl);
            userDao.updateUser(user.getId(), user,false);
        }
    }
}
