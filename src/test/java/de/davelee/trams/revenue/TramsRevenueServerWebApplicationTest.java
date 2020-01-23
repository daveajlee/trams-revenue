package de.davelee.trams.revenue;

import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=TramsRevenueServerApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
/**
 * Test the Spring Boot application to make sure it starts and swagger can be called.
 * @author Dave Lee
 */
public class TramsRevenueServerWebApplicationTest {

    @LocalServerPort
    int port;

    @Before
    /**
     * Set up the test by setting the port correctly.
     */
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    /**
     * Test case: make sure that the swagger can be called.
     * Expected result: 200 status code OK.
     */
    public void testSwagger() {
        when().
                get("/swagger-ui.html").
                then().
                statusCode(HttpStatus.SC_OK);
    }

}