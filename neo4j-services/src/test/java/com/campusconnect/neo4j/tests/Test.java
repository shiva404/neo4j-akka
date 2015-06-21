package com.campusconnect.neo4j.tests;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/12/15
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        String baseString = "";
        String[] result = baseString.split("\\s*,\\s*");
        for (String r : result) {
            System.out.println(r);
        }

        List<String> arrayList = Arrays.asList(result);
        String data = new String("/fdp/doc/thumbnails");
        System.out.println(arrayList.contains(data));
        System.out.println(arrayList.contains("/fdp/doc/thumbnails"));
    }
}
