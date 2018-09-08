package de.davelee.trams.revenue.admin;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents a category (e.g. adult, child etc.) and a price for this category.
 * @author Dave Lee
 */
public class CategoryPrice implements Serializable {

    private String category;
    private BigDecimal price;

    /**
     * Create a new category and price.
     * @param category a <code>String</code> containing the name of the category.
     * @param price a <code>BigDecimal</code> containing the price.
     */
    public CategoryPrice(final String category, final BigDecimal price) {
        this.category = category;
        this.price = price;
    }

    /**
     * Get the name of the category.
     * @return a <code>String</code> containing the name of the category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the name of the category.
     * @param category a <code>String</code> containing the name of the category.
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * Get the price for this category.
     * @return a <code>BigDecimal</code> containing the price of the category.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the price for this category.
     * @param price a <code>BigDecimal</code> containing the price of the category.
     */
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

}
