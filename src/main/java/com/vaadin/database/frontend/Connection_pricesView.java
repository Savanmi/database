package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Connection_pricesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("connect_prices")
public class Connection_pricesView extends VerticalLayout {

    private Connection_pricesService connection_pricesService;
    Grid<Connection_prices> grid = new Grid<>(Connection_prices.class);

    public  Connection_pricesView (Connection_pricesService connection_pricesService){

        this.connection_pricesService = connection_pricesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о тарифах на подключение"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((connection_pricesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}

