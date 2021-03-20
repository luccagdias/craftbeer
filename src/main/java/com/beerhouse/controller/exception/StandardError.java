package com.beerhouse.controller.exception;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String message;
    private String timeStamp;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public StandardError(Integer status, String message, Date timeStamp) {
        super();
        this.status = status;
        this.message = message;
        this.timeStamp = formatter.format(timeStamp);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
