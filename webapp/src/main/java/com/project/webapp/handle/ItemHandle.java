package com.project.webapp.handle;

import java.util.List;

import com.project.webapp.item.Item;

public interface ItemHandle {

	/**
	 * fetches the items
	 * @return
	 */
	List<Item> getItems();
	
	/**
	 * fetches a single item based on itemId
	 * @param id
	 * @return
	 */
	Item getItemById(int id);

}



