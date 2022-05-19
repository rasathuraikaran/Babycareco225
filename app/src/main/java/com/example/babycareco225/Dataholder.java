package com.example.babycareco225;

public class Dataholder {

    public int getHV() {
        return HV;
    }

    public void setHV(int HV) {
        this.HV = HV;
    }

    public int getLV() {
        return LV;
    }

    public void setLV(int LV) {
        this.LV = LV;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    private int HV;
    public Dataholder(){

    }

    public Dataholder(int HV, int LV, int month) {
        this.HV = HV;
        this.LV = LV;
        this.month = month;
    }

    private int LV;
     private int month;
}
