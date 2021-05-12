package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Public_phones;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Public_phonesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("public_phones")
public class Public_phonesView extends VerticalLayout {

    private Public_phonesService public_phonesService;
    Grid<Public_phones> grid = new Grid<>(Public_phones.class);

    public  Public_phonesView (Public_phonesService public_phonesService){

        this.public_phonesService = public_phonesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные об общественных телефонах"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((public_phonesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}
