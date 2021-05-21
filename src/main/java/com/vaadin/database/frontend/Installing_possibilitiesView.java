package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Installing_possibilities;
import com.vaadin.database.data.entity.Long_distance_call_prices;
import com.vaadin.database.data.service.AddressService;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Installing_possibilitiesService;
import com.vaadin.database.data.service.Telephone_exchangesService;
import com.vaadin.database.frontend.forms.Connection_pricesForm;
import com.vaadin.database.frontend.forms.Installing_possibilitiesForm;
import com.vaadin.database.frontend.forms.Ldc_pricesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("installing")
public class Installing_possibilitiesView extends VerticalLayout {

    private Installing_possibilitiesService installing_possibilitiesService;
    Grid<Installing_possibilities> grid = new Grid<>(Installing_possibilities.class);

    private Installing_possibilitiesForm form;


    public  Installing_possibilitiesView (Installing_possibilitiesService installing_possibilitiesService, Telephone_exchangesService telephone_exchangesService, AddressService addressService){

        this.installing_possibilitiesService = installing_possibilitiesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();


        form = new Installing_possibilitiesForm(addressService.findAll(), telephone_exchangesService.findAll());
        form.addListener(Installing_possibilitiesForm.SaveEvent.class, this::saveInstalling_possibilities);
        form.addListener(Installing_possibilitiesForm.DeleteEvent.class, this::deleteInstalling_possibilities);
        form.addListener(Installing_possibilitiesForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о возможностях установки"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();

    }

    private HorizontalLayout getToolBar() {

        Button addInstalling_possibilities = new Button("Добавить возможность установки", click -> addInstalling_possibilities());

        HorizontalLayout toolbar = new HorizontalLayout(addInstalling_possibilities);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addInstalling_possibilities() {
        grid.asSingleSelect().clear();
        editInstalling_possibilities(new Installing_possibilities());
    }


    private void saveInstalling_possibilities(Installing_possibilitiesForm.SaveEvent evt) {
        installing_possibilitiesService.save(evt.getInstalling_possibilities());
        updatelist();
        closeEditor();
    }

    private  void deleteInstalling_possibilities(Installing_possibilitiesForm.DeleteEvent evt) {
        installing_possibilitiesService.delete(evt.getInstalling_possibilities());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setInstalling_possibilities(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editInstalling_possibilities(Installing_possibilities installing_possibilities) {
        if (installing_possibilities == null) {
            closeEditor();
        } else {
            form.setInstalling_possibilities(installing_possibilities);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }


    private void updatelist() {
        grid.setItems((installing_possibilitiesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editInstalling_possibilities(gridAddressComponentValueChangeEvent.getValue()));

    }
}
