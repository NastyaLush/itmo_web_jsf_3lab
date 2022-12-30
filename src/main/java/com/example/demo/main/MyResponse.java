package com.example.demo.main;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Map;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)public class MyResponse {
    protected Response.Status status;
    protected int statusCode;
    protected String message;
    protected String developerMessage;
    protected Map<?,?> data;

    public MyResponse(Response.Status status, int statusCode, String message, String developerMessage, Map<?, ?> data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.developerMessage = developerMessage;
        this.data = data;
    }

    public MyResponse(Response.Status status, int statusCode, String message, String developerMessage) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.developerMessage = developerMessage;
    }
}
