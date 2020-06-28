package com.example.firstdemo.test;

import android.content.Context;

/**
 * @Description:[一句话说明类的作用]
 * @Author: [yuqi]
 * @CreateDate: [2019-11-29 15:25]
 * @email: [yichitgo@gmail.com]
 */
public class MulitAppCheckUtil {
    //比翼多开的类似应用多开
   public static String  getMulitAppContextPath(Context context){
       String fileDirContext="";
       String fileDir="";
       if (context!=null){
           fileDir=context.getFilesDir().getPath();
       }
       if (fileDir!=null&&(fileDir.contains("/data/data/")||fileDir.contains("/data/user/0/"))){
           fileDirContext =  fileDir.replaceAll("/data/data/","");
           fileDirContext =  fileDirContext.replaceAll("/data/user/0/","");
           fileDirContext =  fileDirContext.replaceAll((context!=null?context.getPackageName():"")+"/files","");
       } else {//不符合正常的路径就直接返回全路径
           fileDirContext = fileDir;
       }
        return fileDirContext;
   }
}
