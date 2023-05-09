package com.example.statisticservice.model;

import java.util.Date;

public class StatisticDTO {
    private String message;
    private Date createDate;

    public StatisticDTO() {
    }

    public StatisticDTO(String message, Date createDate) {
        super();
        this.message = message;
        this.createDate = createDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
