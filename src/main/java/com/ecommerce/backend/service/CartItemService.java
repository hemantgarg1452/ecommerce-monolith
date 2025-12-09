package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CartDTO;
import com.ecommerce.backend.models.CartItem;

public interface CartItemService {
    public CartItem createItemforCart(CartDTO cartdto);
}
