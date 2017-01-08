/**
 * 
 */
package com.amaysim.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amaysim.dao.entities.Product;
import com.amaysim.dao.impl.ProductDaoImpl;
import com.amaysim.dao.interfaces.IProductDao;
import com.amaysim.dto.params.ShoppingCartItem;

/**
 * A class that contains the 4 test scenarios.
 * 
 * @author r
 * 
 */
public class PriceRulesUtilityTest {

    private static Product smallProduct;
    private static Product mediumProduct;
    private static Product largeProduct;
    private static Product gbProduct;

    private static IProductDao productDao;

    @BeforeClass
    public static void setBeforeClass() {
        smallProduct = new Product();
        smallProduct.setId(ProductConstants.Small.ID);
        smallProduct.setCode(ProductConstants.Small.CODE);
        smallProduct.setName(ProductConstants.Small.NAME);
        smallProduct.setPrice(new BigDecimal(ProductConstants.Small.PRICE));

        mediumProduct = new Product();
        mediumProduct.setId(ProductConstants.Medium.ID);
        mediumProduct.setCode(ProductConstants.Medium.CODE);
        mediumProduct.setName(ProductConstants.Medium.NAME);
        mediumProduct.setPrice(new BigDecimal(ProductConstants.Medium.PRICE));

        largeProduct = new Product();
        largeProduct.setId(ProductConstants.Large.ID);
        largeProduct.setCode(ProductConstants.Large.CODE);
        largeProduct.setName(ProductConstants.Large.NAME);
        largeProduct.setPrice(new BigDecimal(ProductConstants.Large.PRICE));

        gbProduct = new Product();
        gbProduct.setId(ProductConstants.Gb.ID);
        gbProduct.setCode(ProductConstants.Gb.CODE);
        gbProduct.setName(ProductConstants.Gb.NAME);
        gbProduct.setPrice(new BigDecimal(ProductConstants.Gb.PRICE));

        productDao = new ProductDaoImpl();
        productDao.create(smallProduct);
        productDao.create(mediumProduct);
        productDao.create(largeProduct);
        productDao.create(gbProduct);
    }

    @Test
    public void testScenario1() {
        BigDecimal expected = new BigDecimal("94.70");

        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();
        PriceRulesUtility priceRulesUtility = new PriceRulesUtility();

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(3);
        shoppingCartItem.setProduct(smallProduct);
        BigDecimal totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(1);
        shoppingCartItem.setProduct(largeProduct);
        totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        int shoppingCartItemListSize = shoppingCartItemList.size();
        BigDecimal shoppingCartTotalPriceBd = BigDecimal.ZERO;

        for (int i = 0; i < shoppingCartItemListSize; i++) {
            shoppingCartItemList = priceRulesUtility.processProductPriceRules(shoppingCartItemList,
                    i, productDao);

            ShoppingCartItem newShoppingCartItem = shoppingCartItemList.get(i);
            shoppingCartTotalPriceBd = shoppingCartTotalPriceBd.add(newShoppingCartItem
                    .getTotalPrice());
        }

        shoppingCartTotalPriceBd = priceRulesUtility.computeTotalPriceWithPromoCode("none",
                shoppingCartTotalPriceBd);

        Assert.assertNotNull(shoppingCartItemList);
        Assert.assertEquals(expected, shoppingCartTotalPriceBd);
    }

    @Test
    public void testScenario2() {
        BigDecimal expected = new BigDecimal("209.40");

        PriceRulesUtility priceRulesUtility = new PriceRulesUtility();

        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(2);
        shoppingCartItem.setProduct(smallProduct);
        BigDecimal totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(4);
        shoppingCartItem.setProduct(largeProduct);
        totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        int shoppingCartItemListSize = shoppingCartItemList.size();
        BigDecimal shoppingCartTotalPriceBd = BigDecimal.ZERO;

        for (int i = 0; i < shoppingCartItemListSize; i++) {
            shoppingCartItemList = priceRulesUtility.processProductPriceRules(shoppingCartItemList,
                    i, productDao);

            ShoppingCartItem newShoppingCartItem = shoppingCartItemList.get(i);
            shoppingCartTotalPriceBd = shoppingCartTotalPriceBd.add(newShoppingCartItem
                    .getTotalPrice());
        }

        shoppingCartTotalPriceBd = priceRulesUtility.computeTotalPriceWithPromoCode("none",
                shoppingCartTotalPriceBd);

        Assert.assertNotNull(shoppingCartItemList);
        Assert.assertEquals(expected, shoppingCartTotalPriceBd);
    }

