package de.davelee.trams.revenue.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.davelee.trams.revenue.api.CalculatePriceRequest;
import de.davelee.trams.revenue.api.CreditCardPaymentRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class tests the RevenueOperationsRestController and indirectly the TicketService.
 * @author Dave Lee
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
public class RevenueOperationsRestControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(RevenueOperationsRestControllerTest.class);

    /**
     * Configure the mock application context.
     */
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * This method tests the use case:
     * 1. Load a set of available tickets.
     * 2. Retrieve set of available tickets for a company which exists.
     * 3. Retrieve set of available tickets for a company which does not exist.
     */
    @Test
    public void testAvailableTickets() {
        String jsonText="{\"companyName\":\"Test-company\",\"ticketHashMap\":{\"single\":{\"type\":\"Single Trip\",\"description\":\"1 hour travel on the network.\",\"priceList\":{\"concession\":0.75,\"student\":1.00,\"adult\":1.50}}}}";
        try {
            assertNotNull(mockMvc.perform(post("/revenue/availableTickets").contentType(MediaType.APPLICATION_JSON).content(jsonText)).andExpect(status().isOk()));
            mockMvc.perform(get("/revenue/availableTickets?companyName=Test-company")).andExpect(status().isOk());
            mockMvc.perform(get("/revenue/availableTickets?companyName=My-Test-company")).andExpect(status().isNoContent());
            mockMvc.perform(post("/revenue/calculatePrice").contentType(MediaType.APPLICATION_JSON).content(asJsonString(testCalculatePriceRequest()))).andExpect(status().isOk());
            mockMvc.perform(post("/revenue/creditCardPay").contentType(MediaType.APPLICATION_JSON).content(asJsonString(testCreditCardPaymentRequest()))).andExpect(status().isOk());
        } catch (Exception exception) {
            logger.error("An exception occurred whilst attempting to load available tickets", exception);
        }
    }

    /**
     * This method creates a test request for calculating prices.
     * @return a <code>CalculatePriceRequest</code> object representing a test request.
     */
    public CalculatePriceRequest testCalculatePriceRequest ( ) {
        CalculatePriceRequest calculatePriceRequest = new CalculatePriceRequest();
        calculatePriceRequest.setCompanyName("Test-company");
        calculatePriceRequest.setTicketType("single");
        calculatePriceRequest.setFareStructure("adult");
        calculatePriceRequest.setQuantity("2");
        return calculatePriceRequest;
    }

    /**
     * This method creates a test request for paying by credit card.
     * @return a <code>CreditCardPaymentRequest</code> object representing a test request.
     */
    public CreditCardPaymentRequest testCreditCardPaymentRequest ( ) {
        CreditCardPaymentRequest creditCardPaymentRequest = new CreditCardPaymentRequest();
        creditCardPaymentRequest.setCompanyName("Test-company");
        creditCardPaymentRequest.setType("Visa");
        creditCardPaymentRequest.setNumber("1234");
        creditCardPaymentRequest.setExpiryMonth("January");
        creditCardPaymentRequest.setSecurityCode(123);
        return creditCardPaymentRequest;
    }

    /**
     * This method converts the supplied object into a JSON string. Any errors will be logged.
     * @param object a <code>Object</code> to be converted into JSON
     * @return a <code>String</code> with the JSON representation of this object or null if an error occurred.
     */
    public static String asJsonString(final Object object) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(object);
            return jsonContent;
        } catch (Exception exception) {
            logger.error("An exception occurred whilst translating object into json", exception);
            return null;
        }
    }

}