package com.interview.shoppingbasket;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BasketItem {

    private String productCode;
    @EqualsAndHashCode.Exclude
    private String productName;
    @EqualsAndHashCode.Exclude
    private int quantity;
    @EqualsAndHashCode.Exclude
    private double productRetailPrice;




    
    

}
