package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Clients;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.ClientsService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("clients")
public class ClientsView extends VerticalLayout {

    private ClientsService clientsService;
    Grid<Clients> grid = new Grid<>(Clients.class);

   TextField filterText = new TextField();
   

    public  ClientsView (ClientsService clientsService){

        this.clientsService = clientsService;
        addClassName("list-view");
        setSizeFull();

        configGrid();
        configFilter();

        add(new H3("Данные о клиентах"));
        add(filterText, grid);
        updatelist();
    }

    private void configFilter() {
        filterText.setPlaceholder("Фильтр по ФИО или полу");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updatelist());
    }

    private void updatelist() {
        grid.setItems((clientsService.findAll(filterText.getValue())));
    }


    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}