package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Long_distance_call_prices;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Long_distance_call_pricesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ldcall_prices")
public class Long_distance_call_pricesView extends VerticalLayout {

    private Long_distance_call_pricesService long_distance_call_pricesService;
    Grid<Long_distance_call_prices> grid = new Grid<>(Long_distance_call_prices.class);

    public  Long_distance_call_pricesView (Long_distance_call_pricesService long_distance_call_pricesService){

        this.long_distance_call_pricesService = long_distance_call_pricesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные тарифах на междугородние звонки"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((long_distance_call_pricesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}
