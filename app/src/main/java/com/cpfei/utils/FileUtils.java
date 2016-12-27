package com.cpfei.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.PatternSyntaxException;

public class FileUtils {
	public static final String RES_STRING_PREFIX = "drawable://";
	public static final String HTTP_PREFIX = "http://";
	public static final String HTTPS_PREFIX = "https://";
	public static final String FILE_PREFIX = "file://";
	private static final String TAG = FileUtils.class.getName();

	public static String getFileName(String path) {
		try {
			String[] terms = path.split("/");
			return terms[terms.length - 1];
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void mkDirs(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			try {
				// 按照指定的路径创建文件夹
				file.mkdirs();
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	}

	public static int copyFolder(String srcFolder, String dstFolder) {
		File[] currentFiles;
		File root = new File(srcFolder);
		if (!root.exists()) {
			return -1;
		}
		currentFiles = root.listFiles();
		File targetDir = new File(dstFolder);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		for (File file :currentFiles) {
			if (file.isDirectory()) {
				copyFolder(file.getPath() + "/", dstFolder
						+ file.getName() + "/");
			} else {
				copyFile(file.getPath(),
						dstFolder + file.getName());
			}
		}
		return 0;
	}

	public static boolean copyFile(String srcFile, String dstFile) {
		try {
			InputStream fosFrom = new FileInputStream(srcFile);
			OutputStream fosTo = new FileOutputStream(dstFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosFrom.read(bt)) > 0) {
				fosTo.write(bt, 0, c);
			}
			fosFrom.close();
			fosTo.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean copyFile(InputStream fosFrom, String dstFile) {
		try {
			OutputStream fosTo = new FileOutputStream(dstFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosFrom.read(bt)) > 0) {
				fosTo.write(bt, 0, c);
			}
			fosFrom.close();
			fosTo.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static String getFullUrl(String url) {
		if (TextUtils.isEmpty(url)) {
			return "";
		}

		if (url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX)){
			return url;
		} else {
			String sep = System.getProperty("file.separator");
			if (url.startsWith(sep)) {
				String string = HTTP_PREFIX + url;
				return string;
			} else {
				String mUrl = HTTP_PREFIX + sep + url;
				return mUrl;
			}
		}
	}



	public static String getFullUrlOld(String url) {
		if (TextUtils.isEmpty(url)) {
			return "";
		}
		
		if(url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX)){
			return url;
		} else {
			String sep = System.getProperty("file.separator");
			if (url.startsWith(sep)) {
				return "http:/" + url;
			} else {
				return HTTP_PREFIX + url;
			}
			
		}
	}

	public static String getFullResUri(String url) {
		if (isResIdString(url)) {
			return url;
		}
		return null;
	}

	public static String getFullResUri(int resId) {
		return RES_STRING_PREFIX + resId;
	}

	public static String getFileUrl(Uri uri, ContentResolver cr) {
		String res = null;
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = cr.query(uri, proj, null, null, null);
		if (cursor != null){
		if (cursor.moveToFirst()) {
			int column_index = cursor
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			res = cursor.getString(column_index);
		}
		}
		if (cursor != null) {
			cursor.close();
		}
		return res;
	}

	public static String convertResId2String(int resId) {
		return RES_STRING_PREFIX + String.valueOf(resId);
	}

	public static int convertString2ResId(String str) {
		if (isResIdString(str)) {
			try {
				String strInt = str.substring(RES_STRING_PREFIX.length(),
						str.length());
				return Integer.parseInt(strInt);
			} catch (Exception e) {
				return -1;
			}
		}
		return -1;
	}

	public static boolean isResIdString(String str) {
		return str != null && str.startsWith(RES_STRING_PREFIX);
	}

	public static boolean isFileExist(String filePath) {
		if (!TextUtils.isEmpty(filePath)) {
			File file = new File(filePath);
			if (file.exists()) {
				return true;
			}
		}
		return false;
	}


	public static void writeMsgToFile(String msg, String fileName) {
		msg += "\n";
		File file = new File(Environment.getExternalStorageDirectory(),
				fileName);
		FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(file, true);
			outStream.write(msg.getBytes());
			outStream.close();
			Log.e(TAG, "file save success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
