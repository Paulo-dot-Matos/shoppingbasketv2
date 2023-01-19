package com.interview.shoppingbasket;

import lombok.Data;

@Data
public class QuantityPromotion extends Promotion{
	private int minimumQuantity;
	private int priceOf;
	
	@Override
	public double calculateAfterPromotionPrice(BasketItem item, double price) {
		// TODO Auto-generated method stub
		if(item.getQuantity()<minimumQuantity) {
			return price;
		} else {
			double timesToApplyDiscount = item.getQuantity()/minimumQuantity;
			double discountFactor = (double) priceOf/(double) minimumQuantity;
			int remainder = item.getQuantity()%minimumQuantity;
			return timesToApplyDiscount * discountFactor * price + (remainder*price);
		}
		
	}
	

}
