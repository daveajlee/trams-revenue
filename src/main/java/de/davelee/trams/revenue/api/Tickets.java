package de.davelee.trams.revenue.api;

import java.util.HashMap;

/**
 * This class represents a collection of tickets consisting of a company name and a hash map of available tickets.
 * This class can also be used as a JSON configuration.
 * @author Dave Lee
 */
public class Tickets {

    private String companyName;

    private HashMap<String, Ticket> ticketHashMap;

    /**
     * Return the name of the company managing this collection of tickets.
     * @return a <code>String</code> containing the name of the company managing this collection of tickets.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set the name of the company managing this collection of tickets.
     * @param companyName a <code>String</code> containing the name of the company managing this collection of tickets.
     */
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }

    /**
     * Return a copy of each ticket together with an appropriate code contained in this collection of tickets.
     * @return a <code>Hashmap</code> containing the tickets with an appropriate code and the appropriate Ticket object.
     */
    public HashMap<String, Ticket> getTicketHashMap() {
        return ticketHashMap;
    }

    /**
     * Set the collection of tickets containing a copy of each ticket together with an appropriate code.
     * @param ticketHashMap a <code>Hashmap</code> containing the tickets with an appropriate code and the appropriate Ticket object.
     */
    public void setTicketHashMap(final HashMap<String, Ticket> ticketHashMap) {
        this.ticketHashMap = ticketHashMap;
    }
}
