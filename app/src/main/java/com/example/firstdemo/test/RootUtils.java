package com.example.firstdemo.test;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class RootUtils {

    private final static String TAG = RootUtils.class.getSimpleName();

    /**
     * 判断设备是否已rooted
     * @param context 上下文
     * @param rootManagerPackageNames root权限管理app的包名列表
     * @return 是否已rooted
     */
    public static boolean isRooted(Context context, List<String> rootManagerPackageNames) {
        return isSuFileExists() || checkRootManagerPackageExists(context, rootManagerPackageNames);
    }

    /**
     * 通过ls命令的方式检测su文件是否存在
     * @return 返回是否存在
     */
    private static boolean isSuFileExists() {
        String pathEnv = System.getenv("PATH");
        if (TextUtils.isEmpty(pathEnv)) {
            return false;
        }

        String[] dirPaths = pathEnv.split(":");
       for (String enr:dirPaths){
           Log.i(TAG,"PATH--V--:"+enr);
       }
        StringBuilder cmdSb = new StringBuilder("ls ");
        for (String tmp : dirPaths) {
            File dir = new File(tmp);
            if (dir.exists() && dir.canRead()) {
                cmdSb.append(tmp).append("/su ");
            }
        }
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdSb.toString());
            process.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (br.readLine() != null) return true;
            String tmp;
            br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((tmp = br.readLine()) != null) {
                tmp = tmp.toLowerCase(Locale.getDefault());
                if (tmp.contains("permission denied")) {
                    // su文件存在，只不过没有权限读取而已，使用File.exists()方式是无法判断的
                    Log.i(TAG, "permission denied-----:"+tmp);
                    return true;
                }
            }
            Log.i(TAG, "read end-----");
        } catch (Exception e) {
            Log.i(TAG, "check su file exists by ls command error", e);
        } finally {
            if (null != process) process.destroy();
        }
        return false;
    }

    /**
     * 判断是否存在对应的root权限管理app
     * @param context 上下文
     * @param packages root权限管理app的包名
     * @return 是否存在
     */
    private static boolean checkRootManagerPackageExists(Context context, List<String> packages) {
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            if (packages.indexOf(info.packageName) >= 0) {
                return true;
            }
        }
        return false;
    }
}
