package de.davelee.trams.revenue.rest.controllers;

import de.davelee.trams.revenue.api.CalculatePriceRequest;
import de.davelee.trams.revenue.api.CreditCardPaymentRequest;
import de.davelee.trams.revenue.api.Tickets;
import de.davelee.trams.revenue.services.TicketService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="revenue", description="Revenue Operations")
@RequestMapping(value="/revenue")
/**
 * This class contains the REST endpoints for revenue operations.
 * @author Dave Lee
 */
public class RevenueOperationsRestController {

    @Autowired
    private TicketService ticketService;

    @ApiOperation(value = "Add a collection of available tickets", notes="Method to add available tickets")
    @RequestMapping(method = RequestMethod.POST, value="/availableTickets")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successful operation")})
    /**
     * Save the supplied tickets into the system and return a 200 code to indicate that the tickets
     * were added successfully.
     * @param tickets a <code>Tickets</code> object containing the tickets to be added to the system.
     * @return a <code>ResponseEntity</code> object with the appropriate http status code.
     */
    public ResponseEntity<Void> addAvailableTickets (@RequestBody final Tickets tickets ) {
        ticketService.loadTicketMapForCompany(tickets);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "Get a collection of available tickets for a specified company", notes="Method to get available tickets")
    @RequestMapping(method = RequestMethod.GET, value="/availableTickets")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successful operation",response=Tickets.class),@ApiResponse(code=204,message="Successful operation but nothing found")})
    /**
     * Retrieve the available tickets for a particular company. Http status code is as follows:
     * 200 - successful and data retrieved. 204 - successful but no data found.
     * @param companyName a <code>String</code> containing the name of the company to retrieve tickets for.
     * @return a <code>ResponseEntity</code> object with the appropriate http status code.
     */
    public ResponseEntity<Tickets> getAvailableTickets (@ApiParam(name= "companyName", value="The name of the company", required=true) @RequestParam(value="companyName") final String companyName ) {
        Tickets tickets = ticketService.getTicketMapForCompany(companyName);
        if ( tickets == null ) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @ApiOperation(value = "Calculate the price of tickets", notes="Method to calculate the price of tickets")
    @RequestMapping(method = RequestMethod.POST, value="/calculatePrice")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successful operation")})
    /**
     * Calculate the price based on the supplied request and return the amount in the response.
     * @param calculatePriceRequest a <code>CalculatePriceRequest</code> object containing the request to calculate.
     * @return a <code>ResponseEntity</code> object containing the status code 200 and the amount calculated.
     */
    public ResponseEntity<String> calculatePrice (@RequestBody final CalculatePriceRequest calculatePriceRequest ) {
        String calculatedPrice = ticketService.calculatePrice(calculatePriceRequest.getCompanyName(), calculatePriceRequest.getTicketType(),
                calculatePriceRequest.getFareStructure(), calculatePriceRequest.getQuantity());
        return ResponseEntity.ok(calculatedPrice);
    }

    @ApiOperation(value = "Process payment for tickets", notes="Method to process the credit card payment of tickets")
    @RequestMapping(method = RequestMethod.POST, value="/creditCardPay")
    @ApiResponses(value = {@ApiResponse(code=200,message="Successful operation")})
    /**
     * Process the credit card payment. Since credit card payment is currently not configured, this method
     * will simply return a 200 without deducting any money from the credit card.
     * @param creditCardPaymentRequest a <code>CreditCardPaymentRequest</code> object containing the transaction to be paid.
     * @return a <code>ResponseEntity</code> object containing the status code 200 indicating that payment was successful (
     * although no money will be withdrawn).
     */
    public ResponseEntity<String> processCreditCardPayment (@RequestBody final CreditCardPaymentRequest creditCardPaymentRequest ) {
        String paymentResponse = ticketService.processPayment(creditCardPaymentRequest.getType(), creditCardPaymentRequest.getNumber(),
                creditCardPaymentRequest.getExpiryMonth(), creditCardPaymentRequest.getSecurityCode());
        return ResponseEntity.ok(paymentResponse);
    }

}
