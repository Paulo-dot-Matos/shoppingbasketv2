package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CheckoutPipelineTest {

    CheckoutPipeline checkoutPipeline;

    @Mock
    Basket basket;

    @Mock
    CheckoutStep checkoutStep1;

    @Mock
    CheckoutStep checkoutStep2;
    
    PricingService pricingService;

    @BeforeEach
    void setup() {
    	pricingService = Mockito.mock(PricingService.class);
        checkoutPipeline = new CheckoutPipeline();
    }

    @Test
    void returnZeroPaymentForEmptyPipeline() {
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

        assertEquals(paymentSummary.getRetailTotal(), 0.0);
    }

    @Test
    void executeAllPassedCheckoutSteps() {
    	// Exercise - implement testing passing through all checkout steps
        when(pricingService.getPrice("productCode")).thenReturn(4.0);
        when(pricingService.getPrice("productCode2")).thenReturn(2.0);
        when(pricingService.getPrice("productCode3")).thenReturn(5.0);
        when(pricingService.getPrice("productCode5")).thenReturn(2.5);
    	 
         Basket basketTest = new Basket();
         basketTest.add("productCode", "myProduct", 10);
         basketTest.add("productCode", "myProduct", 10);
         basketTest.add("productCode2", "myProduct2", 10);
         basketTest.add("productCode3", "myProduct3", 2);
         basketTest.add("productCode5", "myProduct3", 1);
         BasketConsolidationCheckoutStep stepOne = new BasketConsolidationCheckoutStep();
         PromotionCheckOutStep prmotionStep = new PromotionCheckOutStep(new PromotionsServiceImpl());
         RetailPriceCheckoutStep stepTwo = new RetailPriceCheckoutStep(pricingService);
         CheckoutPipeline checkoutPipelineTest = new CheckoutPipeline();
        
         checkoutPipelineTest.addStep(stepOne);
         checkoutPipelineTest.addStep(prmotionStep);
        checkoutPipelineTest.addStep(stepTwo);
        PaymentSummary paymentSummary = checkoutPipelineTest.checkout(basketTest);
        //55 is the final result when applied the discount from class PromotionsServiceImpl.class
        assertEquals(paymentSummary.getRetailTotal(), 55);
     
    }

}
