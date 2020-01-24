package com.project.webapp.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.project.webapp.item.Item;

@Component
public class ItemHandleImpl implements ItemHandle {

	List<Item> items;

	@PostConstruct
	public void loadData() {
		Item book = new Item(1, "book", 30.00, 0.5);
		Item pen = new Item(2, "pen", 5.00, 0.1);
		Item pencil = new Item(3, "pencil", 10.00, 0.2);
		Item notebook = new Item(4, "notebook", 25.00, 0.4);
		
		items = new ArrayList<Item>();
		
		items.add(book);
		items.add(pen);
		items.add(pencil);
		items.add(notebook);
	}
	
	@Override
	public List<Item> getItems() {
		return items;
	}
	
	@Override
	public Item getItemById(int id) {
		Optional<Item> item = items.stream().filter(i -> i.getItemId() == id).findFirst();
		return item.get();
	}
	
}





