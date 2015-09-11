package com.myml.model;

public class Category {
	private String category;
	private double probability;
	public Category(String category,double probability) {
		this.category=category;
		this.probability=probability;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
}
