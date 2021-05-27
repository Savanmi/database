package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Address;
import com.vaadin.database.data.service.AddressService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.forms.AddressForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="addresses", layout = MainView.class)
@PageTitle("addresses")
public class AddressView extends VerticalLayout {

    private AddressService addressService;
    Grid<Address> grid = new Grid<>(Address.class);
    TextField filterText = new TextField();

    private AddressForm form;

    public  AddressView (AddressService addressService){

        this.addressService = addressService;
        addClassName("list-view");
        setSizeFull();

        configGrid();


        form = new AddressForm(addressService.findAll());
        form.addListener(AddressForm.SaveEvent.class, this::saveAddress);
        form.addListener(AddressForm.DeleteEvent.class, this::deleteAddress);
        form.addListener(AddressForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H2("Данные об адресах"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Фильтр по адресу");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updatelist());

        Button addAddress = new Button("Добавить адрес", click -> addAddress());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addAddress);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addAddress() {
        grid.asSingleSelect().clear();
        editAddress(new Address());
    }


    private void saveAddress(AddressForm.SaveEvent evt) {
        addressService.save(evt.getAddress());
        updatelist();
        closeEditor();
    }

    private  void deleteAddress(AddressForm.DeleteEvent evt) {
        addressService.delete(evt.getAddress());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setAddress(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editAddress(Address address) {
        if (address == null) {
            closeEditor();
        } else {
            form.setAddress(address);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((addressService.findAll(filterText.getValue())));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editAddress(gridAddressComponentValueChangeEvent.getValue()));

    }


}