package de.davelee.trams.revenue.config;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import de.davelee.trams.revenue.admin.pages.RevenueJsonWebPage;
import de.davelee.trams.revenue.admin.pages.RevenueLoginPage;
import de.davelee.trams.revenue.admin.pages.RevenueTicketsAndPricesPage;
import org.apache.wicket.protocol.http.WebApplication;

@ApplicationInitExtension
/**
 * This class extends the Wicket web application with further configuration including the mounting of pages.
 * @author Dave Lee
 */
public class WicketPageConfiguration implements WicketApplicationInitConfiguration {

    @Override
    /**
     * Mount pages to the supplied Wicket web application.
     * @param webApplication a <code>WebApplication</code> representing the web application to configure.
     */
    public void init(final WebApplication webApplication) {
        webApplication.mountPage("/login", RevenueLoginPage.class);
        webApplication.mountPage("/ticketsAndPrices", RevenueTicketsAndPricesPage.class);
        webApplication.mountPage("/json", RevenueJsonWebPage.class);
    }

}
