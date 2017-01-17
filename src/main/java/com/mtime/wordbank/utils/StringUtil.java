package com.mtime.wordbank.utils;

import org.apache.commons.lang3.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串工具类
 * @author Hevin
 *
 */
public class StringUtil {
	final static Set<Character.UnicodeBlock> japaneseUnicodeBlocks = new HashSet<Character.UnicodeBlock>() {
		{
			add(Character.UnicodeBlock.HIRAGANA);
			add(Character.UnicodeBlock.KATAKANA);
			add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
		}
	};
	/**
	 * 标准化字符串
	 * 
	 * @param string
	 *            字符串
	 * @return 标准的字符串
	 */
	public static String getStandardString(String string) {
		if (isNullOrEmpty(string)) {
			return "";
		}

		return qBchange(string.toLowerCase().trim());
	}

	/**
	 * 返回Pascal化的字符串，即将字符串的首字母大写
	 * 
	 * @param string
	 *            字符串
	 * @return 首字母大写的字符串
	 */
	public static String getPascalString(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	/**
	 * 判断字符是否为中文字符
	 * 
	 * @param ch
	 *            字符
	 * @return true则是中文字符，false不是中文字符
	 */
	public static boolean isChineseCharacter(char ch) {
		String string = String.valueOf(ch);
		return Pattern.compile("[\u4e00-\u9fa5]").matcher(string).find();
	}

	/**
	 * 把pascal化的字符的首字母变小写
	 * 
	 * @param string
	 *            字符串
	 * @return 去pascal化的字符串
	 */
	public static String getStringFromPascal(String string) {
		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}

	/**
	 * 把字符串列用空格连接成一个字符串
	 * 
	 * @param strings
	 *            字符串列
	 * @return 字符串
	 */
	public static String getStringFromStrings(String[] strings) {
		StringBuffer buf = new StringBuffer();
		for (String string : strings) {
			buf.append(string);
			buf.append(" ");
		}

		return buf.toString().trim();
	}

	/**
	 * 把字符串列用指定的方式连接成一个字符串
	 * 
	 * @param strings
	 *            字符串列
	 * @return 字符串
	 */
	public static String getStringFromStrings(String[] strings, String spliter) {
		StringBuffer buf = new StringBuffer();
		for (String string : strings) {
			buf.append(string);
			buf.append(spliter);
		}

		return buf.toString().trim();
	}

	public static String[] getStringsFromString(String stirng, String spliter) {
		return stirng.split(spliter);
	}

	public static boolean isNullOrEmpty(String string) {
		return !(string != null && string.trim().length() != 0);
	}

	/**
	 * 去掉字符串中的空格和tab
	 * 
	 * @param string
	 *            字符串
	 * @return 去掉后的值
	 */
	public static String removeWhiteSpace(String string) {
		if (isNullOrEmpty(string)) {
			return "";
		} else {
			string = string.replace(" ", "");
			string = string.replace("\t", "");

			return string;
		}
	}

	/**
	 * 判断是否为英文或者数字字符串
	 * 
	 * @param string
	 *            字符串
	 * @return true则是，false则否
	 */
	public static boolean isCharOrNumberString(String string) {
		char[] cs = string.toCharArray();
		for (char c : cs) {
			if (!Character.isDigit(c) && !isEnglishCharacter(c)) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 返回英文或者数字字符串
	 * 
	 * @param string
	 *            字符串
	 * @return true则是，false则否
	 */
	public static String hasCharOrNumberString(String string) {
		String ret = "";
		char[] cs = string.toCharArray();
		for (char c : cs) {
			if (Character.isDigit(c) || isEnglishCharacter(c)) {
				ret = ret + c;
			}
		}

		return ret;
	}

	/**
	 * 判断是否日文字符串
	 * @param text
	 * @return
	 */
	public static boolean isChineseOrJapaneseChar(String text){
		boolean flag =  false;
		for (char c : text.toCharArray()) {
			if (japaneseUnicodeBlocks.contains(Character.UnicodeBlock.of(c))) {
				return true;
			}
		}
		return flag;
	}
	

	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为英文字符
	 * 
	 * @param ch
	 *            字符
	 * @return true为英文字符，false则不是
	 */
	public static boolean isEnglishCharacter(char ch) {
		String a = String.valueOf(ch).toLowerCase();
		return a.charAt(0) >= 'a' && a.charAt(0) <= 'z';
	}

	public static boolean isEnglishOrNumberCharacter(char ch) {
		return isEnglishCharacter(ch) || Character.isDigit(ch);
	}

	/**
	 * 返回所有的rex在souce串中独立出现的位置 此处独立的意思为英文和数字的两边不能为英文和数字
	 * 
	 * @param source
	 *            源字符串
	 * @param rex
	 *            表达式
	 * @return 所有的位置信息
	 */
	public static int[] getAllInDependentIndex(String source, String rex) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (isCharOrNumberString(rex)) {
			int position = 0;
			while (position < source.length()) {
				int index = source.indexOf(rex, position);
				if (index > -1) {
					if (index > 0 && index + rex.length() < source.length()) {
						if (!isEnglishOrNumberCharacter(source
								.charAt(index - 1))
								&& !isEnglishOrNumberCharacter(source
										.charAt(index + rex.length()))) {
							list.add(source.indexOf(rex, position));
						}
					} else if (index > 0) {
						if (!isEnglishOrNumberCharacter(source
								.charAt(index - 1))) {
							list.add(source.indexOf(rex, position));
						}
					} else if (index + rex.length() < source.length()) {
						// if (!isEnglishOrNumberCharacter(source.charAt(index
						// + rex.length()))) {
						list.add(source.indexOf(rex, position));
						// }
					} else if (index + rex.length() == source.length()) {
						list.add(source.indexOf(rex, position));
					}
					position = index + 1;
				} else {
					break;
				}
			}
		} else {
			return getAllIndex(source, rex);
		}

		int[] ins = new int[list.size()];
		for (int i = 0; i < ins.length; i++) {
			ins[i] = list.get(i);
		}

		return ins;
	}

	/**
	 * 返回所有的rex在souce串中出现的位置
	 * 
	 * @param source
	 *            源字符串
	 * @param rex
	 *            表达式
	 * @return 所有的位置信息
	 */
	public static int[] getAllIndex(String source, String rex) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int position = 0;
		while (position < source.length()) {
			int index = source.indexOf(rex, position);
			if (index > -1) {
				list.add(source.indexOf(rex, position));
				position = index + 1;
			} else {
				break;
			}
		}

		int[] ins = new int[list.size()];
		for (int i = 0; i < ins.length; i++) {
			ins[i] = list.get(i);
		}

		return ins;
	}

	/**
	 * 倒转字符串，输入abc，返回cba
	 * 
	 * @param string
	 *            字符串
	 * @return 倒转后的值
	 */
	public static String reverseString(String string) {
		if (isNullOrEmpty(string)) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer();
			for (int i = 1; i <= string.length(); i++) {
				sb.append(string.charAt(string.length() - i));
			}

			return sb.toString();
		}
	}

