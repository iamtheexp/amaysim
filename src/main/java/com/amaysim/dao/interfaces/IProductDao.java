/**
 * 
 */
package com.amaysim.dao.interfaces;

import java.util.List;

import com.amaysim.dao.entities.Product;

/**
 * An interface that contains the specific database functionalities to access
 * product table.
 * 
 * @author r
 * 
 */
public interface IProductDao extends IBaseDao<Product> {

    /**
     * Retrieves all the records in the table.
     * 
     * @return
     */
    public List<Product> getList();
}