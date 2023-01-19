package com.interview.shoppingbasket;

import lombok.Data;

@Data
public abstract class Promotion {
	private String productCode;
	private boolean active;
	public abstract double calculateAfterPromotionPrice (BasketItem item, double price);
}