	public static String getNotNullValue(String string) {
		if (string == null || "null".equals(string)) {
			string = "";
		}

		return StringUtil.getStandardString(string);
	}

	/**
	 * 全角转半角
	 * 
	 * @param QJstr
	 *            全角字符
	 * @return
	 */
	public static String qBchange(String QJstr) {
		if(isNullOrEmpty(QJstr)){
			return "";
		}
		
		char[] c = QJstr.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}


	/**
	 * 去除字符串中的特殊字符
	 * @param str 原始字符串
	 * @return 去除特殊字符后的字符串
	 */
	public static String removeSpecialChars(String str){
		if (str == null || "".equals(str)) {
			return "";
		}
		// 清除掉所有特殊字符  
		String regEx = "[`~!@#$%^&*()_\\+\\=\\-|{}':;',\\[\\].·<>/?~！@#￥%……&*《》\\\\（）\\——+|{}【】•‘；\"\"：”“’。，、？“\"”]";
		Matcher m = null;
		try {
			Pattern p = Pattern.compile(regEx);
			m = p.matcher(str);
		} catch (PatternSyntaxException p) {
			p.printStackTrace();
		}
		return m.replaceAll("").trim();
	}
    public static String removeRecoverline(String str){
        if (str == null || "".equals(str)) {
            return "";
        }
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*\\+\\=\\-|{}';',<>/?~！\\@#￥%……&*\\\\——+|{}【】‘；”“’。，\"\"、？“\"”]";
        Matcher m = null;
        try {
            Pattern p = Pattern.compile(regEx);
            m = p.matcher(str);
        } catch (PatternSyntaxException p) {
            p.printStackTrace();
        }
        return m.replaceAll("").trim();
    }

	/**
	 * 处理String 过滤英文和空格，~~替换为~
	 * @param str
	 * @return
	 */
	public static String parseString(String str){
		if(org.apache.commons.lang3.StringUtils.isBlank(str)){
			return "";
		}
		return str.replaceAll("[A-Za-z]+","").replaceAll("[àâèéêîïôöùûüçœæ€ÄäÅåÍðÓÓÓðÓúØØØåÜäÞÞÞúßßäßååðÓðÞðúúÞúúúýþÞýýÀÂÈÉÊËÎÏÔÖÙÛÇŒÆóáíòãñÁøë]+","").replaceAll("\\s","")
				.replaceAll("~~","~").replaceAll("[\\.\\-'\"]","");
	}
}
