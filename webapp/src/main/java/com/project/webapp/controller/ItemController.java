package com.project.webapp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.webapp.handle.BasketHandle;
import com.project.webapp.handle.ItemHandle;
import com.project.webapp.item.Item;

@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemHandle itemHandle;
	
	@Autowired
	private BasketHandle basketHandle;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/list")
	public String listItems(Model model, HttpSession session) {
		List<Item> itemList = itemHandle.getItems();
		model.addAttribute("items", itemList);
		Set<Item> basket = (Set<Item>) session.getAttribute("basket");
		if (basket == null) {
			basket = new HashSet<Item>();
			session.setAttribute("basket", basket);
		}
		Integer basketSize = basketHandle.getBasketSize(basket);
		model.addAttribute("basketSize", basketSize);
		double totalPrice = basketHandle.getTotalPrice(basket);
		if (totalPrice > 100) {
			model.addAttribute("discount", true);
		}
		return "list-items";
	}
	
	@GetMapping("/addToBasket")
	public String addToBasket(@RequestParam("itemId") int id, HttpSession session) {
		Item it = itemHandle.getItemById(id);
		basketHandle.addToBasket(it, session);
		return "redirect:/items/list";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/basket")
	public String goToBasket(HttpSession session, Model model) {
		Set<Item> basketItems = (Set<Item>) session.getAttribute("basket");
		if (basketItems.size() == 0) {
			return "empty";
		} 
		model.addAttribute("basketItems", basketItems);
		setModelByPrice(model, basketItems);
		double shippingCosts = basketHandle.getShippingCosts(basketItems);
		model.addAttribute("shippingCosts", shippingCosts);
		return "theBasket";
	}

	void setModelByPrice(Model model, Set<Item> basketItems) {
		double totalPrice = basketHandle.getTotalPrice(basketItems);
		if (totalPrice > 100) {
			double discountPrice =  totalPrice - (0.1 * totalPrice);
			model.addAttribute("total", discountPrice);
			model.addAttribute("initialPrice", totalPrice);
			model.addAttribute("discount", true);
		} else {
			model.addAttribute("total", totalPrice);
		}
	}
	
	@GetMapping("/delete")
	public String deleteFromBasket(@RequestParam("itemId") int id, HttpSession session) {
		basketHandle.deleteItem(id, session);
		return "redirect:/items/basket";
	}
}






