package com.myml.model;

public class Word {
	private String word;
	private double count;
	private double probability;
	
	public Word(String word,double count,double probability) {
		// TODO Auto-generated constructor stub
		this.word=word;
		this.count=count;
		this.probability=probability;
	}
	
	public double getProbality() {
		return probability;
	}
	public void setProbality(double probability) {
		this.probability = probability;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public double getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
