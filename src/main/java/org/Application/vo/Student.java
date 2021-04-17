package org.Application.vo;

public class Student {
    private int Sid;
    private String name;
    private String mail;
    private String password;
    private String gender;
    private int year;
    private int height;
    private int weight;
    private int level;
    private String date;
    private int Tid = 0;
    public Student(int a, String b, String mail, String c, String gender, int year, int height, int weight, int level, String date,int Tid){
        Sid = a;
        name = b;
        this.mail = mail;
        password = c;
        this.gender = gender;
        this.year = year;
        this.height = height;
        this.weight = weight;
        this.level = level;
        this.date = date;
        this.Tid = Tid;
    }
    public void setId(int id) {this.Sid = id;}
    public void setName(String name) {this.name = name;}
    public int getId() {
        return Sid;
    }
    public String getName(){
        return name;
    }
    public void setPass(String pass){this.password = pass;}
    public String getPass() {return password;}
    public String getMail() {return mail;}
    public String getGender() {return gender;}
    public String getDate() {return date;}
    public int getYear() {return year;}
    public int getHeight() {return height;}
    public int getWeight() {return weight;}
    public int getLevel() {return level;}
    public int getTid() {return Tid;}
}
