package com.myml.util;

import java.util.List;

import com.myml.model.Word;

public class Main {
	
	public Object[][] excute(double hamnum,double spamnum,String tfn){
		// 测试数据
		String[] teststr = new SplitText().Split(tfn);
		
		// 训练数据
		String[] hamwords = new SplitText().Split(hamnum, "ham");
		String[] spamwords = new SplitText().Split(spamnum, "spam");
		List<Word> hamwordslist = new GetInfo().GetWordInfo(hamwords);
		List<Word> spamwordslist = new GetInfo().GetWordInfo(spamwords);
		
		double[] hampx = new MatchWord().GetXProbability(teststr, hamwordslist);
		double[] spampx = new MatchWord().GetXProbability(teststr, spamwordslist);
		
		Object[][] wordpx=new Object[teststr.length][3];
		for (int i = 0; i < wordpx.length; i++) {
			wordpx[i][0]=teststr[i];
			wordpx[i][1]=hampx[i];
			wordpx[i][2]=spampx[i];
		}
		
		return wordpx;
	}
}
