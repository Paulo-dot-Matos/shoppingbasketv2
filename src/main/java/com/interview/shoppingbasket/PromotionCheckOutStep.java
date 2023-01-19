package com.interview.shoppingbasket;

public class PromotionCheckOutStep implements CheckoutStep{
	
	private PromotionsService promotionService;
	
	public PromotionCheckOutStep(PromotionsService promotionService){
		this.promotionService = promotionService;
	}

	@Override
	public void execute(CheckoutContext checkoutContext) {
		  Basket basket = checkoutContext.getBasket();
		  checkoutContext.addPromotions(promotionService.getPromotions(basket));
	}

}
