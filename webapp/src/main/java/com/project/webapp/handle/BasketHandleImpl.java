package com.project.webapp.handle;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.project.webapp.item.Item;

@Component
public class BasketHandleImpl implements BasketHandle {
	
	@SuppressWarnings("unchecked")
	@Override
	public void addToBasket(Item item, HttpSession session) {
		Set<Item> basketSet = (Set<Item>) session.getAttribute("basket");
		if (basketSet.contains(item)) {
			item.setQuantity(item.getQuantity() + 1);
		} else {
			item.setQuantity(1);
		}
		basketSet.add(item);
		session.setAttribute("basket", basketSet);
	}

	@Override
	public Integer getBasketSize(Set<Item> basketSet) {
		Integer count = 0;
		for (Item item : basketSet) {
			count = count + item.getQuantity();
		}
		return count;
	}
	
	@Override
	public Double getTotalPrice(Set<Item> basketSet) {
		if (basketSet.size() == 0) {
			return Double.valueOf(0);
		}
		Optional<Double> total = basketSet.stream().map(item -> item.getPrice() * item.getQuantity()).reduce((subTotal, item) -> subTotal + item);
		return total.get();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteItem(int id, HttpSession session) {
		Set<Item> tempSet = new HashSet<Item>();
		boolean delete = false;
		Set<Item> basketSet = (Set<Item>) session.getAttribute("basket");
		for (Item item : basketSet) {
			if (item.getItemId() == id) {
				item.setQuantity(item.getQuantity()-1);
				if (item.getQuantity() == 0) {
					tempSet.add(item);
					delete = true;
				} else {
					basketSet.add(item);
				}
			}
		}
	    if (delete) {
	    	basketSet.removeAll(tempSet);
	    }
	    session.setAttribute("basket", basketSet);
	}

	@Override
	public double getShippingCosts(Set<Item> basketSet) {
		double shippingCosts = 0;
		for (Item item : basketSet) {
			shippingCosts = shippingCosts + (item.getWeightFactor() * 5 * item.getQuantity());
		}
		return shippingCosts;
	}
}
