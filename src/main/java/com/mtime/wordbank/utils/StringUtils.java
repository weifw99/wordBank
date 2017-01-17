package com.mtime.wordbank.utils;

import java.util.LinkedList;
import java.util.List;

public class StringUtils {
	
	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if(str == null) {
			return false;
		}
		//return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		return str.matches("[0-9]+.?[0-9]?");
	}
	
	/**
	 * 将字符串数组转换成字符串，通过给定分隔符
	 * @param array
	 * @param delimiter  分隔符
	 * @return
	 */
	public static String convertArrayToString(String[] array, String delimiter) {
		String s = null;
		if(null != array && array.length > 0 && null != delimiter && !"".equals(delimiter)) {
			s = "";
			int i = 0;
			for(String str : array) {
				if(i > 0) 
					s = s.concat(delimiter).concat(str);
				s = s.concat(str);
				i++;
			}
		}
		return s;
	}
	
	/**
	 * 将字符串列表转换成字符串，通过给定分隔符
	 * @param array
	 * @param delimiter  分隔符
	 * @return
	 */
	public static String convertListToString(List<String> list, String delimiter) {
		String s = null;
		if(null != list && list.size() > 0 && null != delimiter && !"".equals(delimiter)) {
			s = "";
			int i = 0;
			for(String str : list) {
				if(i > 0) 
					s = s.concat(delimiter).concat(str);
				s = s.concat(str);
				i++;
			}
		}
		return s;
	}
	
	/**
	 * 判断字符串是否是正整数
	 * @param str
	 * @return
	 */
	public static boolean isPositiveInteger(String str) {
		if(str == null) {
			return false;
		}
		return str.matches("^\\d+$");
	}
	
	/**
	 * 判断字符串是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEqual(String str1, String str2) {
		if(str1 == null && str2 == null) 
			return true;
		if(null != str1 && null != str2) 
			return str1.equals(str2);
		return false;
	}
	
	//public static String[] minus(String str1, String str2) {}
	
	/**
	 * 求数组的差集
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String[] minus(String[] str1, String[] str2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
        String[] longerArr = str1;
        String[] shorterArr = str2;
        //找出较长的数组来减较短的数组  
        if (str1.length > str2.length) {
            longerArr = str2;
            shorterArr = str1;
        }
        for (String str : longerArr) {
            if (!list.contains(str)) { 
                list.add(str);
            }
        }
        for (String str : shorterArr) {
            if (list.contains(str)) {
                history.add(str);
                list.remove(str);
            } else {
                if (!history.contains(str)) {
                    list.add(str);
                }
            }
        }
        longerArr = null;
        shorterArr = null;
        history = null;
        String[] result = {};
        return list.toArray(result);
	}
	
	/**
	 * <p>Checks if a String is empty ("") or null. </p>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * <p>Checks if a String is not empty ("") or null. </p>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}
	
	/**
	 * <p>Checks if a String is whitespace, empty ("") or null. </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(" ")   = true
	 * StringUtils.isBlank("\t\n\f\r")   = true
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if(str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for(int i=0; i < strLen; i++) {
			if((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <p>Checks if a String is not whitespace, empty ("") or null. </p>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}


}
