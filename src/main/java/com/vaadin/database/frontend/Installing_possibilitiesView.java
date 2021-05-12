package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Installing_possibilities;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Installing_possibilitiesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("installing")
public class Installing_possibilitiesView extends VerticalLayout {

    private Installing_possibilitiesService installing_possibilitiesService;
    Grid<Installing_possibilities> grid = new Grid<>(Installing_possibilities.class);

    public  Installing_possibilitiesView (Installing_possibilitiesService installing_possibilitiesService){

        this.installing_possibilitiesService = installing_possibilitiesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные о возможностях установки"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((installing_possibilitiesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}
