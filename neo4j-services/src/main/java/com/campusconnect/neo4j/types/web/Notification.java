package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.Target;

/**
 * Created by nam on 4/30/15.
 */
public class Notification {

    private Target target;
    private Long timeStamp;
    private String type;

    public Notification() {
        super();
    }


	public Notification(Target target, Long timeStamp,String type) {
        super();
        this.target = target;
        this.timeStamp = timeStamp;
        this.type = type;
    }


	public Target getTarget() {
        return target;
    }


    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getType() {
		return type;
	}

    public void setTarget(Target target) {
        this.target = target;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setType(String type) {
		this.type = type;
	}


}
