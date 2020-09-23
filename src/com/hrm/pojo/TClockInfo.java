package com.hrm.pojo;

import java.util.Date;

public class TClockInfo {
    private Integer clockId;

    private String employeeNo;

    private Date clockInTime;

    private Date clockOffTime;

    private Date clockDate;

    public Integer getClockId() {
        return clockId;
    }

    public void setClockId(Integer clockId) {
        this.clockId = clockId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo == null ? null : employeeNo.trim();
    }

    public Date getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Date clockInTime) {
        this.clockInTime = clockInTime;
    }

    public Date getClockOffTime() {
        return clockOffTime;
    }

    public void setClockOffTime(Date clockOffTime) {
        this.clockOffTime = clockOffTime;
    }

    public Date getClockDate() {
        return clockDate;
    }

    public void setClockDate(Date clockDate) {
        this.clockDate = clockDate;
    }
}