package de.davelee.trams.revenue.api;

/**
 * This method represents a request to calculate price consisting of a ticket type, fare structure, company name and
 * quantity.
 * @author Dave Lee
 */
public class CalculatePriceRequest {

    private String companyName;
    private String ticketType;
    private String fareStructure;
    private String quantity;

    /**
     * Retrieve the name of the company for this price request.
     * @return a <code>String</code> with the name of the company.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the name of the company for this price request.
     * @param companyName a <code>String</code> with the name of the company.
     */
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    /**
     * Retrieve the type of ticket e.g. single for this price request.
     * @return a <code>String</code> with the type of the ticket.
     */
    public String getTicketType() {
        return ticketType;
    }

    /**
     * Set the type of ticket e.g. single for this price request.
     * @param ticketType a <code>String</code> with the type of the ticket.
     */
    public void setTicketType(final String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * Retrieve the fare structure e.g. adult for this price request.
     * @return a <code>String</code> with the fare structure of the ticket.
     */
    public String getFareStructure() {
        return fareStructure;
    }

    /**
     * Set the fare structure e.g. adult for this price request.
     * @param fareStructure a <code>String</code> with the fare structure of the ticket.
     */
    public void setFareStructure(final String fareStructure) {
        this.fareStructure = fareStructure;
    }

    /**
     * Retrieve the quantity for this price request.
     * @return a <code>String</code> with the quantity.
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity for this price request.
     * @param quantity a <code>String</code> with the quantity.
     */
    public void setQuantity(final String quantity) {
        this.quantity = quantity;
    }
}
