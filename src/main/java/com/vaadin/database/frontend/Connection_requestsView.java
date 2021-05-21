package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Connection_requests;
import com.vaadin.database.data.service.*;
import com.vaadin.database.frontend.forms.Connection_pricesForm;
import com.vaadin.database.frontend.forms.Connection_requestsForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("requests")
public class Connection_requestsView extends VerticalLayout {

    private Connection_requestsService connection_requestsService;
    private AddressService addressService;
    private Telephone_exchangesService telephone_exchangeService;
    private ClientsService clientsService;

    Grid<Connection_requests> grid = new Grid<>(Connection_requests.class);

    private Connection_requestsForm form;


    public  Connection_requestsView (Connection_requestsService connection_requestsService, AddressService addressService, Telephone_exchangesService telephone_exchangeService, ClientsService clientsService){

        this.addressService = addressService;
        this.clientsService = clientsService;
        this.telephone_exchangeService = telephone_exchangeService;
        this.connection_requestsService = connection_requestsService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new Connection_requestsForm(addressService.findAll(), clientsService.findAll(), telephone_exchangeService.findAll());
        form.addListener(Connection_requestsForm.SaveEvent.class, this::saveConnection_requests);
        form.addListener(Connection_requestsForm.DeleteEvent.class, this::deleteConnection_requests);
        form.addListener(Connection_requestsForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о запросах на подключение"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();

    }


    private HorizontalLayout getToolBar() {

        Button addConnection_requests = new Button("Добавить клиента", click -> addConnection_requests());

        HorizontalLayout toolbar = new HorizontalLayout(addConnection_requests);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addConnection_requests() {
        grid.asSingleSelect().clear();
        editConnection_requests(new Connection_requests());
    }


    private void saveConnection_requests(Connection_requestsForm.SaveEvent evt) {
        connection_requestsService.save(evt.getConnection_requests());
        updatelist();
        closeEditor();
    }

    private  void deleteConnection_requests(Connection_requestsForm.DeleteEvent evt) {
        connection_requestsService.delete(evt.getConnection_requests());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setConnection_requests(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editConnection_requests(Connection_requests connection_requests) {
        if (connection_requests == null) {
            closeEditor();
        } else {
            form.setConnection_requests(connection_requests);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((connection_requestsService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editConnection_requests(gridAddressComponentValueChangeEvent.getValue()));

    }
}

