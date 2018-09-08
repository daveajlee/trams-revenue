package de.davelee.trams.revenue.api;

/**
 * This method represents a request to pay by credit card consisting of type, number, expiry month and security code.
 * @author Dave Lee
 */
public class CreditCardPaymentRequest {

    private String companyName;
    private String type;
    private String number;
    private String expiryMonth;
    private int securityCode;

    /**
     * Retrieve the name of the company for this payment request.
     * @return a <code>String</code> with the name of the company.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the name of the company for this payment request.
     * @param companyName a <code>String</code> with the name of the company.
     */
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    /**
     * Retrieve the type of the credit card for this payment request.
     * @return a <code>String</code> with the type of the credit card.
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the credit card for this payment request.
     * @param type a <code>String</code> with the type of the credit card.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Retrieve the number of the credit card for this payment request.
     * @return a <code>String</code> with the number of the credit card.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the number of the credit card for this payment request.
     * @param number a <code>String</code> with the number of the credit card.
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Retrieve the expiry month of the credit card for this payment request.
     * @return a <code>String</code> with the expiry month of the credit card.
     */
    public String getExpiryMonth() {
        return expiryMonth;
    }

    /**
     * Set the expiry month of the credit card for this payment request.
     * @param expiryMonth a <code>String</code> with the expiry month of the credit card.
     */
    public void setExpiryMonth(final String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    /**
     * Retrieve the security code of the credit card for this payment request.
     * @return a <code>int</code> with the security code of the credit card.
     */
    public int getSecurityCode() {
        return securityCode;
    }

    /**
     * Set the security code of the credit card for this payment request.
     * @param securityCode a <code>int</code> with the security code of the credit card.
     */
    public void setSecurityCode(final int securityCode) {
        this.securityCode = securityCode;
    }
}
