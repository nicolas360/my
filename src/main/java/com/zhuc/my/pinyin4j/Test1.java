package com.zhuc.my.pinyin4j;

import java.util.Arrays;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang3.StringUtils;

public class Test1 {

	/**
	 * @param args
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
		String[] s = PinyinHelper.toHanyuPinyinStringArray('曾');
		System.out.println(Arrays.toString(s));

		s = PinyinHelper.toHanyuPinyinStringArray('绿', getDefaultOutputFormat());
		for (String string : s) {
			System.out.println(StringUtils.capitalize(string));
		}

		System.out.println(getPinYin("迪斯蒂法诺"));

	}

	public static String getPinYin(String str) throws BadHanyuPinyinOutputFormatCombination {
		StringBuilder sb = new StringBuilder();
		HanyuPinyinOutputFormat format = getDefaultOutputFormat();
		for (char c : str.toCharArray()) {
			String[] s = PinyinHelper.toHanyuPinyinStringArray(c, format);
			// 首字母大写
			sb.append(StringUtils.capitalize(s[0]));
		}

		return sb.toString();
	}

	public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		return format;
	}
}
