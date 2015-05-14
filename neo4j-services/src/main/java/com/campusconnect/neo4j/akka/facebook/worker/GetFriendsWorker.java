package com.campusconnect.neo4j.akka.facebook.worker;

import akka.actor.TypedActor;
import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.facebook.task.FriendsListTask;
import com.github.sabomichal.akkaspringfactory.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Component;

/**
 * Created by sn1 on 3/2/15.
 */

@Actor
public class GetFriendsWorker extends UntypedActor {

    @Autowired
    private FacebookConnectionFactory facebookConnectionFactory;
    
    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof FriendsListTask) {
            FriendsListTask friendsListTask = (FriendsListTask) o;
//            Connection<Facebook> facebook = facebookConnectionFactory.createConnection(new AccessGrant(accessToken));
//        final PagedList<Reference> friends = facebook.getApi().friendOperations().getFriends();
            getSender().tell(friendsListTask.getAccessToken(), getSelf());
            
        }
    }
}
