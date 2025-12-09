package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CartDTO;
import com.ecommerce.backend.exception.ProductNotFoundException;
import com.ecommerce.backend.models.CartItem;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ProductStatus;
import com.ecommerce.backend.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    ProductDao productDao;

    @Override
    public CartItem createItemforCart(CartDTO cartdto) {

        Product existingProduct = productDao.findById(cartdto.getProductId()).orElseThrow( () -> new ProductNotFoundException("Product Not found"));

        if(existingProduct.getStatus().equals(ProductStatus.OUTOFSTOCK) || existingProduct.getQuantity() == 0) {
            throw new ProductNotFoundException("Product OUT OF STOCK");
        }

        CartItem newItem = new CartItem();

        newItem.setCartItemQuantity(1);

        newItem.setCartProduct(existingProduct);

        return newItem;
    }

//	@Override
//	public CartItem addItemToCart(CartDTO cartdto) {
//
//
//
////		Product existingProduct = productDao.findById(cartdto.getProductId()).orElseThrow( () -> new ProductException("Product Not found"));
//
//		Optional<Product> opt = productDao.findById(cartdto.getProductId());
//
//		if(opt.isEmpty())
//			throw new ProductNotFoundException("Product not found");
//
//		Product existingProduct = opt.get();
//
//		CartItem newItem = new CartItem();
//
//		newItem.setCartProduct(existingProduct);
//
//		newItem.setCartItemQuantity(1);
//
//		return newItem;
//	}

}
