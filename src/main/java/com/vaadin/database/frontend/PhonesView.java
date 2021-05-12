package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Phones;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.PhonesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("phones")
public class PhonesView extends VerticalLayout {

    private PhonesService phonesService;
    Grid<Phones> grid = new Grid<>(Phones.class);

    public  PhonesView (PhonesService phonesService){

        this.phonesService = phonesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о телефонах"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((phonesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}

