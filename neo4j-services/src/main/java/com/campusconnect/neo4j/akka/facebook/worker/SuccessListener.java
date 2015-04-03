package com.campusconnect.neo4j.akka.facebook.worker;

import akka.actor.UntypedActor;
import com.github.sabomichal.akkaspringfactory.Actor;

/**
 * Created by sn1 on 3/2/15.
 */

@Actor
public class SuccessListener extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof String){

        }
    }
}
