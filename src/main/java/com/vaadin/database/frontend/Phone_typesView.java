package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Phone_types;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Phone_numbersService;
import com.vaadin.database.data.service.Phone_typesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("phones_types")
public class Phone_typesView extends VerticalLayout {

    private Phone_typesService phone_typesService;
    Grid<Phone_types> grid = new Grid<>(Phone_types.class);

    public  Phone_typesView (Phone_typesService phone_typesService){

        this.phone_typesService = phone_typesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о типах телефонов"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((phone_typesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}