    @Test
    public void testScenario3() {
        BigDecimal expected = new BigDecimal("84.70");

        PriceRulesUtility priceRulesUtility = new PriceRulesUtility();

        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(1);
        shoppingCartItem.setProduct(smallProduct);
        BigDecimal totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(2);
        shoppingCartItem.setProduct(mediumProduct);
        totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        int shoppingCartItemListSize = shoppingCartItemList.size();
        BigDecimal shoppingCartTotalPriceBd = BigDecimal.ZERO;

        for (int i = 0; i < shoppingCartItemListSize; i++) {
            shoppingCartItemList = priceRulesUtility.processProductPriceRules(shoppingCartItemList,
                    i, productDao);

            ShoppingCartItem newShoppingCartItem = shoppingCartItemList.get(i);
            shoppingCartTotalPriceBd = shoppingCartTotalPriceBd.add(newShoppingCartItem
                    .getTotalPrice());
        }

        shoppingCartTotalPriceBd = priceRulesUtility.computeTotalPriceWithPromoCode("none",
                shoppingCartTotalPriceBd);

        Assert.assertNotNull(shoppingCartItemList);
        Assert.assertEquals(expected, shoppingCartTotalPriceBd);
    }

    @Test
    public void testScenario4() {
        BigDecimal expected = new BigDecimal("31.32");

        PriceRulesUtility priceRulesUtility = new PriceRulesUtility();

        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<>();

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(1);
        shoppingCartItem.setProduct(smallProduct);
        BigDecimal totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setFirstMonthToPurchase(true);
        shoppingCartItem.setPieces(1);
        shoppingCartItem.setProduct(gbProduct);
        totalPriceBd = shoppingCartItem.getProduct().getPrice()
                .multiply(new BigDecimal(shoppingCartItem.getPieces()));
        shoppingCartItem.setTotalPrice(totalPriceBd);
        shoppingCartItemList.add(shoppingCartItem);

        int shoppingCartItemListSize = shoppingCartItemList.size();
        BigDecimal shoppingCartTotalPriceBd = BigDecimal.ZERO;

        for (int i = 0; i < shoppingCartItemListSize; i++) {
            shoppingCartItemList = priceRulesUtility.processProductPriceRules(shoppingCartItemList,
                    i, productDao);

            ShoppingCartItem newShoppingCartItem = shoppingCartItemList.get(i);
            shoppingCartTotalPriceBd = shoppingCartTotalPriceBd.add(newShoppingCartItem
                    .getTotalPrice());
        }

        shoppingCartTotalPriceBd = priceRulesUtility.computeTotalPriceWithPromoCode("I<3AMAYSIM",
                shoppingCartTotalPriceBd);

        Assert.assertNotNull(shoppingCartItemList);
        Assert.assertEquals(expected, shoppingCartTotalPriceBd);
    }

    @Test
    public void testPromoCodeValid() {
        BigDecimal expected = new BigDecimal("22.41");

        String promoCode = "I<3AMAYSIM";
        BigDecimal totalPriceBd = new BigDecimal("24.90");

        PriceRulesUtility priceRulesUtility = new PriceRulesUtility();
        BigDecimal newTotalPriceBd = priceRulesUtility.computeTotalPriceWithPromoCode(promoCode,
                totalPriceBd);

        Assert.assertNotNull(newTotalPriceBd);
        Assert.assertEquals(expected, newTotalPriceBd);
    }

    @Test
    public void testPromoCodeInvalid() {
        BigDecimal expected = new BigDecimal("24.90");

        String promoCode = "AMAYSIM";
        BigDecimal totalPriceBd = new BigDecimal("24.90");

        PriceRulesUtility priceRulesUtility = new PriceRulesUtility();
        BigDecimal newTotalPriceBd = priceRulesUtility.computeTotalPriceWithPromoCode(promoCode,
                totalPriceBd);

        Assert.assertNotNull(newTotalPriceBd);
        Assert.assertEquals(expected, newTotalPriceBd);
    }
}