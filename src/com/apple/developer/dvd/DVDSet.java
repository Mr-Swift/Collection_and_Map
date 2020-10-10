package com.apple.developer.dvd;

@SuppressWarnings("all")
public class DVDSet {
    private String name = null;
    private String state = "可出借";
    private String date = " ";              //出借日期
    private int counts = 0;                 //出借次数

    protected DVDSet(String name) {       //构造方法，DVD刚被添加时，默认状态为可出借、出借日期为空、出借次数为0
        this.name = name;
        this.state = "可出借";
        this.date = " ";
        this.counts = 0;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getState() {
        return state;
    }

    protected void setState(String state) {
        this.state = state;
    }

    protected String getDate() {
        return date;
    }

    protected void setDate(String date) {
        this.date = date;
    }

    protected int getCounts() {
        return counts;
    }


    protected void setCounts(int counts) {
        this.counts = counts;
    }

    protected void countsAdd() {           //出借次数+1
        this.counts++;
    }

    protected void reSetCounts() {         //出借次数归零
        this.setCounts(0);
    }

    protected String getInfo() {
        return this.getName() + "\t\t\t" + this.getCounts() + "\t\t\t" + this.getState() + "\t\t\t" + this.getDate();
    }

}
