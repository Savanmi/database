package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Subscription_fees;
import com.vaadin.database.data.service.Subscription_feesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("fee")
public class Subscription_feeView extends VerticalLayout {

    private Subscription_feesService subscription_feesService;
    Grid<Subscription_fees> grid = new Grid<>(Subscription_fees.class);

    public Subscription_feeView(Subscription_feesService subscription_feesService){

        this.subscription_feesService = subscription_feesService;

        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные об абонентской плате"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((subscription_feesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("subscription_fee-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}
