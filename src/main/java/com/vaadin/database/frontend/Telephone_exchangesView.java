package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.database.data.service.Telephone_exchangesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route ("tex")
public class Telephone_exchangesView extends VerticalLayout {

    private Telephone_exchangesService telephone_exchangesService;
    Grid<Telephone_exchanges> grid = new Grid<>(Telephone_exchanges.class);

    public Telephone_exchangesView(Telephone_exchangesService telephone_exchangesService){
        this.telephone_exchangesService = telephone_exchangesService;

        addClassName("list-view");
        setSizeFull();

        configGrid();
        add(new H3("Данные об АТС"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((telephone_exchangesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("subscription_fee-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(telephone_exchangesColumn -> telephone_exchangesColumn.setAutoWidth(true));
    }
}
