package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Long_distance_calls;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Long_distance_callsService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ldcalls")
public class Long_distance_callsView extends VerticalLayout {

    private Long_distance_callsService long_distance_callsService;
    Grid<Long_distance_calls> grid = new Grid<>(Long_distance_calls.class);

    public  Long_distance_callsView (Long_distance_callsService long_distance_callsService){

        this.long_distance_callsService = long_distance_callsService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о междугородних звонках"));
        updatelist();
    }

    private void updatelist() {
        grid.setItems((long_distance_callsService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}

