/**
 * 
 */
package com.amaysim.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amaysim.dao.entities.Product;
import com.amaysim.dao.interfaces.IProductDao;
import com.amaysim.dto.params.ShoppingCartItem;
import com.amaysim.service.interfaces.IAmaysimService;
import com.amaysim.utilities.PriceRulesUtility;
import com.amaysim.utilities.ProductConstants;

/**
 * A class that implements IAmaysimService.
 * 
 * @author r
 * 
 */
public class AmaysimServiceImpl implements IAmaysimService {

    private static final String YES = "y";

    @Override
    public void login(Scanner inputScanner) {
        System.out.println("Please input your name: ");
        String name = inputScanner.next();

        System.out.println("Hello " + name + "! Welcome to Amaysim Shopping Cart!");
    }

    @Override
    public void addProductList(Scanner inputScanner, IProductDao productDao) {
        Product smallProduct = new Product();
        smallProduct.setId(ProductConstants.Small.ID);
        smallProduct.setCode(ProductConstants.Small.CODE);
        smallProduct.setName(ProductConstants.Small.NAME);
        smallProduct.setPrice(new BigDecimal(ProductConstants.Small.PRICE));

        Product mediumProduct = new Product();
        mediumProduct.setId(ProductConstants.Medium.ID);
        mediumProduct.setCode(ProductConstants.Medium.CODE);
        mediumProduct.setName(ProductConstants.Medium.NAME);
        mediumProduct.setPrice(new BigDecimal(ProductConstants.Medium.PRICE));

        Product largeProduct = new Product();
        largeProduct.setId(ProductConstants.Large.ID);
        largeProduct.setCode(ProductConstants.Large.CODE);
        largeProduct.setName(ProductConstants.Large.NAME);
        largeProduct.setPrice(new BigDecimal(ProductConstants.Large.PRICE));

        Product gbProduct = new Product();
        gbProduct.setId(ProductConstants.Gb.ID);
        gbProduct.setCode(ProductConstants.Gb.CODE);
        gbProduct.setName(ProductConstants.Gb.NAME);
        gbProduct.setPrice(new BigDecimal(ProductConstants.Gb.PRICE));

        productDao.create(smallProduct);
        productDao.create(mediumProduct);
        productDao.create(largeProduct);
        productDao.create(gbProduct);
    }

    @Override
    public void shop(Scanner inputScanner, IProductDao productDao,
            PriceRulesUtility priceRulesUtility) {
        List<Product> productList = productDao.getList();
        if (productList != null && !productList.isEmpty()) {
            System.out.println("Here are the products...");
            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);
                System.out.println(product.getId() + ". " + product.getName() + " - $"
                        + product.getPrice());
            }

            System.out.println("Do you want to shop? [Y/N] ");
            String answer = inputScanner.next();

            List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

            while (answer != null && answer.equalsIgnoreCase(YES)) {
                System.out.println("Please input the product number: [1/2/3/4] ");
                int id = inputScanner.nextInt();

                System.out.println("Please input the pieces: ");
                int pieces = inputScanner.nextInt();

                System.out.println("Is this your first month to purchase the product? [Y/N] ");
                String firstMonthToPurchase = inputScanner.next();

                ShoppingCartItem newShoppingCartItem = createShoppingCartItem(productDao, id,
                        pieces, firstMonthToPurchase);
                shoppingCartItemList.add(newShoppingCartItem);

                System.out.println("Do you want to add more items? [Y/N] ");
                answer = inputScanner.next();
            }

            if (shoppingCartItemList != null && !shoppingCartItemList.isEmpty()) {
                processShoppingCart(inputScanner, productDao, priceRulesUtility,
                        shoppingCartItemList);
            }
        }

        System.out.println("Thank you for shopping! See you again! :) -Amaysim");
    }

    private ShoppingCartItem createShoppingCartItem(IProductDao productDao, int id, int pieces,
            String firstMonthToPurchase) {
        boolean isFirstMonthToPurchase = false;
        if (firstMonthToPurchase != null && firstMonthToPurchase.equalsIgnoreCase(YES)) {
            isFirstMonthToPurchase = true;
        }

        Product addedProduct = productDao.retrieve(id);

        BigDecimal itemPiecesBd = new BigDecimal(pieces);
        BigDecimal itemProductPriceBd = addedProduct.getPrice();
        BigDecimal itemTotalPriceBd = itemPiecesBd.multiply(itemProductPriceBd);

        ShoppingCartItem newShoppingCartItem = new ShoppingCartItem();
        newShoppingCartItem.setPieces(pieces);
        newShoppingCartItem.setProduct(addedProduct);
        newShoppingCartItem.setTotalPrice(itemTotalPriceBd);
        newShoppingCartItem.setFirstMonthToPurchase(isFirstMonthToPurchase);
        return newShoppingCartItem;
    }

    private void processShoppingCart(Scanner inputScanner, IProductDao productDao,
            PriceRulesUtility priceRulesUtility, List<ShoppingCartItem> shoppingCartItemList) {
        int shoppingCartItemListSize = shoppingCartItemList.size();
        BigDecimal shoppingCartTotalPriceBd = BigDecimal.ZERO;

        for (int i = 0; i < shoppingCartItemListSize; i++) {
            shoppingCartItemList = priceRulesUtility.processProductPriceRules(shoppingCartItemList,
                    i, productDao);
        }

        System.out.println("Shopping cart items: ");
        shoppingCartItemListSize = shoppingCartItemList.size();
        for (int i = 0; i < shoppingCartItemListSize; i++) {
            ShoppingCartItem newShoppingCartItem = shoppingCartItemList.get(i);
            shoppingCartTotalPriceBd = shoppingCartTotalPriceBd.add(newShoppingCartItem
                    .getTotalPrice());
            System.out.println(newShoppingCartItem.getPieces() + " x "
                    + newShoppingCartItem.getProduct().getName());
        }

        System.out.println("Please input the promo code: ");
        String promoCode = inputScanner.next();

        shoppingCartTotalPriceBd = priceRulesUtility.computeTotalPriceWithPromoCode(promoCode,
                shoppingCartTotalPriceBd);
        System.out.println("Shopping cart total price: $" + shoppingCartTotalPriceBd);
    }
}