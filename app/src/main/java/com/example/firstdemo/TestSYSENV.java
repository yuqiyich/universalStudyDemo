package com.example.firstdemo;


import java.util.Map;

public class TestSYSENV {
    public static void main(String[] args)   {
        System.out.println("******************************Environment Vars*****************************");
        Map<String, String> enviorntmentVars  = System.getenv();
        enviorntmentVars.entrySet().forEach(System.out::println);

    }


}
