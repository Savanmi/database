package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Long_distance_calls;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Phone_numbersService;
import com.vaadin.database.data.service.PhonesService;
import com.vaadin.database.data.service.Telephone_exchangesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.database.frontend.forms.LdcForm;
import com.vaadin.database.frontend.forms.Phone_numbersForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="numbers", layout = QueryView.class)
@PageTitle("phone numbers")
public class Phone_numbersView extends VerticalLayout {

    private Phone_numbersService phone_numbersService;
    Grid<Phone_numbers> grid = new Grid<>(Phone_numbers.class);

    Phone_numbersForm form;

    public  Phone_numbersView (Phone_numbersService phone_numbersService, PhonesService phonesService, Telephone_exchangesService telephone_exchangesService){

        this.phone_numbersService = phone_numbersService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new Phone_numbersForm(phonesService.findAll(), telephone_exchangesService.findAll());
        form.addListener(Phone_numbersForm.SaveEvent.class, this::savePhone_numbers);
        form.addListener(Phone_numbersForm.DeleteEvent.class, this::deletePhone_numbers);
        form.addListener(Phone_numbersForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о номерах телефонов"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {

        Button addPhone_numbers = new Button("Добавить ноемр телефона", click -> addPhone_numbers());

        HorizontalLayout toolbar = new HorizontalLayout(addPhone_numbers);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addPhone_numbers() {
        grid.asSingleSelect().clear();
        editPhone_numbers(new Phone_numbers());
    }


    private void savePhone_numbers(Phone_numbersForm.SaveEvent evt) {
        phone_numbersService.save(evt.getPhone_numbers());
        updatelist();
        closeEditor();
    }

    private  void deletePhone_numbers(Phone_numbersForm.DeleteEvent evt) {
        phone_numbersService.delete(evt.getPhone_numbers());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setPhone_numbers(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editPhone_numbers(Phone_numbers phone_numbers) {
        if (phone_numbers == null) {
            closeEditor();
        } else {
            form.setPhone_numbers(phone_numbers);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((phone_numbersService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.setColumns("phone_number_ID","phone_number","telephone_exchange_id");

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editPhone_numbers(gridAddressComponentValueChangeEvent.getValue()));

    }
}

