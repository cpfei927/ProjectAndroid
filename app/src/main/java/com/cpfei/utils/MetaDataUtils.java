package com.cpfei.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by cpfei on 2016/5/24.
 * 获取manifest meta 节点
 */
public class MetaDataUtils {

    public static int getInt(Context context, String key) {
        int value = 0;
        ApplicationInfo appInfo = getApplicationInfo(context);
        if (appInfo != null) {
            value = appInfo.metaData
                    .getInt(key, 0);
        }
        return value;
    }

    public static String getString(Context context, String key) {
        String value = "";
        ApplicationInfo appInfo = getApplicationInfo(context);
        if (appInfo != null) {
            value = appInfo.metaData
                    .getString(key);
        }
        return value;
    }

    /**
     * @return
     */
    private static ApplicationInfo getApplicationInfo(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(),
                    PackageManager.GET_META_DATA);
            return appInfo;
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
        return null;
    }

}
