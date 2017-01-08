/**
 * 
 */
package com.amaysim.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.amaysim.dao.entities.Product;
import com.amaysim.dao.interfaces.IProductDao;

/**
 * A class that implements IProductDao.
 * 
 * @author r
 * 
 */
public class ProductDaoImpl implements IProductDao {

    private List<Product> productList = new ArrayList<>();

    @Override
    public void create(Product params) {
        productList.add(params);
    }

    @Override
    public Product retrieve(int id) {
        int index = id - 1;
        return productList.get(index);
    }

    @Override
    public void update(Product t) {

    }

    @Override
    public void delete(int id) {
        productList.remove(id);
    }

    @Override
    public List<Product> getList() {
        return productList;
    }
}