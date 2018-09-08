package de.davelee.trams.revenue.admin.forms;

import de.davelee.trams.revenue.admin.pages.RevenueTicketsAndPricesPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * The login form allows the user to log in to the admin interface.
 * @param <T> a <code>Class</code> type to use the LoginForm with (usually String).
 * @author Dave Lee
 */
public class LoginForm<T> extends Form<T> {

    private List operators = Arrays.asList(new String[] { "Sample Operator", "Another Sample Operator"});
    private String selectedOperator = "Sample Operator";

    private DropDownChoice<String> operatorSelect;
    private TextField<String> usernameField;
    private TextField<String> passwordField;

    private static Logger logger = LoggerFactory.getLogger(LoginForm.class);

    /**
     * Create a new login form by specifying a wicket id.
     * @param name a <code>String</code> containing the wicket id.
     */
    public LoginForm(final String name) {
        super(name);

        add(new FeedbackPanel("feedback"));

        add(new Label("operatorLabel", "Operator:"));

        operatorSelect = new DropDownChoice<String>("operatorSelect", new PropertyModel(this, "selectedOperator"), operators);
        add(operatorSelect);

        add(new Label("usernameLabel", "Username:"));

        usernameField = new TextField<String>("usernameField", new Model<String>(""));
        add(usernameField.setRequired(true));

        add(new Label("passwordLabel", "Password:"));

        passwordField = new PasswordTextField("passwordField", new Model<String>(""));
        add(passwordField.setRequired(true));

        add(new Button("loginButton"));
    }

    @Override
    /**
     * Attempt to authenticate the user based on the supplied credentials. If successful, redirect them to
     * the start page of the admin page. If unsuccessful (i.e. credentials were not right), log the attempt.
     */
    public void onSubmit() {
        AuthenticatedWebSession session = AuthenticatedWebSession.get();
        if (session.signIn(usernameField.getModelObject(), passwordField.getModelObject())) {
            getSession().setAttribute("displayName", "Max Mustermann");
            getSession().setAttribute("operator", operatorSelect.getModel().getObject());
            setResponsePage(RevenueTicketsAndPricesPage.class);
        } else {
            error("Login failed");
        }
    }

}
