/**
 * 
 */
package com.amaysim.service.interfaces;

import java.util.Scanner;

import com.amaysim.dao.interfaces.IProductDao;
import com.amaysim.utilities.PriceRulesUtility;

/**
 * An interface that contains the different functionalities for Amaysim service.
 * 
 * @author r
 * 
 */
public interface IAmaysimService {

    /**
     * Allows the user to input his/her name.
     * 
     * @param inputScanner
     */
    public void login(Scanner inputScanner);

    /**
     * Creates the hard coded product list to be used.
     * 
     * @param inputScanner
     * @param productDao
     */
    public void addProductList(Scanner inputScanner, IProductDao productDao);

    /**
     * Contains the displaying and inputting of products and computation of
     * promotions.
     * 
     * @param inputScanner
     * @param productDao
     * @param priceRulesUtility
     */
    public void shop(Scanner inputScanner, IProductDao productDao,
            PriceRulesUtility priceRulesUtility);
}