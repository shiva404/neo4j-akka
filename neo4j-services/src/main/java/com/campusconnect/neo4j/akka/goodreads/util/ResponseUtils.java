package com.campusconnect.neo4j.akka.goodreads.util;

import com.campusconnect.neo4j.util.StringUtils;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by sn1 on 3/10/15.
 */
public class ResponseUtils {
    public static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static <T> T getEntity(String xmlData, Class<T> clazz) throws IOException {
        try {
            XMLSerializer xmlSerializer = new XMLSerializer();
            final String cleanedUpXML = StringUtils.cleanEmptyTags(xmlData);
//        logger.debug("Cleaned up data \n" + cleanedUpXML);
            JSON json = xmlSerializer.read(cleanedUpXML);
//        logger.debug("Converted Json \n" + json);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T response = objectMapper.readValue(json.toString(), clazz);
            return response;
        } catch (Exception e) {
            logger.error("Failed to deserialize:\n" + "XML:" + xmlData, e);
        }
        return null;
    }

//    public static String getStandardShelfName(List<Shelf> shelfList) {
//        if (shelfList != null)
//        for (Shelf shelf : shelfList) {
//            if(shelf.getName().equals(GoodreadsStatus.READ.toString()))
//                return GoodreadsStatus.READ.toString();
//            else if(shelf.getName().equals(GoodreadsStatus.TO_READ.toString()))
//                return GoodreadsStatus.TO_READ.toString();
//            else if(shelf.getName().equals(GoodreadsStatus.CURRENTLY_READING.toString()))
//                return GoodreadsStatus.CURRENTLY_READING.toString();
//            else
//                GoodreadsStatus.READ.toString();
//        }
//        return GoodreadsStatus.READ.toString();
//    }

}
