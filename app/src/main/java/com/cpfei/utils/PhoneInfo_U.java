/*
 * Copyright © 1999-2014 byecity, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cpfei.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

/******************************************
 * 类描述： 获取手机信息相关类 类名称：PhoneInfo_U
 * 
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2014-3-5 20:41
 ******************************************/
public class PhoneInfo_U {

	/** 屏幕高度 */
	private static int screenHeight;
	/** 屏幕宽度 */
	private static int screenWidth;

	private PhoneInfo_U() {
	}

	/**
	 * 获取手机MAC地址
	 * 
	 * @param mContext
	 * @return 本机MAC地址
	 */
	public static String getMac(Context mContext) {
		WifiManager wifiMgr = (WifiManager) mContext
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
		if (null != info) {
			return info.getMacAddress();
		}
		return "F0:25:B7:47:A7:D9";
	}

	/**
	 * 获取手机IMEI号
	 * 
	 * @param mContext
	 * @return 本机DeviceId
	 */
	public static String getIMEI(Context mContext) {
		TelephonyManager tm = (TelephonyManager) mContext
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (null != tm) {
			return tm.getDeviceId();
		}
		return "357523058648023";
	}

	/**
	 * 获取手机唯一识别码
	 * 
	 * @param mContext
	 * @return
	 */
	@TargetApi(3)
	public static String getUDID(Context mContext) {
		String m_androidId = Secure.getString(mContext.getContentResolver(),
				Secure.ANDROID_ID);
		return m_androidId;
	}

	/**
	 * 获取手机应用android系统版本
	 * 
	 * @return 2.3、4.1等
	 */
	public static String getOSBuildVersion() {
		return Build.VERSION.RELEASE;
	}

	public static int getScreenHeight(Activity activity) {
		if (screenHeight == 0 && activity != null) {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay()
					.getMetrics(displayMetrics);
			screenHeight = displayMetrics.heightPixels;
		}
		return screenHeight;
	}

	public static void setScreenHeight(int screenHeight) {
		PhoneInfo_U.screenHeight = screenHeight;
	}

	public static int getScreenWidth(Activity activity) {
		if (screenWidth == 0 && activity != null) {
			DisplayMetrics displayMetrics = new DisplayMetrics();
			activity.getWindowManager().getDefaultDisplay()
					.getMetrics(displayMetrics);
			screenWidth = displayMetrics.widthPixels;
		}
		return screenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		PhoneInfo_U.screenWidth = screenWidth;
	}

	/**
	 * 获取手机状态栏高度
	 *
	 * @param context
	 * @return int 当前手机状态栏高度
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	// 获取ActionBar的高度
	public static int getActionBarHeight(Context context) {
		TypedValue tv = new TypedValue();
		int actionBarHeight = 0;
		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,
				tv, true))// 如果资源是存在的、有效的
		{
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					context.getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}

	/**
	 * 获取手机生产商
	 *
	 * @return samsung
	 */
	@TargetApi(4)
	public static String getMerchan() {
		return Build.MANUFACTURER;
	}

	/**
	 * 获取手机品牌
	 *
	 * @return samsung
	 */
	public static String getBrand() {
		return Build.BRAND;
	}

	/**
	 * 获取手机型号
	 *
	 * @return N9008
	 */
	public static String getModel() {
		return Build.MODEL;
	}

	/**
	 * 生成随机UUID
	 *
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String getOnlyIdForIp(Context mContext) {
		String imei = PhoneInfo_U.getIMEI(mContext);
		String udid = PhoneInfo_U.getUDID(mContext);
		if (!TextUtils.isEmpty(imei)) {
			return imei;
		} else if (!TextUtils.isEmpty(udid)) {
			return udid;
		} else {
			return PhoneInfo_U.getUUID();
		}
	}

	@SuppressLint("NewApi")
	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			TelephonyManager tm = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = Secure.getString(
						context.getContentResolver(),
						Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getScreenHeightAll(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			return dm.heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取App versonName
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersonName(Context context) {
		try {
			String pkName = context.getPackageName();
			String versionName = context.getPackageManager().getPackageInfo(
					pkName, 0).versionName;
			return "v" + versionName;
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * 获取App versonName
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersonNameNoV(Context context) {
		try {
			String pkName = context.getPackageName();
			String versionName = context.getPackageManager().getPackageInfo(
					pkName, 0).versionName;
			return versionName;
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 获取App versonCode
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersonCode(Context context) {
		try {
			String pkName = context.getPackageName();
			int versionCode = context.getPackageManager().getPackageInfo(
					pkName, 0).versionCode;
			return versionCode;
		} catch (Exception e) {
		}
		return -1;
	}
}
