package de.davelee.trams.revenue.admin.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import de.davelee.trams.revenue.admin.forms.LoginForm;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@WicketSignInPage
/**
 * This class represents the Login Page in the Wicket Admin Application.
 * @author Dave Lee
 */
public class RevenueLoginPage extends WebPage {

    /**
     * Create a new LoginPage with some PageParameters which are currently ignored. A login page
     * consists of a title label and a login form.
     * @param pageParameters a <code>PageParameters</code> object which is currently ignored but required by Wicket.
     */
    public RevenueLoginPage(final PageParameters pageParameters) {
        super(pageParameters);

        if (((AbstractAuthenticatedWebSession) getSession()).isSignedIn()) {
            continueToOriginalDestination();
        }

        add(new Label("title", "Revenue Administration"));

        add(new LoginForm("loginForm"));
    }

}