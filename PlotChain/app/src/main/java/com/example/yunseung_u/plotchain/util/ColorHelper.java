package com.example.yunseung_u.plotchain.util;

public class ColorHelper {

    public static String positionToColor(int position){

        String color = null;

        switch (position){
            case 0:
                color = "#575d63";
                break;
            case 1:
                color = "#bec3cd";
                break;
            case 2:
                color = "#ffa8a8";
                break;
            case 3:
                color = "#bfa9ff";
                break;
            case 4:
                color = "#7ce3ff";
                break;
            case 5:
                color = "#6cb8ff";
                break;
            case 6:
                color = "#63e6be";
                break;
            case 7:
                color = "#ffca8d";
                break;
        }

        return color;

    }
    public static String backgourndToColor(int position){

        String color = "";

        switch (position){
            case 0:
                color = "#2e3237";
                break;
            case 1:
                color = "#dde1e9";
                break;
            case 2:
                color = "#ff8787";
                break;
            case 3:
                color = "#a88cfa";
                break;
            case 4:
                color = "#59dbff";
                break;
            case 5:
                color = "#4fa8fc";
                break;
            case 6:
                color = "#37d9a9";
                break;
            case 7:
                color = "#ffbd71";
                break;
        }
        return color;

    }
}
