package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CustomerDTO;
import com.ecommerce.backend.dto.SellerDTO;
import com.ecommerce.backend.dto.SessionDTO;
import com.ecommerce.backend.models.UserSession;

public interface LoginLogoutService {
    public UserSession loginCustomer(CustomerDTO customer);

    public SessionDTO logoutCustomer(SessionDTO session);

    public void checkTokenStatus(String token);

    public void deleteExpiredTokens();


    public UserSession loginSeller(SellerDTO seller);

    public SessionDTO logoutSeller(SessionDTO session);
}
