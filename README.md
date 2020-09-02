# trams-revenue

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a17b1a1340bc4a7da3226c10425fab6a)](https://app.codacy.com/manual/dave_33/trams-revenue?utm_source=github.com&utm_medium=referral&utm_content=daveajlee/trams-revenue&utm_campaign=Badge_Grade_Dashboard)

This microservice for the TraMS platform manages revenue operations through REST and allows export of information as JSON through a web client.

##How to use

1.  To use the microservice you need to specify the user specific configuration parameters in application.properties and application-production.properties.
2.  Create an executable jar using mvn clean install.
3.  Run the jar (for example in production mode): java -Dspring.profiles.active=production -jar trams-revenue.jar

###How to use the integrated Admin Client and Swagger

The microservice comes with an integrated admin client which allows you to define available ticket types and export it as JSON. This admin interface is available at https://your-domain/trams-revenue/admin/ The initial username and password is admin/admin.

You can use trams-revenue through any language by calling the REST interface which is documented by Swagger and reachable through this URL:  https://your-domain/trams-revenue/swagger-ui.html

###Available Profiles
*   local - This profile uses an in-memory database but does not use Eureka for service discovery. This works well for local development.
*   dev - This profile uses an in-memory database and Eureka for service discovery. This works well for development and testing.
*   production - This profile uses Eureka for service discovery and can be configured to use a database.

####Disclaimer

Please note that the microservice only contains a dummy implementation for credit card processing and nothing will be charged from any credit card entered. In addition, data is only stored on the computer where this microservice is used and whoever uses this microservice is responsible for ensuring that this data processing occurs within the laws of the country where the microservice is being used.
