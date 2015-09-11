package com.myml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import sun.misc.Sort;

public class SplitText {
	public SplitText() {

	}

	// 参数1代表的是训练的文件数，参数2代表的是选择训练的文件类别
	public String[] Split(double flength, String sort) {
		String[] transtr = { "" };
		try {
			File[] file = new File[(int) flength];
			FileInputStream fis;
			String restr = "";
			for (int i = 0; i < file.length; i++) {
				if (sort.trim().equalsIgnoreCase("ham"))
					file[i] = new File("E:\\ham\\" + (i + 1) + ".txt");
				else if (sort.trim().equalsIgnoreCase("spam"))
					file[i] = new File("E:\\spam\\" + (i + 1) + ".txt");

				// 开流读文件
				fis = new FileInputStream(file[i]);
				byte[] buff = new byte[1024];
				int hasread = 0;

				// 每次读取1024字节的数据，存入restr中
				while ((hasread = fis.read(buff)) > 0) {
					restr += new String(buff, 0, hasread);
				}
				restr += "\n";
			}
			// 替换特殊字符 , 、 ？等（换成空），第一个参数是正则表达式 |-注意放在最后
			restr = restr
					.replaceAll("[,|?|&|$|;|.|!|:|(|)|/|\r\n|\"|=|-]", " ");
			// 这三个特殊考虑
			restr = restr.replaceAll("[%]", " % ");
			restr = restr.replaceAll("[+]", " + ");
			restr = restr.replaceAll("[*]", " * ");
			restr = restr.replaceAll("\\s+", " ");
			// 划分单词得到单词数组
			transtr = restr.split(" ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transtr;
	}

	public String[] Split(String filename) {
		String[] transtr = { "" };
		try {
			File file = new File("E:\\test\\"+filename);
			FileInputStream fis;
			String restr = "";
			// 开流读文件
			fis = new FileInputStream(file);
			byte[] buff = new byte[1024];
			int hasread = 0;

			// 每次读取1024字节的数据，存入restr中
			while ((hasread = fis.read(buff)) > 0) {
				restr += new String(buff, 0, hasread);
			}
			restr += "\n";

			// 替换特殊字符 , 、 ？等（换成空），第一个参数是正则表达式 |-注意放在最后
			restr = restr
					.replaceAll("[,|?|&|$|;|.|!|:|(|)|/|\r\n|\"|=|-]", " ");
			// 这三个特殊考虑
			restr = restr.replaceAll("[%]", " % ");
			restr = restr.replaceAll("[+]", " + ");
			restr = restr.replaceAll("[*]", " * ");
			restr = restr.replaceAll("\\s+", " ");
			// 划分单词得到单词数组
			transtr = restr.split(" ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transtr;
	}
	
	/*public static void main(String[] args) {
		String[] transtr=new SplitText().Split("test.txt");
		Arrays.sort(transtr);
		for (String string : transtr) {
			System.out.println(string);
		}
	}*/
}

