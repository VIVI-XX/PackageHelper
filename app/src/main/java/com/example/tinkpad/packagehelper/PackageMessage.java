package com.example.tinkpad.packagehelper;

import java.io.Serializable;
import java.sql.Date;

public class PackageMessage implements Serializable {
    private  String com;
    private  String num;
    private String date;


    public PackageMessage(String com, String num, String date) {
        this.com = com;
        this.num = num;
        this.date = date;

    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}


