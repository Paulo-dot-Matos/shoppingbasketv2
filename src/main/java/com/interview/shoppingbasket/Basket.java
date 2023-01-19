package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
    	// Exercise - implements this function
    	// Map items by product code and quantity
    	Map<String,Integer> productQuantities = items.stream().collect(Collectors.toMap(BasketItem::getProductCode, BasketItem::getQuantity,Math::addExact));
    	// update the list with unique elements and the consolidated 
    	this.items=items.stream().distinct().peek(item-> item.setQuantity(productQuantities.get(item.getProductCode()))).collect(Collectors.toList());
    	
    	
    }
}
