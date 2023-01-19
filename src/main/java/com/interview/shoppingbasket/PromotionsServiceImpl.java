package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionsServiceImpl implements PromotionsService {
	

	@Override
	public List<Promotion> getPromotions(Basket basket) {

		return getPromotionsDB().stream()
				.filter(promotion->basket.getItems().stream()
						.anyMatch(item-> item.getProductCode().equalsIgnoreCase(promotion.getProductCode())))
				.collect(Collectors.toList());
	}
	
	private List<Promotion> getPromotionsDB(){
		// This would be from a db
		List<Promotion> promotionsDataBase = new ArrayList<>();
		PricePromotion promotionOne = new PricePromotion();
		PricePromotion promotionTwo =new PricePromotion();
		QuantityPromotion promotionThree= new QuantityPromotion();
		PricePromotion promotionFour= new PricePromotion();
		promotionOne.setProductCode("productCode");
		promotionOne.setActive(true);
		promotionOne.setPriceOff(0.5);
		promotionTwo.setProductCode("productCode2");
		promotionTwo.setActive(true);
		promotionTwo.setPriceOff(0.5);
		promotionThree.setProductCode("productCode3");
		promotionThree.setActive(true);
		promotionThree.setMinimumQuantity(2);
		promotionThree.setPriceOf(1);
		promotionFour.setProductCode("productCode4");
		promotionFour.setActive(true);
		promotionFour.setPriceOff(0.2);
		promotionsDataBase.add(promotionOne);
		promotionsDataBase.add(promotionTwo);
		promotionsDataBase.add(promotionThree);
		promotionsDataBase.add(promotionFour);
		return promotionsDataBase;
		
	}

	

}
