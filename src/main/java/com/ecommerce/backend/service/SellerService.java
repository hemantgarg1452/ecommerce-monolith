package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.SellerDTO;
import com.ecommerce.backend.dto.SessionDTO;
import com.ecommerce.backend.exception.SellerException;
import com.ecommerce.backend.models.Seller;

import java.util.List;

public interface SellerService {
    public Seller addSeller(Seller seller);

    public List<Seller> getAllSellers() throws SellerException;

    public Seller getSellerById(Integer sellerId)throws SellerException;

    public Seller getSellerByMobile(String mobile, String token) throws SellerException;

    public Seller getCurrentlyLoggedInSeller(String token) throws SellerException;

    public SessionDTO updateSellerPassword(SellerDTO sellerDTO, String token) throws SellerException;

    public Seller updateSeller(Seller seller, String token)throws SellerException;

    public Seller updateSellerMobile(SellerDTO sellerdto, String token)throws SellerException;

    public Seller deleteSellerById(Integer sellerId, String token)throws SellerException;

}
