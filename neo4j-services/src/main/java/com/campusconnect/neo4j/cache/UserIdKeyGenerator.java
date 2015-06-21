package com.campusconnect.neo4j.cache;

import com.campusconnect.neo4j.types.neo4j.User;
import com.googlecode.ehcache.annotations.key.CacheKeyGenerator;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

/**
 * Created by sn1 on 3/6/15.
 */
public class UserIdKeyGenerator implements CacheKeyGenerator {

    @Override
    public Serializable generateKey(MethodInvocation methodInvocation) {
        return null;
    }

    @Override
    public Serializable generateKey(Object... data) {
        if (data.length == 1) {
            if (data[0] instanceof User) {
                return ((User) data[0]).getId();
            } else if (data[0] instanceof String) {
                return (String) data[0];
            }
        }
        return null;
    }

}
