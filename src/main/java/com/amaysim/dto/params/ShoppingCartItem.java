/**
 * 
 */
package com.amaysim.dto.params;

import java.math.BigDecimal;

import com.amaysim.dao.entities.Product;

/**
 * A class that contains the input parameters for shop service.
 * 
 * @author r
 * 
 */
public class ShoppingCartItem {

    private int id;
    private int pieces;
    private Product product;
    private BigDecimal totalPrice;
    private boolean isFirstMonthToPurchase;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the pieces
     */
    public int getPieces() {
        return pieces;
    }

    /**
     * @param pieces
     *            the pieces to set
     */
    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice
     *            the totalPrice to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the isFirstMonthToPurchase
     */
    public boolean isFirstMonthToPurchase() {
        return isFirstMonthToPurchase;
    }

    /**
     * @param isFirstMonthToPurchase
     *            the isFirstMonthToPurchase to set
     */
    public void setFirstMonthToPurchase(boolean isFirstMonthToPurchase) {
        this.isFirstMonthToPurchase = isFirstMonthToPurchase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShoppingCartItem [id=" + id + ", pieces=" + pieces + ", product=" + product
                + ", totalPrice=" + totalPrice + ", isFirstMonthToPurchase="
                + isFirstMonthToPurchase + "]";
    }
}