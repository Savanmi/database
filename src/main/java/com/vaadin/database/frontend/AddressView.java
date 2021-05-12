package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Address;
import com.vaadin.database.data.entity.Subscription_fees;
import com.vaadin.database.data.service.AddressService;
import com.vaadin.database.data.service.Subscription_feesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("addresses")
public class AddressView extends VerticalLayout {

    private AddressService addressService;
    Grid<Address> grid = new Grid<>(Address.class);

    public  AddressView (AddressService addressService){

        this.addressService = addressService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        add(new H3("Данные об адресах"));
        add(grid);
        updatelist();
    }

    private void updatelist() {
        grid.setItems((addressService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

    }
}