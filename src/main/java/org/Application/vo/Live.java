package org.Application.vo;

public class Live {
    private int Sid;
    private int Tid;
    private String date;

    public Live(int sid,int tid,String date){
        this.Sid = sid;
        this.Tid = tid;
        this.date = date;
    }

    public int getSid(){return Sid;}
    public int getTid() {return Tid;}
    public String getDate() {return date;}
}