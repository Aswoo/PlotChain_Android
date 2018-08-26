package com.example.yunseung_u.plotchain.util;

public class GenreHelper {


    public static int switchStringToCode(String genre){

        int genreCode = 0;
        switch (genre){
            case "판타지":
                genreCode = 0;
                break;
            case "로맨스":
                genreCode = 1;
                break;
            case "무협":
                genreCode = 2;
                break;
            case "게임":
                genreCode =3;
                break;
            case "추리":
                genreCode =4;
                break;
            case "팬픽":
                genreCode = 5;
                break;
            case "라이트노벨":
                genreCode = 6;
                break;
            case "BL":
                genreCode = 7;
                break;
            case "GL":
                genreCode = 8;
                break;
        }
        return genreCode;

    }

    public static String switchCodeToString(int genreCode){
        String genre = null;
        switch (genreCode){
            case 0:
                genre = "판타지";
                break;
            case 1:
                genre = "로맨스";
                break;
            case 2:
                genre = "무협";
                break;
            case 3:
                genre = "게임";
                break;
            case 4:
                genre = "추리";
                break;
            case 5:
                genre = "팬픽";
                break;
            case 6:
                genre = "라이트노밸";
                break;
            case 7:
                genre = "BL";
                break;
            case 8:
                genre = "GL";
                break;
        }
        return genre;
    }
}
