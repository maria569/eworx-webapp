package com.project.webapp.handle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.webapp.item.Item;

public class ItemHandleImplTest {
	
	ItemHandleImpl itemHandle;
	
	List<Item> list;
	
	@BeforeEach
	void setUp() {
		itemHandle = new ItemHandleImpl();
		Item item = new Item(1, "book", 5.00, 0.2);
		list = new ArrayList<Item>();
		list.add(item);
		itemHandle.items = list;
	}
	
	@Test
	void getItemsTest() {
		assertEquals(list, itemHandle.getItems());
	}
	
	@Test
	void getItemsByIdTest() {
		Item result = itemHandle.getItemById(1);
		assertEquals(1, result.getItemId());
	}
	
	
}






