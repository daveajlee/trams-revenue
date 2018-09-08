package de.davelee.trams.revenue.services;

import de.davelee.trams.revenue.api.Ticket;
import de.davelee.trams.revenue.api.Tickets;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class provides methods for adding, displaying and purchasing tickets for one or more companies.
 * @author Dave Lee
 */
@Service
public class TicketService {

    private Map<String, Tickets> companyTicketsMap = new HashMap<>();

    /**
     * Load a set of available tickets based on the supplied Tickets object.
     * @param tickets a <code>Tickets</code> object containing the available tickets and a company name.
     */
    public void loadTicketMapForCompany ( final Tickets tickets ) {
        companyTicketsMap.put(tickets.getCompanyName(), tickets);
    }

    /**
     * Method to retrieve available tickets for a supplied company name.
     * @param companyName a <code>String</code> with the company to retrieve the available tickets for.
     * @return a <code>Tickets</code> object containing the available tickets.
     */
    public Tickets getTicketMapForCompany ( final String companyName ) {
        return companyTicketsMap.get(companyName);
    }

    /**
     * This method calculates the price for a particular ticket and fare structure and quantity for a particular company.
     * @param companyName a <code>String</code> with the company to use for calculating prices.
     * @param ticketType a <code>String</code> with the type of the ticket to calculate e.g. single
     * @param fareStructure a <code>String</code> with the fare structure to calculate e.g. adult
     * @param quantity a <code>String</code> with the number of tickets to be purchased.
     * @return a <code>String</code> containing the amount calculated or NaN if invalid data was entered.
     */
    public String calculatePrice ( final String companyName, final String ticketType, final String fareStructure, final String quantity ) {
        HashMap<String, Ticket> ticketMap = companyTicketsMap.get(companyName).getTicketHashMap();
        if ( !ticketMap.containsKey(ticketType) ) {
            return "NaN";
        }
        Ticket ticket = ticketMap.get(ticketType);
        BigDecimal price = ticket.getPrice(fareStructure);
        if ( price == null ) {
            return "NaN";
        } else {
            double totalPrice = price.doubleValue() * Double.parseDouble(quantity);
            NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.UK);
            String displayPrice = formatter.format(totalPrice);
            return displayPrice;
        }
    }

    /**
     * This method processes a credit card payment. At the moment it just checks that the input is valid and does nothing
     * else.
     * @param creditCardType a <code>String</code> with the type of credit card - usually Visa or Mastercard.
     * @param creditCardNumber a <code>String</code> with the credit card number.
     * @param creditCardExpiryMonth a <code>String</code> with the month that the credit card expires (in English)
     * @param creditCardSecurityCode a <code>int</code> with the security number on the back of the credit card.
     * @return a <code>String</code> with the status of the payment.
     */
    public String processPayment(final String creditCardType, final String creditCardNumber, final String creditCardExpiryMonth, final int creditCardSecurityCode) {
        if ( creditCardType != null && (creditCardType.contentEquals("Visa") || creditCardType.contentEquals("Mastercard") ) ) {
            if ( creditCardNumber.contentEquals("") ) {
                return "Payment unsuccessful - no credit card number was entered!";
            } else {
                if ( creditCardExpiryMonth.contentEquals("") ) {
                    return "Payment unsuccessful - no expiry month given!";
                } else {
                    if ( creditCardSecurityCode <= 0 ) {
                        return "Payment unsuccessful - no security code supplied!";
                    } else {
                        return "Payment successful!";
                    }
                }
            }
        } else {
            return "Payment unsuccessful - please check that correct credit card information was entered";
        }
    }

}
