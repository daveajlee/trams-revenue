package de.davelee.trams.revenue.admin.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * This page prints out the generated json to the browser.
 * @author Dave Lee
 */
public class RevenueJsonWebPage extends WebPage {

    /**
     * Print out the supplied JSON in the page parameters to the browser.
     * @param pageParameters a <code>PageParameters</code> object containing the json to be displayed.
     */
    public RevenueJsonWebPage(final PageParameters pageParameters) {
        getRequestCycle().scheduleRequestHandlerAfterCurrent(new TextRequestHandler("application/json", "utf-8", pageParameters.get("json").toString()));
    }

}
