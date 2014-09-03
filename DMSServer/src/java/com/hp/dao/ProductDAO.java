/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Product;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ProductDAO {
    public List<Product> getCustomerProductList(String pCustomerID, String pProvider);
    public List<Product> getProductList(String pProvider);
    public List<Product> getProductList();
    
    public Product loadProduct(int pID);
    public boolean saveOrUpdate(Product pProduct);
    public boolean update(Product pProduct);
    
    public boolean delete(Product pProduct);
    
    //Search product
    public List<Product> getSearchProductList(String pText);
}
