package de.davelee.trams.revenue.admin.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.google.common.collect.Lists;
import de.davelee.trams.revenue.admin.CategoryPrice;
import de.davelee.trams.revenue.admin.forms.AddTicketTypeForm;
import de.davelee.trams.revenue.admin.forms.ExportForm;
import de.davelee.trams.revenue.api.Ticket;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import java.util.*;

@WicketHomePage
@AuthorizeInstantiation("ADMIN")
/**
 * This class represents the Tickets and Prices Page in the Wicket Admin Application.
 * @author Dave Lee
 */
public class RevenueTicketsAndPricesPage extends WebPage {

    private List<Ticket> ticketList;

    private List<CategoryPrice> categoryPriceList = Lists.newArrayList();

    /**
     * Create a new Tickets and Prices page with all components including the form to display to the user.
     * Default constructor without any parameters.
     */
    public RevenueTicketsAndPricesPage() {
        this(new ArrayList<>());
    }

    /**
     * Create a new Tickets and Prices page with all components including the form to display to the user.
     * @param myTicketList a <code>List</code> of <code>Ticket</code> objects to display already on the page.
     */
    public RevenueTicketsAndPricesPage(final List<Ticket> myTicketList) {

        ticketList = myTicketList;

        add(new Label("title", "Revenue Administration"));

        add(new Label("pageTitle", "Tickets and Prices"));

        add(new Label("userOperator", getSession().getAttribute("displayName") + ", " + getSession().getAttribute("operator")));

        add(new AddTicketTypeForm("typeForm", ticketList, categoryPriceList));

        add(new ListView<Ticket>("ticketList", new PropertyModel<>(this, "ticketList")) {

            @Override
            protected void populateItem(ListItem<Ticket> item) {
                item.add(new Label("shortId", item.getModelObject().getShortId()));
                item.add(new Label("type", item.getModelObject().getType()));
            }
        });

        add(new ExportForm("exportForm", ticketList));
    }

    /**
     * Return the current list of tickets which are currently defined in the form.
     * @return a <code>List</code> of <code>Ticket</code> objects containing the current list of tickets.
     */
    public List<Ticket> getTicketList() {
        return ticketList;
    }

}