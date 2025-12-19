package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.ProductDTO;
import com.ecommerce.backend.models.CategoryEnum;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.models.ProductStatus;
import com.ecommerce.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService pService;

    // this method adds new product to catalog by seller(if seller is new it adds
    // seller as well
    // if seller is already existing products will be mapped to same seller) and
    // returns added product

    @PostMapping("/products")
    public ResponseEntity<Product> addProductToCatalogHandler(@RequestHeader("token") String token,
                                                              @Valid @RequestBody Product product) {

        Product prod = pService.addProductToCatalog(token, product);

        return new ResponseEntity<Product>(prod, HttpStatus.ACCEPTED);

    }

    // This method gets the product which needs to be added to the cart returns
    // product

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductFromCatalogByIdHandler(@PathVariable("id") Integer id) {

        Product prod = pService.getProductFromCatalogById(id);

        return new ResponseEntity<Product>(prod, HttpStatus.FOUND);

    }

    // This method will delete the product from catalog and returns the response
    // This will be called only when the product qty will be zero or seller wants to
    // delete for any other reason

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductFromCatalogHandler(@PathVariable("id") Integer id) {

        String res = pService.deleteProductFromCatalog(id);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> updateProductInCatalogHandler(@Valid @RequestBody Product prod) {

        Product prod1 = pService.updateProductIncatalog(prod);

        return new ResponseEntity<Product>(prod1, HttpStatus.OK);

    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProductsHandler() {

        List<Product> list = pService.getAllProductsIncatalog();

        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    //this method gets the products mapped to a particular seller
    @GetMapping("/products/seller/{id}")
    public ResponseEntity<List<ProductDTO>> getAllProductsOfSellerHandler(@PathVariable("id") Integer id) {

        List<ProductDTO> list = pService.getAllProductsOfSeller(id);

        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
    }

    @GetMapping("/products/{catenum}")
    public ResponseEntity<List<ProductDTO>> getAllProductsInCategory(@PathVariable("catenum") String catenum) {
        CategoryEnum ce = CategoryEnum.valueOf(catenum.toUpperCase());
        List<ProductDTO> list = pService.getProductsOfCategory(ce);
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);

    }

    @GetMapping("/products/status/{status}")
    public ResponseEntity<List<ProductDTO>> getProductsWithStatusHandler(@PathVariable("status") String status) {

        ProductStatus ps = ProductStatus.valueOf(status.toUpperCase());
        List<ProductDTO> list = pService.getProductsOfStatus(ps);

        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);

    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateQuantityOfProduct(@PathVariable("id") Integer id,@RequestBody ProductDTO prodDto){

        Product prod =   pService.updateProductQuantityWithId(id, prodDto);

        return new ResponseEntity<Product>(prod,HttpStatus.ACCEPTED);
    }
}
