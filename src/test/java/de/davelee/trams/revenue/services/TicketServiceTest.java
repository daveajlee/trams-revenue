package de.davelee.trams.revenue.services;

import de.davelee.trams.revenue.api.Ticket;
import de.davelee.trams.revenue.api.Tickets;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This class tests the TicketService.
 * @author Dave Lee
 */
public class TicketServiceTest {

    /**
     * This method tests the use case:
     * 1. Load a set of available tickets.
     * 2. Retrieve set of available tickets for a company which exists.
     * 3. Retrieve set of available tickets for a company which does not exist.
     * 4. Calculate the price of tickets which exist.
     * 5. Calculate the price of tickets which do not exist.
     */
    @Test
    public void testAvailableTickets() {
        TicketService ticketService = new TicketService();
        //Create a test collection of tickets.
        Tickets tickets = new Tickets();
        tickets.setCompanyName("Test-Company");
        HashMap<String, Ticket> ticketHashMap = new HashMap<>();
        ticketHashMap.put("single", createTestTicket());
        tickets.setTicketHashMap(ticketHashMap);
        //Test case 1: test loading a collection of tickets.
        ticketService.loadTicketMapForCompany(tickets);
        //Test case 2: test retrieving a collection of tickets for a company which exists.
        Tickets retrievedTickets = ticketService.getTicketMapForCompany("Test-Company");
        assertEquals("Test-Company", retrievedTickets.getCompanyName());
        assertTrue(retrievedTickets.getTicketHashMap().containsKey("single"));
        //Test case 3: test retrieving a collection of tickets for a company which does not exist.
        Tickets myRetrievedTickets = ticketService.getTicketMapForCompany("My-Test-Company");
        assertNull(myRetrievedTickets);
        //Test case 4: Calculate the price of tickets which exist.
        assertEquals("£1.60", ticketService.calculatePrice("Test-Company", "single", "adult", "2"));
        //Test case 5: Calculate the price of tickets which do not exist.
        assertEquals("NaN", ticketService.calculatePrice("Test-Company", "return", "adult", "2"));
        assertEquals("NaN", ticketService.calculatePrice("Test-Company", "single", "male", "2"));
    }

    /**
     * This is a helper method which creates a test ticket for test purposes.
     * @return a <code>Ticket</code> object representing a test ticket.
     */
    public Ticket createTestTicket() {
        Ticket ticket = new Ticket();
        ticket.setDescription("Valid for 1 hour");
        ticket.setType("Single Ticket");
        HashMap<String, BigDecimal> priceList = new HashMap<>();
        priceList.put("adult", new BigDecimal("0.80"));
        ticket.setPriceList(priceList);
        return ticket;
    }

    /**
     * This method tests the positive and negative use cases concerning process payment.
     */
    @Test
    public void testProcessPayment() {
        TicketService ticketService = new TicketService();
        //Test case 1: wrong credit card type
        assertEquals("Payment unsuccessful - please check that correct credit card information was entered",
                ticketService.processPayment("American Express", "1234", "January", 123));
        //Test case 2: valid credit card type but no credit card number
        assertEquals("Payment unsuccessful - no credit card number was entered!",
                ticketService.processPayment("Visa", "", "January", 123));
        //Test case 3: valid credit card type and credit card number but no expiry month
        assertEquals("Payment unsuccessful - no expiry month given!",
                ticketService.processPayment("Visa", "1234", "", 123));
        //Test case 4: valid credit card type, credit card number, expiry month but invalid security number
        assertEquals("Payment unsuccessful - no security code supplied!",
                ticketService.processPayment("Visa", "1234", "January", -1));
        //Test case 5: all valid infos with visa card
        assertEquals("Payment successful!",
                ticketService.processPayment("Visa", "1234", "January", 123));
        //Test case 6: all valid infos with mastercard
        assertEquals("Payment successful!",
                ticketService.processPayment("Mastercard", "1234", "January", 123));
    }

}
