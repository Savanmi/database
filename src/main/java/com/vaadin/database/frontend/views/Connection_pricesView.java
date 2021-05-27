package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.entity.Clients;
import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Connection_pricesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.forms.CallerForm;
import com.vaadin.database.frontend.forms.ClientForm;
import com.vaadin.database.frontend.forms.Connection_pricesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="connect_prices", layout = MainView.class)
@PageTitle("connect prices")
public class Connection_pricesView extends VerticalLayout {

    private Connection_pricesService connection_pricesService;
    Grid<Connection_prices> grid = new Grid<>(Connection_prices.class);

    private Connection_pricesForm form;

    public  Connection_pricesView (Connection_pricesService connection_pricesService) {
        this.connection_pricesService = connection_pricesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();


        form = new Connection_pricesForm(connection_pricesService.findAll());
        form.addListener(Connection_pricesForm.SaveEvent.class, this::saveConnection_prices);
        form.addListener(Connection_pricesForm.DeleteEvent.class, this::deleteConnection_prices);
        form.addListener(Connection_pricesForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о ценах на подключение"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {

        Button addConnection_prices = new Button("Добавить цену", click -> addConnection_prices());

        HorizontalLayout toolbar = new HorizontalLayout(addConnection_prices);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addConnection_prices() {
        grid.asSingleSelect().clear();
        editConnection_prices(new Connection_prices());
    }


    private void saveConnection_prices(Connection_pricesForm.SaveEvent evt) {
        connection_pricesService.save(evt.getConnection_prices());
        updatelist();
        closeEditor();
    }

    private  void deleteConnection_prices(Connection_pricesForm.DeleteEvent evt) {
        connection_pricesService.delete(evt.getConnection_prices());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setConnection_prices(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editConnection_prices(Connection_prices connection_prices) {
        if (connection_prices == null) {
            closeEditor();
        } else {
            form.setConnection_prices(connection_prices);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((connection_pricesService.findAll()));
    }


    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editConnection_prices(gridAddressComponentValueChangeEvent.getValue()));

    }
}