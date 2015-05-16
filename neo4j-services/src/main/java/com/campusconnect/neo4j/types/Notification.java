package com.campusconnect.neo4j.types;

/**
 * Created by nam on 4/30/15.
 */
public class Notification {

    private Target target;
    private Long timeStamp;

    public Notification() {
        super();
    }


    public Notification(Target target, Long timeStamp) {
        super();
        this.target = target;
        this.timeStamp = timeStamp;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }


}
