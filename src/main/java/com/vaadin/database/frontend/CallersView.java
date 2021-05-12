package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.CallerService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("callers")
public class CallersView extends VerticalLayout {

    private CallerService callerService;
    Grid<Callers> grid = new Grid<>(Callers.class);

    public  CallersView (CallerService callerService){

        this.callerService = callerService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о звонящих"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((callerService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}

