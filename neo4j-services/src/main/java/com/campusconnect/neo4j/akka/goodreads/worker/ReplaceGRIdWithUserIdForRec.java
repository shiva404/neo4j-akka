package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.task.ReplaceGRIdWithUserIdForRecTask;
import com.campusconnect.neo4j.da.UtilsDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sn1 on 5/5/15.
 */
public class ReplaceGRIdWithUserIdForRec extends UntypedActor {
    @Autowired
    UtilsDao utilsDao;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ReplaceGRIdWithUserIdForRecTask) {
            ReplaceGRIdWithUserIdForRecTask replaceGRIdWithUserIdForRecTask = (ReplaceGRIdWithUserIdForRecTask) message;
            utilsDao.getAndReplaceGRRecByGoodreadsId(replaceGRIdWithUserIdForRecTask.getGoodreadsId(), replaceGRIdWithUserIdForRecTask.getUserId(), replaceGRIdWithUserIdForRecTask.getImageUrl());
        }
    }
}
