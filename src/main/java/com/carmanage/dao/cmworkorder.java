package com.carmanage.dao;

import java.util.Date;

public class cmworkorder {
    private Integer workorderid;

    private Integer uid;

    private Integer cmuid;

    private Date starttime;

    private Date finishedtime;

    private String status;

    public Integer getWorkorderid() {
        return workorderid;
    }

    public void setWorkorderid(Integer workorderid) {
        this.workorderid = workorderid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCmuid() {
        return cmuid;
    }

    public void setCmuid(Integer cmuid) {
        this.cmuid = cmuid;
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