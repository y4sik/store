package com.yasik.service.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ErrorResponseBody {

    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String timeStamp;
    private int status;
    private String message;


    public ErrorResponseBody() {

    }

    public ErrorResponseBody(int status, String message, LocalDateTime timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = formatDate(timeStamp);
    }

    private String formatDate(LocalDateTime timeStamp) {
        return timeStamp.format(DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = formatDate(timeStamp);
    }

    @Override
    public String toString() {
        return "ErrorResponseBody{" +
                "timeStamp='" + timeStamp + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
