package com.amaysim.utilities;

import java.math.BigDecimal;
import java.util.List;

import com.amaysim.dao.entities.Product;
import com.amaysim.dto.params.ShoppingCartItem;

/**
 * A class the contains the different price rules and promotions of each
 * product.
 * 
 * @author r
 * 
 */
public class PriceRulesUtility {

    private static final int TWO_PIECES = 2;
    private static final int THREE_PIECES = 3;
    private static final int TWO_DECIMAL_PLACES = 2;
    private static final String PROMO_CODE = "I<3AMAYSIM";
    private static final BigDecimal PROMO_CODE_DISCOUNT = new BigDecimal(".10");

    public List<ShoppingCartItem> processProductPriceRules(
            List<ShoppingCartItem> shoppingCartItemList, int i) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemList.get(i);

        int itemProductId = shoppingCartItem.getProduct().getId();
        String itemProductCode = shoppingCartItem.getProduct().getCode();
        int itemPieces = shoppingCartItem.getPieces();
        boolean isFirstMonthToPurchase = shoppingCartItem.isFirstMonthToPurchase();

        if (itemProductCode.equals(ProductConstants.Small.CODE) && itemPieces == THREE_PIECES
                && isFirstMonthToPurchase) {
            shoppingCartItemList = processSmallProductPriceRule(shoppingCartItem,
                    shoppingCartItemList, i, itemProductId, itemPieces);
        }

        if (itemProductCode.equals(ProductConstants.Medium.CODE)) {
            shoppingCartItemList = processMediumProductPriceRule(shoppingCartItem,
                    shoppingCartItemList, itemPieces);
        }

        if (itemProductCode.equals(ProductConstants.Large.CODE) && itemPieces > THREE_PIECES
                && isFirstMonthToPurchase) {
            shoppingCartItemList = processLargeProductPriceRule(shoppingCartItem,
                    shoppingCartItemList, i, itemProductId, itemPieces);
        }

        return shoppingCartItemList;
    }

    private List<ShoppingCartItem> processLargeProductPriceRule(ShoppingCartItem shoppingCartItem,
            List<ShoppingCartItem> shoppingCartItemList, int i, int itemProductId, int itemPieces) {
        Product promoProduct = shoppingCartItem.getProduct();
        promoProduct.setPrice(new BigDecimal(ProductConstants.Large.NEW_PRICE));

        BigDecimal productPiecesBd = new BigDecimal(itemPieces);
        BigDecimal productPriceBd = promoProduct.getPrice();
        BigDecimal productTotalPriceBd = productPiecesBd.multiply(productPriceBd);

        ShoppingCartItem promoShoppingCart = new ShoppingCartItem();
        promoShoppingCart.setPieces(itemPieces);
        promoShoppingCart.setProduct(promoProduct);
        promoShoppingCart.setTotalPrice(productTotalPriceBd);

        shoppingCartItemList.remove(i);
        shoppingCartItemList.add(promoShoppingCart);

        return shoppingCartItemList;
    }

    private List<ShoppingCartItem> processMediumProductPriceRule(ShoppingCartItem shoppingCartItem,
            List<ShoppingCartItem> shoppingCartItemList, int itemPieces) {
        Product promoProduct = shoppingCartItem.getProduct();

        ShoppingCartItem promoShoppingCartItem = new ShoppingCartItem();
        promoShoppingCartItem.setPieces(itemPieces);
        promoShoppingCartItem.setProduct(promoProduct);
        promoShoppingCartItem.setTotalPrice(BigDecimal.ZERO);

        shoppingCartItemList.add(promoShoppingCartItem);

        return shoppingCartItemList;
    }

    private List<ShoppingCartItem> processSmallProductPriceRule(ShoppingCartItem shoppingCartItem,
            List<ShoppingCartItem> shoppingCartItemList, int i, int itemProductId, int itemPieces) {
        Product promoProduct = shoppingCartItem.getProduct();

        BigDecimal itemPiecesBd = new BigDecimal(TWO_PIECES);
        BigDecimal itemProductPriceBd = promoProduct.getPrice();
        BigDecimal itemProductTotalPriceBd = itemPiecesBd.multiply(itemProductPriceBd);

        ShoppingCartItem promoShoppingCartItem = new ShoppingCartItem();
        promoShoppingCartItem.setPieces(itemPieces);
        promoShoppingCartItem.setProduct(promoProduct);
        promoShoppingCartItem.setTotalPrice(itemProductTotalPriceBd);

        shoppingCartItemList.remove(i);
        shoppingCartItemList.add(promoShoppingCartItem);

        return shoppingCartItemList;
    }

    public BigDecimal computeTotalPriceWithPromoCode(String promoCode, BigDecimal totalPriceBd) {
        BigDecimal newTotalPriceBd = totalPriceBd;

        if (promoCode != null && promoCode.equals(PROMO_CODE)) {
            BigDecimal priceToBeDeductedBd = totalPriceBd.multiply(PROMO_CODE_DISCOUNT);
            newTotalPriceBd = totalPriceBd.subtract(priceToBeDeductedBd);
            newTotalPriceBd = newTotalPriceBd.setScale(TWO_DECIMAL_PLACES);
        }

        return newTotalPriceBd;
    }
}