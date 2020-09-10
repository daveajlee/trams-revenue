package de.davelee.trams.revenue.admin.forms;

import de.davelee.trams.revenue.admin.CategoryPrice;
import de.davelee.trams.revenue.admin.pages.RevenueTicketsAndPricesPage;
import de.davelee.trams.revenue.api.Ticket;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * This class implements the form to add a new ticket type in the Wicket interface.
 * @param <T> a <code>Class</code> type to use the AddTicketTypeForm with (usually String).
 * @author Dave Lee
 */
public class AddTicketTypeForm<T> extends Form<T> {

    private TextField<String> shortIdField;
    private TextField<String> typeField;
    private TextArea<String> descriptionArea;
    private NumberTextField<Integer> displayPositionField;
    private List<CategoryPrice> categoryPrices;

    /**
     * Create a new Add Ticket Type Form by supplying an id and an existing list of ticket types which can be empty.
     * @param name a <code>String</code> containing the name to use as the wicket id.
     * @param ticketList a <code>List</code> of <code>Ticket</code> containing the existing list of
     *                       ticket types which may be empty but not null.
     * @param categoryPriceList a <code>List</code> of <code>CategoryPrice</code> containing the existing list of
     *                             categories and prices which may be empty but not null.
     */
    public AddTicketTypeForm (final String name, final List<Ticket> ticketList, final List<CategoryPrice> categoryPriceList) {

        super(name);
        this.categoryPrices = categoryPriceList;

        add(new Label("shortIdLabel", "Short Id:"));

        shortIdField = new TextField<String>("shortIdField", new Model<String>(""));
        add(shortIdField.setRequired(true));

        add(new Label("typeLabel", "Type:"));

        typeField = new TextField<String>("typeField", new Model<String>(""));
        add(typeField.setRequired(true));

        add(new Label("descriptionLabel", "Description:"));

        descriptionArea = new TextArea<String>("descriptionArea", new Model<String>(""));
        add(descriptionArea.setRequired(true));

        add(new Label("displayPositionLabel", "Display Position:"));

        displayPositionField = new NumberTextField<Integer>("displayPositionField", new Model<>(0), Integer.class);
        displayPositionField.setMinimum(0);
        add(displayPositionField.setRequired(true));

        add(new AddPriceForm("priceForm", categoryPriceList));

        add(new ListView<CategoryPrice>("categoryPriceList", new PropertyModel<>(this, "categoryPrices")) {

            @Override
            protected void populateItem(ListItem<CategoryPrice> item) {
                item.add(new Label("category", new PropertyModel(item.getModel(), "category")));
                item.add(new Label("price", new PropertyModel<>(item.getModel(), "price")));
            }

        });

        add(new Button("addTicketButton") {
            @Override
            /**
             * Method to perform form submission and form related processing i.e. add the new ticket type to the
             * list of existing ticket types.
             */
            public void onSubmit() {
                //Get the short id.
                String shortId = shortIdField.getModel().getObject();
                //Create the ticket.
                Ticket ticket = new Ticket();
                ticket.setType(typeField.getModel().getObject());
                ticket.setDescription(descriptionArea.getModelObject());
                ticket.setShortId(shortId);
                ticket.setSortOrder(displayPositionField.getModelObject());
                //Create price map based on category price list.
                HashMap<String, BigDecimal> priceList = new HashMap<String, BigDecimal>();
                for ( CategoryPrice categoryPrice : categoryPriceList ) {
                    priceList.put(categoryPrice.getCategory(), categoryPrice.getPrice());
                }
                ticket.setPriceList(priceList);
                //Store the ticket in the ticket map.
                ticketList.add(ticket);
                //Clear form.
                this.setResponsePage(new RevenueTicketsAndPricesPage(ticketList));
            }
        });

    }

}
