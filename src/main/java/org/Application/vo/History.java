package org.Application.vo;

/**
 * @author MCMainTank
 * @create 2021/4/15
 */
public class History {
    private int Hid;
    private int Vid;
    private int Sid;
    private String name;
    private String filepath;
    private String category;
    private String date;
    private int Tid;
    private int deleted;
    public History(int Hid, int Vid, int Sid, String name, String filepath, String category, String date, int Tid, int deleted){
        this.Hid=Hid;
        this.Vid=Vid;
        this.Sid=Sid;
        this.name=name;
        this.filepath=filepath;
        this.category=category;
        this.date=date;
        this.Tid=Tid;
        this.deleted=deleted;
    }

    public int getTid() {
        return Tid;
    }

    public int getVid() {
        return Vid;
    }

    public int getDeleted() {
        return deleted;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getName() {
        return name;
    }

    public int getHid() {
        return Hid;
    }

    public int getSid() {
        return Sid;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

}
