package com.campusconnect.neo4j.akka.goodreads;

import com.campusconnect.neo4j.util.StringUtils;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

/**
 * Created by sn1 on 3/8/15.
 */
public class XmlToJson {
    public static void main(String[] args) {
        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<GoodreadsResponse>\n" +
                "  <Request>\n" +
                "    <authentication>true</authentication>\n" +
                "      <key><![CDATA[QLM3lL2nqXe4LujHQt12A]]></key>\n" +
                "    <method><![CDATA[friend_user]]></method>\n" +
                "  </Request>\n" +
                "  <friends start=\"1\" end=\"15\" total=\"15\">\n" +
                "    <user>\n" +
                "      <id>7762117</id>\n" +
                "      <name>Sonam</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/7762117-sonam]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1355239910p3/7762117.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1355239910p2/7762117.jpg]]></small_image_url>\n" +
                "      <friends_count>59</friends_count>\n" +
                "      <reviews_count>99</reviews_count>\n" +
                "  \t\t\t<created_at>Mon Sep 23 09:25:55 -0700 2013</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>3077400</id>\n" +
                "      <name>Sravan</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/3077400-sravan]]></link>\n" +
                "      <image_url><![CDATA[https://s.gr-assets.com/assets/nophoto/user/u_111x148-9394ebedbb3c6c218f64be9549657029.png]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://s.gr-assets.com/assets/nophoto/user/u_50x66-632230dc9882b4352d753eedf9396530.png]]></small_image_url>\n" +
                "      <friends_count>79</friends_count>\n" +
                "      <reviews_count>38</reviews_count>\n" +
                "  \t\t\t<created_at>Tue Dec 03 08:04:40 -0800 2013</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>11796232</id>\n" +
                "      <name>Akshatha</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/11796232-akshatha]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1350148677p3/11796232.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1350148677p2/11796232.jpg]]></small_image_url>\n" +
                "      <friends_count>83</friends_count>\n" +
                "      <reviews_count>105</reviews_count>\n" +
                "  \t\t\t<created_at>Tue Sep 24 08:47:32 -0700 2013</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>9851897</id>\n" +
                "      <name>Darshan</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/9851897-darshan]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1338270729p3/9851897.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1338270729p2/9851897.jpg]]></small_image_url>\n" +
                "      <friends_count>53</friends_count>\n" +
                "      <reviews_count>31</reviews_count>\n" +
                "  \t\t\t<created_at>Sun Jan 26 05:49:11 -0800 2014</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>4376941</id>\n" +
                "      <name>Abhinand</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/4376941-abhinand]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1286272334p3/4376941.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1286272334p2/4376941.jpg]]></small_image_url>\n" +
                "      <friends_count>96</friends_count>\n" +
                "      <reviews_count>69</reviews_count>\n" +
                "  \t\t\t<created_at>Wed Aug 28 11:15:23 -0700 2013</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>39308143</id>\n" +
                "      <name>Mithra</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/39308143-mithra]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1421607814p3/39308143.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1421607814p2/39308143.jpg]]></small_image_url>\n" +
                "      <friends_count>146</friends_count>\n" +
                "      <reviews_count>1</reviews_count>\n" +
                "  \t\t\t<created_at>Sun Jan 18 11:04:34 -0800 2015</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>33596190</id>\n" +
                "      <name>Soumya Goud</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/33596190-soumya-goud]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1406997887p3/33596190.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1406997887p2/33596190.jpg]]></small_image_url>\n" +
                "      <friends_count>23</friends_count>\n" +
                "      <reviews_count>0</reviews_count>\n" +
                "  \t\t\t<created_at>Sat Aug 02 09:46:09 -0700 2014</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>38928759</id>\n" +
                "      <name>Prashant Andani</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/38928759-prashant-andani]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1420744133p3/38928759.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1420744133p2/38928759.jpg]]></small_image_url>\n" +
                "      <friends_count>28</friends_count>\n" +
                "      <reviews_count>0</reviews_count>\n" +
                "  \t\t\t<created_at>Thu Jan 08 11:09:05 -0800 2015</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>38731012</id>\n" +
                "      <name>Sushma Ch</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/38731012-sushma-ch]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1420363948p3/38731012.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1420363948p2/38731012.jpg]]></small_image_url>\n" +
                "      <friends_count>26</friends_count>\n" +
                "      <reviews_count>33</reviews_count>\n" +
                "  \t\t\t<created_at>Sun Jan 04 01:32:42 -0800 2015</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>24433197</id>\n" +
                "      <name>Sushanth Kamath</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/24433197-sushanth-kamath]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1380830585p3/24433197.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1380830585p2/24433197.jpg]]></small_image_url>\n" +
                "      <friends_count>57</friends_count>\n" +
                "      <reviews_count>5</reviews_count>\n" +
                "  \t\t\t<created_at>Thu Oct 03 13:03:30 -0700 2013</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>38017148</id>\n" +
                "      <name>Rishabh Pansari</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/38017148-rishabh-pansari]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1418798204p3/38017148.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1418798204p2/38017148.jpg]]></small_image_url>\n" +
                "      <friends_count>31</friends_count>\n" +
                "      <reviews_count>1</reviews_count>\n" +
                "  \t\t\t<created_at>Tue Dec 16 22:36:57 -0800 2014</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>36497981</id>\n" +
                "      <name>Vishwanath Nagaraj</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/36497981-vishwanath-nagaraj]]></link>\n" +
                "      <image_url><![CDATA[https://s.gr-assets.com/assets/nophoto/user/m_111x148-e08b4682eea7088f8cdfd67c131d51cd.png]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://s.gr-assets.com/assets/nophoto/user/m_50x66-82093808bca726cb3249a493fbd3bd0f.png]]></small_image_url>\n" +
                "      <friends_count>38</friends_count>\n" +
                "      <reviews_count>2</reviews_count>\n" +
                "  \t\t\t<created_at>Sat Nov 01 10:16:21 -0700 2014</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>35783297</id>\n" +
                "      <name>Kusuma Sanjeev</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/35783297-kusuma-sanjeev]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1412866782p3/35783297.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1412866782p2/35783297.jpg]]></small_image_url>\n" +
                "      <friends_count>81</friends_count>\n" +
                "      <reviews_count>5</reviews_count>\n" +
                "  \t\t\t<created_at>Thu Oct 09 08:00:13 -0700 2014</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>35254503</id>\n" +
                "      <name>Suman Bharadwaj</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/35254503-suman-bharadwaj]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1411437954p3/35254503.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1411437954p2/35254503.jpg]]></small_image_url>\n" +
                "      <friends_count>61</friends_count>\n" +
                "      <reviews_count>1</reviews_count>\n" +
                "  \t\t\t<created_at>Mon Sep 22 19:06:23 -0700 2014</created_at>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "      <id>28804990</id>\n" +
                "      <name>Chaitali Choudhary</name>\n" +
                "      <link><![CDATA[https://www.goodreads.com/user/show/28804990-chaitali-choudhary]]></link>\n" +
                "      <image_url><![CDATA[https://d.gr-assets.com/users/1392705455p3/28804990.jpg]]></image_url>\n" +
                "      <small_image_url><![CDATA[https://d.gr-assets.com/users/1392705455p2/28804990.jpg]]></small_image_url>\n" +
                "      <friends_count>61</friends_count>\n" +
                "      <reviews_count>2</reviews_count>\n" +
                "  \t\t\t<created_at>Mon Feb 17 22:37:44 -0800 2014</created_at>\n" +
                "    </user>\n" +
                "</friends>\n" +
                "\n" +
                "</GoodreadsResponse>";


                XMLSerializer xmlSerializer = new XMLSerializer();
                JSON json = xmlSerializer.read(StringUtils.cleanEmptyTags(data));
                System.out.println( json.toString(2) );


    }
}
