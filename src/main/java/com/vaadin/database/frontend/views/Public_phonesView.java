package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Public_phones;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.database.data.service.AddressService;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Public_phonesService;
import com.vaadin.database.data.service.Telephone_exchangesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.database.frontend.forms.Phone_numbersForm;
import com.vaadin.database.frontend.forms.Public_phonesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="public_phones", layout = QueryView.class)
@PageTitle("public_phones")
public class Public_phonesView extends VerticalLayout {

    private Public_phonesService public_phonesService;
    Grid<Public_phones> grid = new Grid<>(Public_phones.class);
    Public_phonesForm form;

    public  Public_phonesView (Public_phonesService public_phonesService, Telephone_exchangesService telephone_exchangesService, AddressService addressService){

        this.public_phonesService = public_phonesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new Public_phonesForm(telephone_exchangesService.findAll(), addressService.findAll());
        form.addListener(Public_phonesForm.SaveEvent.class, this::savePublic_phones);
        form.addListener(Public_phonesForm.DeleteEvent.class, this::deletePublic_phones);
        form.addListener(Public_phonesForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные об общественных телефонах"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }


    private HorizontalLayout getToolBar() {

        Button addPublic_phones = new Button("Добавить общественный телефон", click -> addPublic_phones());

        HorizontalLayout toolbar = new HorizontalLayout(addPublic_phones);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addPublic_phones() {
        grid.asSingleSelect().clear();
        editPublic_phones(new Public_phones());
    }


    private void savePublic_phones(Public_phonesForm.SaveEvent evt) {
        public_phonesService.save(evt.getPublic_phones());
        updatelist();
        closeEditor();
    }

    private  void deletePublic_phones(Public_phonesForm.DeleteEvent evt) {
        public_phonesService.delete(evt.getPublic_phones());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setPublic_phones(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editPublic_phones(Public_phones public_phones) {
        if (public_phones == null) {
            closeEditor();
        } else {
            form.setPublic_phones(public_phones);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((public_phonesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editPublic_phones(gridAddressComponentValueChangeEvent.getValue()));

    }
}
