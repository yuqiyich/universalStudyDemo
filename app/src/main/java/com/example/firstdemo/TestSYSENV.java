package com.example.firstdemo;


import java.util.Map;

public class TestSYSENV {
    public static void main(String[] args)   {
        System.out.println("******************************Environment Vars*****************************");
        Map<String, String> enviorntmentVars  = System.getenv();
        //appVersion:300000150phone num:deviceId:99001155052774phoneBrand:OnePlus-ONEPLUS A5010androidApiVer:28channel:null
        String rule="hitGray(%d,'%s','%s','%s',%d,'%s')";
//        enviorntmentVars.entrySet().forEach(System.out::println);
        System.out.println(String.format(rule,300000150,"","99001155052774","OnePlus-ONEPLUS A5010",28,null));

    }


}
