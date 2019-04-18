package com.vironit.vinylRecordsStore.data;

import com.vironit.vinylRecordsStore.dto.CartItemDTO;

public class MarketData {
    
    public static final double DELIVERY_COST = 5;
    
    public static final int PRODUCT_ID = 1;
    public static final int PRODUCT_QUANTITY = 2;
    public static final int PRODUCT_UNIT_COST = 118;
    
    public static final int IMPROBABLE_ID = 100500;

    public static CartItemDTO getCartItemDTO() {
        return new CartItemDTO(PRODUCT_ID, (short)PRODUCT_QUANTITY);
    }

}
