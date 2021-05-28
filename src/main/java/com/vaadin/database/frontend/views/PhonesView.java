package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Phone_types;
import com.vaadin.database.data.entity.Phones;
import com.vaadin.database.data.service.*;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.database.frontend.forms.Phone_numbersForm;
import com.vaadin.database.frontend.forms.PhonesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="phones", layout = QueryView.class)
@PageTitle("phones")
public class PhonesView extends VerticalLayout {

    private PhonesService phonesService;
    Grid<Phones> grid = new Grid<>(Phones.class);

    PhonesForm form;

    public  PhonesView (PhonesService phonesService, AddressService addressService, CallerService callerService, Phone_typesService phone_typesService, Phone_numbersService phone_numbersService){

        this.phonesService = phonesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();


        form = new PhonesForm(phone_numbersService.findAll(), phone_typesService.findAll(), addressService.findAll(), callerService.findAll());
        form.addListener(PhonesForm.SaveEvent.class, this::savePhones);
        form.addListener(PhonesForm.DeleteEvent.class, this::deletePhones);
        form.addListener(PhonesForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о телефонах"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {

        Button addPhones = new Button("Добавить телефон", click -> addPhones());

        HorizontalLayout toolbar = new HorizontalLayout(addPhones);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addPhones() {
        grid.asSingleSelect().clear();
        editPhones(new Phones());
    }


    private void savePhones(PhonesForm.SaveEvent evt) {
        phonesService.save(evt.getPhones());
        updatelist();
        closeEditor();
    }

    private  void deletePhones(PhonesForm.DeleteEvent evt) {
        phonesService.delete(evt.getPhones());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setPhones(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editPhones(Phones phones) {
        if (phones == null) {
            closeEditor();
        } else {
            form.setPhones(phones);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }


    private void updatelist() {
        grid.setItems((phonesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editPhones(gridAddressComponentValueChangeEvent.getValue()));

    }
}

