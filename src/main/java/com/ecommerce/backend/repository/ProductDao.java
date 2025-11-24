package com.ecommerce.backend.repository;

import com.ecommerce.backend.dto.ProductDTO;
import com.ecommerce.backend.models.CategoryEnum;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query("SELECT NEW com.ecommerce.backend.dto.ProductDTO(p.productName, p.manufacturer,p.price,p.quantity) "
            + "FROM Product p WHERE p.category=:catenum")
    public List<ProductDTO> getAllProductsInACategory(@Param("catenum")CategoryEnum catenum);

    @Query("SELECT NEW com.ecommerce.backend.dto.ProductDTO(p.productName,p.manufacturer,p.price,p.quantity)"
            + "from Product p where p.status=:status")
    public List<ProductDTO> getProductsWithStatus(@Param("status")ProductStatus status);

    @Query("select new com.ecommerce.backend.dto.ProductDTO(p.productName,p.manufacturer,p.price,p.quantity) "
            + "from Product p where p.seller.sellerId=:id")
    public List<ProductDTO>getProductsOfASeller(@Param("id") Integer id);
}
