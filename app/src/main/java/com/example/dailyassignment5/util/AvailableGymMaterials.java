package com.example.dailyassignment5.util;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AvailableGymMaterials {

    private ArrayList<String> allMaterials;
    private ArrayList<String> URLs;

    public AvailableGymMaterials() {
        allMaterials = new ArrayList<String>();
        allMaterials.add("Weights");
        allMaterials.add("Squat Rack");
        allMaterials.add("Bicycle");
        allMaterials.add("Treadmill");
        allMaterials.add("Yoga_Mat");
        allMaterials.add("Medicine_Ball");
        allMaterials.add("Elliptical");
        allMaterials.add("Pool");
        URLs = new ArrayList<String>();
        URLs.add("https://www.gymsource.com/media/prod.thumb/h/a/hampton-hex-dumbbells.jpg");
        URLs.add("https://images-na.ssl-images-amazon.com/images/I/71%2Bqll7qnOL._SY550_.jpg");
        URLs.add("https://images.giant-bicycles.com/b_white,c_pad,h_650,q_80/gh3epa7lkkzvn9zrewg1/MY19-ATX-3-27-5_Color-B.jpg");
        URLs.add("https://res.cloudinary.com/iconfitness/image/upload/dpr_3.0,f_auto,fl_progressive.lossy,q_auto,w_500/v1/site-51/NTL17219_gallery_1_L%402x.jpg");
        URLs.add("https://www.yogaaccessories.com/assets/images/1_4inch%20yoga%20mat_website.jpg");
        URLs.add("https://blackmountainproducts.com/wp-content/uploads/2015/10/Medicine-Ball-Group-01.jpg");
        URLs.add("https://ik.imagekit.io/solefitness/WebImages/tr:w-700,q-100/E35_right-new-2020.png");
        URLs.add("https://www.inquirer.com/resizer/c9I_UVpa8Fl4rx75h3GnulQkAxI=/1400x932/smart/arc-anglerfish-arc2-prod-pmn.s3.amazonaws.com/public/4FZ3FON5FREXJFO25DECVPIOMY.jpg");
    }

    //three getters for arraylists
    public String getURL(int n){
        return URLs.get(n);
    }

    public String getMaterials(int n) {
        if(n>=allMaterials.size()){
            return "";
        }else{
            return allMaterials.get(n);
        }
    }

    public ArrayList<String> getAllMaterials(){
        return allMaterials;
    }



    //turn the string from preferences (coordinates) into an arraylist of
    public ArrayList<String> convertString(String input){
        if(input.length()==0){
            return new ArrayList<String>();
        }
        ArrayList<String> toReturn = new ArrayList<String>();
        String[] coordinates = input.split(" ");
        int[][] list = new int[coordinates.length][2];
        for(int i =  0; i<coordinates.length; i++){
            String[] valuesInCoordinates = coordinates[i].split(",");
            list[i][0]=Integer.parseInt(valuesInCoordinates[0]);
            list[i][1]=Integer.parseInt(valuesInCoordinates[1]);
        }
        for(int i = 0; i<list.length; i++){
            for(int j = i+1; j<list.length;j++){
                boolean fix = false;
                int n = 0;
                if(list[j][0]==-1){
                    break;
                    //do nothing
                }else if(list[i][0]==list[j][0]){
                    list[i][1]+=list[j][1];
                    list[j][0]=-1;
                }
            }
            if(list[i][0]!=-1){
                if(list[i][1]==1){
                    toReturn.add(list[i][1] + " " + getMaterials(list[i][0]));
                }else {
                    toReturn.add(list[i][1] + " " + getMaterials(list[i][0]) + "s");
                }
            }
        }
        return toReturn;
    }




}
