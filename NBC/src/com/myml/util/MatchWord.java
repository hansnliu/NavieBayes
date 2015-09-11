package com.myml.util;

import java.util.Arrays;
import java.util.List;

import com.myml.model.Word;

public class MatchWord {
	public MatchWord() {
		// TODO Auto-generated constructor stub
	}
	
	public double[] GetXProbability(String[] teststr, List<Word> trainstr) {
		// 让测试数据变成有序，以便查找
		Arrays.sort(teststr);

		// 测试数据的单词在训练数据的单词集中查找，得到概率
		int i = 0, j = 0;
		int temp; // temp用于记录j的变化，当测试的某单词在训练单词中从没出现，下一次训练单词从j开始
		double[] px = new double[teststr.length]; // 记录测试的单词的概率
		int neversee=0;
		// 测试单词集合训练单词集都是有序的
		while (i != teststr.length) {
			temp = j;
			// 如果测试的某单词比训练单词最后一个单词都大（不能匹配），那么后面一定都大（不能匹配），匹配结束，
			// 后面的概率进一步赋值
			if (teststr[i].compareToIgnoreCase(trainstr
					.get(trainstr.size() - 1).getWord()) > 0)
				break;
			// 当测试单词不等于当前训练单词时，继续向训练单词下一个匹配
			while (j < trainstr.size()
					&& !trainstr.get(j).getWord().equalsIgnoreCase(teststr[i]))
				j++;
			// 如果到训练单词最后一个都不能匹配，那么把这个测试单词概率赋值为0.0001，
			// j回到temp位置，i指向下一位
			if (j == trainstr.size()) {
				neversee++;
				px[i] = 0.0;
				j = temp;
				i++;
			} else {
				// 出现了匹配，就把当前训练单词概率赋给该测试单词概率
				px[i] = trainstr.get(j).getProbality();
				i++;
			}
		}

		// 当...后面的概率进一步赋值
		while (i != teststr.length) {
			//px[i] = 1.0/(teststr.length+trainstr.size());
			neversee++;
			px[i]=0.0;
			i++;
		}
		
		
		//进一步处理没出现单词概率
		double multipx=1.0;
		for (int k = 0; k < px.length; k++) {
			if(px[k]==0.0)
				continue;
			multipx*=px[k];
		}
		if(multipx==1.0)		//每个测试单词都没出现，这里在求拉普拉斯，还需进一步改进
			return px;
		
		String str=String.valueOf(multipx);
		String[] lenstr={""};
		String nepxstr = null;
		int flag=str.indexOf("E");
		//是科学计数法用E-分开
		if(flag!=-1){
			lenstr=str.split("E-");
			nepxstr="1E-"+lenstr[1];
		}
		//不是科学计数法用.分开
		else if(flag==-1){
			lenstr=str.split("[.]");
			for (int k = 0; k < lenstr.length; k++) {
				System.out.println(lenstr[k]);
			}
			nepxstr="1E-"+String.valueOf(lenstr.length-1);
		}
		
		//用1E-*除以没有出现的单词次数，把这个概率赋值给没出现的单词
		double nepx=Double.parseDouble(nepxstr);
		if(neversee!=0){
			for (int k = 0; k < px.length; k++) {
				if(px[k]==0.0)
					px[k]=nepx/neversee;
			}
		}
		return px;
	}
	
}
