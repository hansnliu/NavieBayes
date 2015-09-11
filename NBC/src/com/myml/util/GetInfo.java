package com.myml.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.myml.model.Word;

public class GetInfo {
	public GetInfo() {

	}

	public List<Word> GetWordInfo(String[] transtr) {
		// 排序
		long date1=System.currentTimeMillis();
		System.out.println(date1);
		Arrays.sort(transtr);
		long date2=System.currentTimeMillis();
		System.out.println(date2);
		//System.out.println("HHHHHHH"+(date2-date1));

		List<Word> words = new ArrayList<Word>();
		// 遍历每个单词，统计相同单词个数，将单词、它出现次数和概率存入对象word
		String temp = transtr[0];
		double count = 0;
		double sum = transtr.length;
		DecimalFormat df = new DecimalFormat("0.000000"); // 概率控制到小数点后六位
		for (int i = 0; i < transtr.length; i++) {
			//相同训练单词
			if (temp.trim().equalsIgnoreCase(transtr[i].trim()))
				count++;
			else {
				//单词不同把当前单词的信息存入一个word，继续下一个训练单词
				Word word = new Word(temp, count, Double.parseDouble(df.format(count / sum)));
				words.add(word);
				temp = transtr[i];
				count = 1; // 比较时自身就算一次
			}
		}
		
		return words;
	}
}
