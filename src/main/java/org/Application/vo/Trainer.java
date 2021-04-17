package org.Application.vo;

public class Trainer {
    private int Tid;
    private String mail;
    private String password;
    private String gender;
    private String name;
    private String strength;

    public Trainer(int tid,String mail,String password,String gender,String name,String strength){
        this.Tid = tid;
        this.mail = mail;
        this.password  = password;
        this.gender = gender;
        this.name = name;
        this.strength  = strength;
    }
    public int getTid(){return Tid;}
    public String getMail() {return mail;}
    public String getPassword() {return password;}
    public String getGender() {return gender;}
    public String getName() {return name;}
    public String getStrength() {return strength;}

}