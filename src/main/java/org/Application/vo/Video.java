package org.Application.vo;

public class Video {
    private int Vid;
    private String name;
    private String description;
    private String vidPath;
    private int Tid;
    private int category;
    public Video(int Vid, String name, String description, String vidPath, int Tid){
        this.Vid = Vid;
        this.name = name;
        this.description = description;
        this.vidPath = vidPath;
        this.Tid = Tid;
    }

    public int getVid() {
        return Vid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVidPath() {
        return vidPath;
    }

    public int getTid() {
        return Tid;
    }

    public int getCategory() {
        return category;
    }
}