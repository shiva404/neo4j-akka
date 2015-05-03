package com.campusconnect.neo4j.akka.goodreads.worker;

import org.springframework.beans.factory.annotation.Autowired;

import akka.actor.UntypedActor;

import com.campusconnect.neo4j.akka.goodreads.task.AddFriendsFromGoodReadsTask;
import com.campusconnect.neo4j.akka.goodreads.task.AddGoodreadsBookToUserTask;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.GoodreadsStatus;
import com.campusconnect.neo4j.types.ReadRelation;
import com.campusconnect.neo4j.types.User;
import com.campusconnect.neo4j.types.WishListRelationship;

public class AddFriendsFromGoodReadsWorker extends UntypedActor {
	
	    @Autowired
	    private UserDao userDao;
	    
	    @Override
	    public void onReceive(Object message) throws Exception {
	        if(message instanceof AddFriendsFromGoodReadsTask) {
	        	AddFriendsFromGoodReadsTask addFriendsFromGoodReadsTask = (AddFriendsFromGoodReadsTask) message;
	        	
	        	for(com.campusconnect.neo4j.akka.goodreads.types.User goodReadsUser :addFriendsFromGoodReadsTask.getFriends().getUser())
	        	{
	        		User grUser = userDao.getUserByGoodreadsId(goodReadsUser.getId());
	        		if(null!= grUser && null!=userDao.getUsersRelationShip(addFriendsFromGoodReadsTask.getUser(), grUser))
	        		{
	        			userDao.createFriendRelation(addFriendsFromGoodReadsTask.getUser(),grUser);
	        		}
	        	}
	        }
	    }
}
