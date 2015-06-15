package com.campusconnect.neo4j.da;

import akka.actor.ActorRef;
import com.campusconnect.neo4j.akka.facebook.api.GetUserProfileImage;
import com.campusconnect.neo4j.akka.facebook.task.FriendsListTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

/**
 * Created by sn1 on 2/27/15.
 */
public class FBDao {

    @Autowired
    GetUserProfileImage getUserProfileImage;
    FacebookConnectionFactory facebookConnectionFactory;
    private ActorRef getFriendsRouter;
    private ActorRef successListener;

    public FBDao() {
    }

    public FBDao(FacebookConnectionFactory facebookConnectionFactory, ActorRef getFriendsRouter, ActorRef successListener) {
        this.facebookConnectionFactory = facebookConnectionFactory;
        this.getFriendsRouter = getFriendsRouter;
        this.successListener = successListener;
    }

    public static void main(String[] args) {
        FBDao fbDao = new FBDao(null, null, null);
        fbDao.setFacebookConnectionFactory(new FacebookConnectionFactory("136567019835635", "925042e37ac32f1591cc9e18cda99297"));
        fbDao.getFriendsOfUser("CAACEdEose0cBABANF4bglnMTeNNnPlPd0ne3ZBCaQnfkxbYTrd7dkab69wl8GzQeO02UhREqAm5UZBSWiZBC1irCUDtGu79pRfZBn1XZBHYFW7sW2yF42ZAzOuabWKGsZAx2UIcEZA4DWZBir6LqEJTcZBwtgd5Od4r3k1CFYp13fbkIJnyYLsSqiwSrvWZCsIZC4WMZCjY8jIDZCiHC9YdEZA9Gp1KmJma7WVUJErty5I8gWEmkAZDZD");
    }

    public String getUserProfileImage(String fbId, String accessToken) {
        return getUserProfileImage.getImageUrlForUser(fbId, accessToken);
    }

    public void setFacebookConnectionFactory(FacebookConnectionFactory facebookConnectionFactory) {
        this.facebookConnectionFactory = facebookConnectionFactory;
    }

    public void getFriendsOfUser(String accessToken) {

        getFriendsRouter.tell(new FriendsListTask(accessToken), successListener);
//        for (Reference reference : friends){
//
//        }
    }

    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory("136567019835635", "925042e37ac32f1591cc9e18cda99297"));
        return registry;
    }
}
