package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Phone_numbersService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("numbers")
public class Phone_numbersView extends VerticalLayout {

    private Phone_numbersService phone_numbersService;
    Grid<Phone_numbers> grid = new Grid<>(Phone_numbers.class);

    public  Phone_numbersView (Phone_numbersService phone_numbersService){

        this.phone_numbersService = phone_numbersService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о номерах телефонов"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((phone_numbersService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}

