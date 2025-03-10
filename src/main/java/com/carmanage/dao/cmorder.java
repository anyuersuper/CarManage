package com.carmanage.dao;

import java.util.Date;

public class cmorder {
    private Integer orderid;

    private Integer uid;

    private Integer money;

    private Date starttime;

    private Date finishedtime;

    private String status;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getFinishedtime() {
        return finishedtime;
    }

    public void setFinishedtime(Date finishedtime) {
        this.finishedtime = finishedtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}