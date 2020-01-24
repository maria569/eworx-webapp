package com.project.webapp.handle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.webapp.item.Item;


@ExtendWith(MockitoExtension.class)
public class BasketHandleImplTest {

	@Mock
	HttpSession mockSession;
	
	BasketHandleImpl basketHandle;
	
	Item item;
	
	Set<Item> basketSet;

	@BeforeEach
	void setUp() {
		basketHandle = new BasketHandleImpl();
		item = new Item(1, "book", 5.00, 0.2);
		item.setQuantity(1);
	    basketSet = new HashSet<Item>();
		basketSet.add(item);
	}
	
	@Test
	void addToBasketContainsItemTest() {
		Item newItem = new Item(1, "book", 5.00, 0.2);
		newItem.setQuantity(1);
		when(mockSession.getAttribute(anyString())).thenReturn(basketSet);
		basketHandle.addToBasket(newItem, mockSession);
		verify(mockSession).getAttribute(anyString());
	}
	
	@Test
	void addToBasketNoItemTest() {
		Item item = new Item();
		item.setItemId(2);
		when(mockSession.getAttribute(anyString())).thenReturn(basketSet);
		basketHandle.addToBasket(item, mockSession);
		verify(mockSession).getAttribute(anyString());
	}
	
	@Test
	void getBasketSizeTest() {
		Integer result = basketHandle.getBasketSize(basketSet);
		assertEquals(1, result);
	}
	
	@Test
	void getTotalPriceOneItemTest() {
		Double result = basketHandle.getTotalPrice(basketSet);
		assertEquals(5.00, result);
	}
	
	@Test
	void getTotalPriceNoItemsTest() {
		Set<Item> basketS = new HashSet<Item>();
		Double result = basketHandle.getTotalPrice(basketS);
		assertEquals(Double.valueOf(0), result);
	}
	
	@Test
	void deleteItemOneItemTest() {
		when(mockSession.getAttribute(anyString())).thenReturn(basketSet);
		basketHandle.deleteItem(1, mockSession);
		verify(mockSession).getAttribute(anyString());
	}
	
	@Test
	void deleteItemTwoItemdTest() {
		Item newItem = new Item();
		newItem.setItemId(2);
		newItem.setQuantity(2);
		basketSet.add(newItem);
		when(mockSession.getAttribute(anyString())).thenReturn(basketSet);
		basketHandle.deleteItem(2, mockSession);
		verify(mockSession).getAttribute(anyString());
	}
	
	@Test
	void getShippingCostsTest() {
		double result = basketHandle.getShippingCosts(basketSet);
		assertEquals(1, result);
	}
	
}










