package com.yasik.service.excetpion.handle;

import java.time.LocalDateTime;


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
        return timeStamp.getYear() + "-" + timeStamp.getMonthValue() + "-" + timeStamp.getDayOfMonth() +
                ", " + timeStamp.getHour() + ":" + timeStamp.getMinute() + ":" + timeStamp.getSecond();
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
