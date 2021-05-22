package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Phone_types;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Phone_numbersService;
import com.vaadin.database.data.service.Phone_typesService;
import com.vaadin.database.data.service.PhonesService;
import com.vaadin.database.frontend.forms.Phone_numbersForm;
import com.vaadin.database.frontend.forms.Phone_typesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("phones_types")
public class Phone_typesView extends VerticalLayout {

    private Phone_typesService phone_typesService;

    Grid<Phone_types> grid = new Grid<>(Phone_types.class);

    Phone_typesForm form;

    public  Phone_typesView (Phone_typesService phone_typesService, PhonesService phonesService){

        this.phone_typesService = phone_typesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new Phone_typesForm(phonesService.findAll());
        form.addListener(Phone_typesForm.SaveEvent.class, this::savePhone_types);
        form.addListener(Phone_typesForm.DeleteEvent.class, this::deletePhone_types);
        form.addListener(Phone_typesForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о типах телефонов"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {

        Button addPhone_types = new Button("Добавить тип телефона", click -> addPhone_types());

        HorizontalLayout toolbar = new HorizontalLayout(addPhone_types);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addPhone_types() {
        grid.asSingleSelect().clear();
        editPhone_types(new Phone_types());
    }


    private void savePhone_types(Phone_typesForm.SaveEvent evt) {
        phone_typesService.save(evt.getPhone_types());
        updatelist();
        closeEditor();
    }

    private  void deletePhone_types(Phone_typesForm.DeleteEvent evt) {
        phone_typesService.delete(evt.getPhone_types());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setPhone_types(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editPhone_types(Phone_types phone_types) {
        if (phone_types == null) {
            closeEditor();
        } else {
            form.setPhone_types(phone_types);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }
    private void updatelist() {
        grid.setItems((phone_typesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editPhone_types(gridAddressComponentValueChangeEvent.getValue()));

    }
}

