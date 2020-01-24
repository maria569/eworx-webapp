package com.project.webapp.handle;

import java.util.Set;

import javax.servlet.http.HttpSession;

import com.project.webapp.item.Item;

public interface BasketHandle {

	/**
	 * adds an item to the basket
	 * @param item
	 */
	void addToBasket(Item item, HttpSession session);
	
	/**
	 * retrieves basket size
	 * @return
	 */
	Integer getBasketSize(Set<Item> basketSet);
	
	/**
	 * calculates the total price of items
	 * @return
	 */
	Double getTotalPrice(Set<Item> basketSet);
	
	/**
	 * deletes item from the basket
	 * @param id
	 * @return 
	 */
	void deleteItem(int id, HttpSession session);

	/**
	 * calculates the shipping costs
	 * @return
	 */
	double getShippingCosts(Set<Item> basketSet);
}
