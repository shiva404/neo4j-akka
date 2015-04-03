package com.campusconnect.neo4j.types;

/**
 * Created by sn1 on 3/1/15.
 */
public class Neo4jErrorResponse {
    protected String code;
    protected String type;
    protected String message;
    protected String detail;
    protected String moreInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Neo4jErrorResponse(String code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public Neo4jErrorResponse() {
    }

    public Neo4jErrorResponse(String code, String type, String message, String detail, String moreInfo) {
        this.code = code;
        this.type = type;
        this.message = message;
        this.detail = detail;
        this.moreInfo = moreInfo;
    }
}
