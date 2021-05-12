package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Address;
import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.service.AddressService;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("balances")
public class BalancesView extends VerticalLayout {

    private BalancesService balancesService;
    Grid<Balances> grid = new Grid<>(Balances.class);

    public  BalancesView (BalancesService balancesService){

        this.balancesService = balancesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о балансах пользователей"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((balancesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}
