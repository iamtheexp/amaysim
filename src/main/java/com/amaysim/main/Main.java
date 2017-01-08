/**
 * 
 */
package com.amaysim.main;

import java.util.Scanner;

import com.amaysim.dao.impl.ProductDaoImpl;
import com.amaysim.dao.interfaces.IProductDao;
import com.amaysim.service.impl.AmaysimServiceImpl;
import com.amaysim.service.interfaces.IAmaysimService;
import com.amaysim.utilities.PriceRulesUtility;

/**
 * A class that contains the main method to access the Amaysim services. Since
 * it is a console application, it uses System.out.println.
 * 
 * @author r
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        IProductDao productDao = new ProductDaoImpl();
        PriceRulesUtility priceRulesUtility = new PriceRulesUtility();

        IAmaysimService amaysimService = new AmaysimServiceImpl();
        amaysimService.login(inputScanner);
        amaysimService.addProductList(inputScanner, productDao);
        amaysimService.shop(inputScanner, productDao, priceRulesUtility);

        inputScanner.close();
    }
}
