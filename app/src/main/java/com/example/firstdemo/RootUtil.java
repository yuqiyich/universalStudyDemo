package com.example.firstdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class RootUtil {
    private final static String TAG = "RootUtil";

    private final static StringBuilder sb = new StringBuilder();

    /**
     * 设备是否ROOT
     *
     * @param context
     * @param rootManagerPackageNames
     * @return
     */
    public static boolean isDeviceRooted(Context context, List<String> rootManagerPackageNames) {
        boolean isRooted = false;
        // 担心判断逻辑的代码有异常影响到应用使用。
        try {
            // 希望能统计出来所有的检测方法的结果。
            if (checkRootMethod1()) {
                isRooted = true;
            }
            if (checkRootMethod2()) {
                isRooted = true;
            }
            if (checkRootMethod3()) {
                isRooted = true;
            }
            if (isSuFileExists()) {
                isRooted = true;
            }
            if (checkRootManagerPackageExists(context, rootManagerPackageNames)) {
                isRooted = true;
            }
        } catch (Exception e) {
            sb.append(".isDeviceRooted Exception:" + e.getMessage());
        }
        return isRooted;
    }

    /**
     * 给外部提供一个查看检查信息的方法，返回检测到的异常信息
     *
     * @return
     */
    public static String getCheckResultInfo() {
        return sb.toString();
    }

    private static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            sb.append(".TAGS.contains(\"test-keys\")");
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkRootMethod2() {
        String[] paths = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        boolean isHadSU = false;
        for (String path : paths) {
            if (new File(path).exists()) {
                sb.append(path+".path.exists()");
                isHadSU = true;
            }
        }
        return isHadSU;
    }

    private static boolean checkRootMethod3() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) {
                sb.append("./system/xbin/which.su");
                return true;
            }
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }

    /**
     * 通过ls命令的方式检测su文件是否存在
     *
     * @return 返回是否存在
     */
    private static boolean isSuFileExists() {
        String pathEnv = System.getenv("PATH");
        if (TextUtils.isEmpty(pathEnv)) {
            return false;
        }

        String[] dirPaths = pathEnv.split(":");

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
            if (br.readLine() != null) {
                sb.append(".exec cmdSb:" + cmdSb.toString());
                return true;
            }

            String tmp;
            br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((tmp = br.readLine()) != null) {
                tmp = tmp.toLowerCase(Locale.getDefault());
                if (tmp.contains("permission denied")) {
                    // su文件存在，只不过没有权限读取而已，使用File.exists()方式是无法判断的
                    sb.append(".permission denied");
                    return true;
                }
            }
        } catch (Exception e) {
            sb.append(".check su file exists by ls command error:" + e.getMessage());
        } finally {
            if (null != process) process.destroy();
        }
        return false;
    }

    /**
     * 判断是否存在对应的root权限管理app
     *
     * @param context  上下文
     * @param packages root权限管理app的包名
     * @return 是否存在
     */
    private static boolean checkRootManagerPackageExists(Context context, List<String> packages) {
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);
        boolean isHadPackage = false;
        for (PackageInfo info : packageInfos) {
            if (packages.indexOf(info.packageName) >= 0) {
                sb.append(".exists package:" + info.packageName);
                isHadPackage = true;
            }
        }
        return isHadPackage;
    }
}