package com.punitexample.collection_tracker;

public class User {
    String iName,sNum,mDate,iDate,sDate;
    public User(String iName, String sNum, String mDate, String iDate, String sDate) {
        this.iName = iName;
        this.sNum = sNum;
        this.mDate = mDate;
        this.iDate = iDate;
        this.sDate = sDate;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getiDate() {
        return iDate;
    }

    public void setiDate(String iDate) {
        this.iDate = iDate;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }
    public User(){}
}
