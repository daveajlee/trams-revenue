package de.davelee.trams.revenue.admin.forms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.davelee.trams.revenue.admin.pages.RevenueJsonWebPage;
import de.davelee.trams.revenue.api.Ticket;
import de.davelee.trams.revenue.api.Tickets;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * The Export Form implements the ability to export the ticket type configuration as JSON.
 * @param <T> a <code>Class</code> type to use the ExportForm with (usually String).
 * @author Dave Lee
 */
public class ExportForm<T> extends Form<T> {

    private static Logger logger = LoggerFactory.getLogger(ExportForm.class);

    /**
     * Create a new Export Form by supplying a wicket id for the form.
     * @param name a <code>String</code> containing the name to use as the wicket id.
     * @param ticketList a <code>HashMap</code> of <code>Ticket</code> containing the existing list of
     *                       tickets which may be empty but not null.
     */
    public ExportForm ( final String name, final List<Ticket> ticketList ) {

        super ( name );

        add ( new Button("exportJSONButton") {
            @Override
            public void onSubmit() {
                final ObjectMapper mapper = new ObjectMapper();
                //Build tickets object out of ticket list.
                Tickets tickets = new Tickets();
                tickets.setCompanyName(getSession().getAttribute("operator").toString());
                HashMap<String, Ticket> ticketHashMap = new HashMap<String, Ticket>();
                for ( Ticket ticket : ticketList ) {
                    ticketHashMap.put(ticket.getShortId(), ticket);
                }
                tickets.setTicketHashMap(ticketHashMap);
                //Convert the tickets object to json.
                try {
                    String json = mapper.writeValueAsString(tickets);
                    PageParameters params = new PageParameters();
                    params.set("json", json);
                    this.setResponsePage(RevenueJsonWebPage.class, params);
                } catch ( JsonProcessingException jsonProcessingException) {
                    logger.error("Failure converting to json: " + jsonProcessingException);
                }
            }
        });

        add ( new Button("resetButton") {
            @Override
            public void onSubmit() {
                ticketList.clear();
            }
        });

    }

}
