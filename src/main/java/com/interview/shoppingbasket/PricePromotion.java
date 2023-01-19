package com.interview.shoppingbasket;

import lombok.Data;

@Data
public class PricePromotion extends Promotion{
	private double priceOff;

	@Override
	public double calculateAfterPromotionPrice(BasketItem item, double price) {
		return price * priceOff*item.getQuantity();
		
	}


}
