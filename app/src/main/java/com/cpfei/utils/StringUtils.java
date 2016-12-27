package com.cpfei.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
public class StringUtils {
	private static final String tag = StringUtils.class.getName();

	/**
	 * 判断字符串是不是11位数字
	 * @param num
	 * @return
     */
	public static boolean isLegalNum(String num) {
		boolean numeric = isNumeric(num);
		if (numeric) {
			if (num.length() == 11){
				return true;
			}
		}
		return false;
	}

	public static boolean isLegalPhoneNum(String num) {
		String template = "^1[3,5,7,8,9]\\d{9}$";
		Pattern pattern = Pattern.compile(template);
		Matcher matcher = pattern.matcher(num);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isLegalEMailAddr(String addr) {
		String template = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(template);
		Matcher matcher = pattern.matcher(addr);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isLegalAlias(String alias) {
		String template = "^[\\W\\w]{1,12}$";
		Pattern pattern = Pattern.compile(template);
		Matcher matcher = pattern.matcher(alias);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}


	/**
	 * 保留小数
	 * @param db
	 * @param decimalDigits 位数
     * @return
     */
	public static double scaleDouble(double db, int decimalDigits) {
		BigDecimal bd = new BigDecimal(db);
		db = bd.setScale(decimalDigits, BigDecimal.ROUND_HALF_UP).doubleValue();
		return db;
	}

	/**
	 * 流转成string
	 * @param inputStream
	 * @param charSet
	 * @return
	 * @throws IOException
     */
	public static String convertStreamToString(InputStream inputStream, String charSet) throws IOException {
		if (inputStream != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charSet));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				inputStream.close();
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 将所有的数字、字母及标点全部转为全角字符，使它们与汉字同占两个字节
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375) {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 替换、过滤特殊字符
	 * 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	private static String strFilter(String str) throws PatternSyntaxException {
		str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("!", "！").replaceAll(" ", "").replace(",", "，")
				.replace(".", "。");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 将字符串中的数字挑出来
	 * 
	 * @param str
	 * @return 当 字符串中没有 数字的时候，将返回-1
	 */
	public static int StringGetInt(String str) {
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m != null) {
			try {
				return Integer.parseInt(m.replaceAll("").trim());
			}catch (Exception e){
				return -1;
			}
		}
		return -1;

	}

	/**
	 * 将 字符串截取出来，按照指点的长度
	 * 
	 * @param longString abdcdefghi
	 * @param limitLength abdcd...
	 * @return
	 */
	public static String cutLongString(String longString, int limitLength) {
		String tempStr;
		StringBuilder strBuilder;
		if (longString.length() > limitLength) {
			tempStr = longString.substring(0, limitLength);
			strBuilder = new StringBuilder(tempStr);
			strBuilder.append("...");
		} else {
			tempStr = longString;
			strBuilder = new StringBuilder(tempStr);
		}
		return strBuilder.toString();
	}

	public static String double2String(double x) {
		return String.format("%.2f", x);
	}


	/**
	 * 去掉小数点后的位数
	 * @param value
	 * @return
     */
	public static String delDot(double value) {

		String strValue = String.valueOf(value);
		if (strValue.contains(".")) {
			strValue = strValue.substring(0, strValue.indexOf("."));
		}
		return strValue;
	}

	/**
	 * 将字符串从右边起三位加一个“，”号字符
	 * 
	 * @param str
	 *            123456456
	 * @return 13,456,456
	 */
	public static String getStringCommaSeparator(int str) {
		return getStringCommaSeparator(str + "");
	}

	/**
	 * 将字符串从右边起三位加一个“，”号字符
	 * 
	 * @param str
	 *            123456456
	 * @return 13,456,456
	 */
	public static String getStringCommaSeparator(String str) {
		if (TextUtils.isEmpty(str)) {
			return "";
		}

		int length = str.length();
		if (length <= 3) {
			return str;
		}

		StringBuilder sb = new StringBuilder(str);
		int i = length - 3;
		while (i > 0) {
			sb.insert(i, ",");
			i -= 3;
		}
		return sb.toString();
	}

	public static String getTimeByMillis(long millis) {
		int hours = (int)TimeUtils.getHour(millis);
		if (hours >= 2 && hours <= 9) {
			return "早晨";
		} else if (hours > 9 && hours <= 12) {
			return "上午";
		} else if (hours > 12 && hours <= 14) {
			return "中午";
		} else if (hours > 14 && hours < 18) {
			return "下午";
		} else {
			return "晚上";
		}
	}

	public static String getStringInsertN(String str) {
		if (str.length() <= 4) {
			return str;
		}

		StringBuilder stringBuffer = new StringBuilder(str);

		switch (str.length()) {
		case 5:
		case 6:
			stringBuffer.insert(2, "\n");
			break;
		case 7:
			stringBuffer.insert(3, "\n");
			break;
		case 8:
			stringBuffer.insert(4, "\n");
			break;

		default:
			break;
		}

		return stringBuffer.toString();
	}

	public static double getOneDecimal(double decimal) {

		if (decimal > 0) {
			decimal = (double) ((int) (decimal * 100) / 10.0);
		}
		return decimal;
	}


	/**
	 * 判断字符串是否是数字类型
	 * @param str
	 * @return
     */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}


	/**
	 * @desctiption 将 阿拉伯数字转换为汉字
	 * @param position
	 * @return
     */
	public static String castNumber2Letter(int position) {
		String source = String.valueOf(position);
		String result = "";
		for (int i = 0; i< source.length();i++) {
			String c = String.valueOf(source.charAt(i));
			if ("1".equals(c)) {
				result +=  "一";
			} else if ("2".equals(c)) {
				result += "二";
			} else if ("4".equals(c)) {
				result += "三";
			} else if ("4".equals(c)) {
				result += "四";
			} else if ("5".equals(c)) {
				result += "五";
			} else if ("6".equals(c)) {
				result += "六";
			} else if ("7".equals(c)) {
				result += "七";
			} else if ("8".equals(c)) {
				result += "八";
			} else if ("9".equals(c)) {
				result += "九";
			} else if ("0".equals(c)) {
				result += "零";
			}
		}
		return result;
	}


}
