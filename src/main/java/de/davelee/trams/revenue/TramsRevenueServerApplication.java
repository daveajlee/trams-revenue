package de.davelee.trams.revenue;

import com.google.common.base.Predicate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * This class represents the Spring Boot application for TraMS Revenue Management.
 * @author Dave Lee
 */
@SpringBootApplication
@EnableSwagger2
@Configuration
@ComponentScan()
@EnableAutoConfiguration
/**
 * This class represents the Spring Boot application for TraMS revenue management.
 */
public class TramsRevenueServerApplication {

    /**
     * Main method to start the application.
     * @param args a <code>String</code> array of arguments which are not presently used.
     */
    public static void main ( String[] args ) {
        new SpringApplicationBuilder()
                .sources(TramsRevenueServerApplication.class)
                .run(args);
    }

    @Bean
    /**
     * Configure Swagger to display appropriate information.
     * @return a <code>Docket</code> object containing the configured information.
     */
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("operations")
                .apiInfo(apiInfo())
                .select()
                .paths(paths())
                .build();
    }

    /**
     * Only map urls starting with revenue.
     * @return a <code>Predicate</code> object containing the configuration of limited urls.
     */
    private Predicate<String> paths() {
        return or (
                regex("/revenue.*")
        );
    }

    /**
     * Return an API Info object with the configured information for the Swagger UI.
     * @return a <code>ApiInfo</code> object with the configured information for the Swagger UI.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TraMS Revenue Rest API")
                .description("Rest API for TraMS Revenue")
                .termsOfServiceUrl("http://www.davelee.de")
                .contact("Dave Lee")
                .license("TraMS Website")
                .licenseUrl("http://www.davelee.de/")
                .version("0.1")
                .build();
    }

}
