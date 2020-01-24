package com.project.webapp.item;

public class Item {

	private int itemId;
	private String name;
	private int quantity;
	private double price;
	private double weightFactor;
	
	public Item() {
		
	}
	
	public Item(int itemId, String name, double price, double weightFactor) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.weightFactor = weightFactor;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeightFactor() {
		return weightFactor;
	}
	public void setWeightFactor(double weightFactor) {
		this.weightFactor = weightFactor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
