package de.davelee.trams.revenue.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * This class represents a ticket consisting of a type, description and a price list allowing multiple prices for same ticket.
 * @author Dave Lee
 */
public class Ticket implements Serializable {

    private String shortId;
    private String type;
    private String description;
    private int sortOrder;
    private HashMap<String, BigDecimal> priceList;

    /**
     * Return the short id for this ticket as a String - the shortId is used as the hash code as well.
     * @return a <code>String</code> with the short id of this ticket.
     */
    public String getShortId() {
        return shortId;
    }

    /**
     * Set the short id of this ticket as a String - the shortId is used as the hash code as well.
     * @param shortId a <code>String</code> with the short id of this ticket.
     */
    public void setShortId(final String shortId) {
        this.shortId = shortId;
    }

    /**
     * Return the type of this ticket as a String - the type is also the name of the ticket.
     * @return a <code>String</code> with the type of this ticket.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of this ticket as a String - the type is also the name of the ticket.
     * @param type a <code>String</code> with the type of this ticket.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Return the description of this ticket as a String - the description is a short info about how this ticket is valid.
     * @return a <code>String</code> with the description of this ticket.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of this ticket as a String - the description is a short info about how this ticket is valid.
     * @param description a <code>String</code> with the type of this ticket.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Return the sort order of this ticket as an int - the sort order defines the order if this ticket is stored in a map.
     * @return a <code>int</code> with the sort order of this ticket.
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * Set the sort order of this ticket as an int - the sort order defines the order if this ticket is stored in a map.
     * @param sortOrder a <code>int</code> with the sort order of this ticket.
     */
    public void setSortOrder(final int sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Return the price list of this ticket as a map consisting of descriptions and prices which are valid for this ticket.
     * @return a <code>HashMap</code> with the price list of this ticket.
     */
    public HashMap<String, BigDecimal> getPriceList() {
        return priceList;
    }

    /**
     * Return a particular price based on the specified price code e.g. Adult.
     * @param priceCode a <code>String</code> with the specified price code.
     * @return a <code>BigDecimal</code> with the price as a number with 2 decimal points.
     */
    public BigDecimal getPrice ( final String priceCode ) {
        return priceList.get(priceCode);
    }

    /**
     * Set the price list of this ticket as a map consisting of descriptions and prices which are valid for this ticket.
     * @param priceList a <code>HashMap</code> with the price list of this ticket.
     */
    public void setPriceList(final HashMap<String, BigDecimal> priceList) {
        this.priceList = priceList;
    }

}
