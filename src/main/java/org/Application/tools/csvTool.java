package org.Application.tools;

import com.opencsv.*;
import org.Application.Main;
import org.Application.launcher;
import org.Application.vo.*;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.*;
import java.net.URL;
import java.util.Objects;

//javacsv 简单教程 io流就按这个写就好
public class csvTool {
    public static String getDir(){
        if(Main.class.getResource("").toString().charAt(0) != 'j'){
            return System.getProperty("user.dir")+"/src/main/resources/org/Application/storage/";
        }else{
            return "D:/SE-JavaFX/storage/";
        }
    }

    public static void cleanCur(String name){
        String url = getDir()+""+name+".csv";
        File file = new File(url);
        try{
            file.delete();
            file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String[] readCur(String name){
        //idea的相对路径是 src/else
        //URL url = Main.class.getResource("storage/"+name+".csv");
        String url = getDir()+""+name+".csv";
        String[] res = new String[100];
        int count = 0,i = 0;
        // 创建CSV读对象
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(url)));
            String[] str;
            while ((str  = reader.readNext()) != null) {
                for (String s : str) {
                    res[i] = s;
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return res;
    }

    public static void write(String name,String[] str){
        //用string数组按行写入csv
        String url = getDir()+""+name+".csv";

        try {

            // 创建CSV写对象
            //用java.io不覆盖写csv
            FileWriter fileWriter = new FileWriter(url, true);
            CSVWriter writer = new CSVWriter(fileWriter);
            //CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("UTF-8"));
            //CsvWriter csvWriter = new CsvWriter(filePath);

            // csv的表头和内容没区别，规定好数据就不需要写表头了
            writer.writeNext(str);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Student[] searchStu(String text){
        String url = getDir()+"student.csv";
        Student[] stu = new Student[100];
        int i = 0;

        try {
            // 创建CSV读对象
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(url)));


            // 读表头
            reader.readNext();
            //System.out.println(csvReader.getHeader(0)+"         "+csvReader.getHeader(1));
            String[] str;
            while ((str  = reader.readNext()) != null){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
                // 读这行的特定列 get(column_index) 0-n-1
                int Sid = Integer.parseInt(str[0]);
                String name = str[1];

                if(Objects.equals(name, text)){
                    String mail = str[2];
                    String pass = str[3];
                    String gender = str[4];
                    int year = Integer.parseInt(str[5]);
                    int height = Integer.parseInt(str[6]);
                    int weight  = Integer.parseInt(str[7]);
                    int level = Integer.parseInt(str[8]);
                    String date = str[9];
                    int Tid = Integer.parseInt(str[10]);
                    stu[i] = new Student(Sid,name, mail, pass, gender, year, height, weight, level, date,Tid);
                    i++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i==0) return null;
        else return stu;
    }
    public static Trainer[] searchTrainer(String Text, int index){
        String url = getDir()+"trainer.csv";
        Trainer[] tra = new Trainer[100];
        int i = 0;
        int j = 0;
        try {
            // 创建CSV读对象
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(url)));

            // 读表头
            reader.readNext();
            String[] str;
            while ((str  = reader.readNext()) != null){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
                // 读这行的特定列 get(column_index) 0-n-1
                int id = Integer.parseInt(str[0]);
                String mail = str[1];
                String pass = str[2];
                String gender = str[3];
                String name = str[4];
                String strength = str[5];
                if(index == 0){
                    if(Objects.equals(Text, str[0])){
                        tra[i] = new Trainer(id, mail, pass, gender, name,strength);
                        i++;
                        break;
                    }
                }else if(index ==1){
                    if(Objects.equals(Text, str[5])){
                        tra[i] = new Trainer(id, mail, pass, gender, name,strength);
                        i++;
                    }
                }
                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i==0) return null;
        else return tra;
    }

    public static Live[] searchLive(int id, int index){
        String url = getDir()+"live.csv";

        Live[] lives = new Live[100];
        int i = 0;
        int j = 0;
        try {
            // 创建CSV读对象
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(url)));

            // 读表头
            reader.readNext();
            String[] str;
            while ((str  = reader.readNext()) != null){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
                // 读这行的特定列 get(column_index) 0-n-1
                int Sid = Integer.parseInt(str[0]);
                int Tid = Integer.parseInt(str[1]);
                String date = str[2];
                if(index == 0){
                    if(Sid == id){
                        lives[i] = new Live(Sid,Tid,date);
                        i++;
                    }
                }else if(index ==1){
                    if(Tid == id){
                        lives[i] = new Live(Sid,Tid,date);
                        i++;
                    }
                }
                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        if(i==0) return null;
        else return lives;

    }

    public static Video[] selectVid(String text, int index){
        String filePath = getDir()+"video.csv";
        Video[] videos = new Video[100];
        int i = 0;
        int j = 0;
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath,'|');

            // 读表头
            csvReader.readHeaders();
            //System.out.println(csvReader.getHeader(0)+"         "+csvReader.getHeader(1));
            while (csvReader.readRecord() && j<100){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
                // 读这行的特定列 get(column_index) 0-n-1
                int Vid = Integer.parseInt(csvReader.get(0));
                String name = csvReader.get(1);
                String description = csvReader.get(2);
                String vidPath = csvReader.get(3);
                int Tid = Integer.parseInt(csvReader.get(4));
                String category = csvReader.get(5);
                System.out.println(Vid+name+description+vidPath+Tid+category);
                if(index == 0){
                    if(text == category){
                        videos[i] = new Video(Vid,name,description,vidPath,Tid);
                        i++;
                    }
                }
//                else if(index ==1){
//                    if(Tid == id){
//                        videos[i] = new Video(Sid,Tid,date);
//                        i++;
//                    }
//                }
                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i==0) return null;
        else return videos;
    }

    public static History[] searchHist(int id, int index){
        String filePath = getDir()+"Hist.csv";
        History[] histories = new History[100];
        int i = 0;
        int j = 0;
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath);

            // 读表头
            csvReader.readHeaders();
            //System.out.println(csvReader.getHeader(0)+"         "+csvReader.getHeader(1));
            while (csvReader.readRecord() && j<100){
                // 读一整行
                //System.out.println(csvReader.getRawRecord());
                // 读这行的特定列 get(column_index) 0-n-1
                int Hid = Integer.parseInt(csvReader.get(0));
                int Sid = Integer.parseInt(csvReader.get(1));
                int Vid = Integer.parseInt(csvReader.get(2));
                String name = csvReader.get(3);
                String vidPath = csvReader.get(4);
                String category = csvReader.get(5);
                String date = csvReader.get(6);
                int Tid = Integer.parseInt(csvReader.get(7));
                int deleted = Integer.parseInt(csvReader.get(8));
                System.out.println(Hid+Sid+Vid+name+vidPath+Tid+category);
                if(index == 0){
                    if(id == Sid){
                        histories[i] = new History(Hid,Sid,Vid,name,vidPath,category,date,Tid,deleted);
                        i++;
                    }
                }
//                else if(index ==1){
//                    if(Tid == id){
//                        videos[i] = new Video(Sid,Tid,date);
//                        i++;
//                    }
//                }
                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(i==0) return null;
        else return histories;
    }

    public static String readCurVid() throws IOException {
        String filePath = getDir()+"CurVid.csv";
        CsvReader csvReader = new CsvReader(filePath);
        csvReader.readRecord();
        String result=csvReader.get(0);
        System.out.println(result);
        return result;
    }


}
