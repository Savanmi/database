package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Connection_requests;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Connection_requestsService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("requests")
public class Connection_requestsView extends VerticalLayout {

    private Connection_requestsService connection_requestsService;
    Grid<Connection_requests> grid = new Grid<>(Connection_requests.class);

    public  Connection_requestsView (Connection_requestsService connection_requestsService){

        this.connection_requestsService = connection_requestsService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о запросах на подключение"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((connection_requestsService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}

