package com.interview.shoppingbasket;

import java.util.List;
import java.util.Optional;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        retailTotal = 0.0;

        for (BasketItem basketItem: basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            Optional<Promotion> promotion = findPromotion(checkoutContext.getPromotions(), basketItem);
            if(promotion.isPresent()) {
            	retailTotal += applyPromotion(promotion.get(), basketItem, price);
            } else {
            	retailTotal += quantity*price;
            }
            
            
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
    	double promotionPrice = promotion.calculateAfterPromotionPrice(item, price);	
        return promotionPrice;
    }
    
    private Optional<Promotion> findPromotion(List<Promotion> promotions,BasketItem basketItem) {
    	return promotions.stream().filter(promotion-> promotion.getProductCode().equalsIgnoreCase(basketItem.getProductCode())).findFirst();
    }
}
