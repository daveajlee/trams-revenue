package de.davelee.trams.revenue.admin.forms;

import de.davelee.trams.revenue.admin.CategoryPrice;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * This class implements the form to add price entries (categories and prices) in Wicket.
 * @param <T> a <code>Class</code> type to use the AddPriceForm with (usually String).
 * @author Dave Lee
 */
public class AddPriceForm<T> extends Form<T> {

    private List categories = Arrays.asList(new String[] { "adult", "student", "concession"});
    private String selectedOperator = "adult";

    private DropDownChoice<String> categorySelect;
    private NumberTextField<Double> priceField;

    private List<CategoryPrice> categoryPriceList;

    /**
     * Create a new Add Price Form with a wicket id and a list of categories and prices.
     * @param name a <code>String</code> containing the name of the form to use as Wicket Id.
     * @param categoryPriceList a <code>List</code> of <code>CategoryPrice</code> containing the categories and prices.
     */
    public AddPriceForm (final String name, final List<CategoryPrice> categoryPriceList) {
        super(name);

        this.categoryPriceList = categoryPriceList;

        add(new Label("categoryLabel", "Category:"));

        categorySelect = new DropDownChoice<String>("categorySelect", new PropertyModel(this, "selectedOperator"), categories);
        add(categorySelect);

        add(new Label("priceLabel", "Price:"));

        priceField = new NumberTextField("priceField", new Model<>(1.55));
        priceField.setMinimum(0.00);
        priceField.setStep(0.01);
        add(priceField.setRequired(true));

        add(new Button("addPriceButton") {
            @Override
            /**
             * Method to submit the form and do form relevant processing i.e. add the new price and category to the
             * existing list.
             */
            public void onSubmit() {
                String category = categorySelect.getModel().getObject();
                BigDecimal price = new BigDecimal(priceField.getModelObject().toString());
                categoryPriceList.add(new CategoryPrice(category, price));
            }
        });

        add(new Button("clearPricesButton") {
            public void onSubmit() {
                categoryPriceList.clear();
            }
        });

    }

}
