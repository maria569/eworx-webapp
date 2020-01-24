package com.project.webapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.webapp.handle.BasketHandle;
import com.project.webapp.handle.ItemHandle;
import com.project.webapp.item.Item;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

	@Mock
	ItemHandle itemHandle;
	
	@Mock
	BasketHandle basketHandle;
	
	@InjectMocks
	ItemController controller;
	
	MockMvc mockMvc;
	
	Set<Item> set = new HashSet<Item>();
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	void listItemsTest() throws Exception {
	   mockMvc.perform(get("/items/list"))
          .andExpect(status().isOk())
          .andExpect(view().name("list-items"));
	}
	
	@Test
	void addToBasketTest() throws Exception {
		mockMvc.perform(get("/items/addToBasket")
				.queryParam("itemId", "1"))
			.andExpect(status().is3xxRedirection());
	}
	
	@Test
	void goToBasketEmptyTest() throws Exception {
		mockMvc.perform(get("/items/basket")
				.sessionAttr("basket", set))
			.andExpect(status().isOk())
			.andExpect(view().name("empty"));
	}
	
	@Test
	void goToBasketTest() throws Exception {
		Item item = new Item();
		set.add(item);
		mockMvc.perform(get("/items/basket")
				.sessionAttr("basket", set))
			.andExpect(status().isOk())
			.andExpect(view().name("theBasket"));
	}
	
	@Test
	void deleteFromBasketTest() throws Exception {
		mockMvc.perform(get("/items/delete")
				.queryParam("itemId", "1"))
			.andExpect(status().is3xxRedirection());
	}
}









