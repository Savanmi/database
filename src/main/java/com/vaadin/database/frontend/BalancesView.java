package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.service.AddressService;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.CallerService;
import com.vaadin.database.frontend.forms.AddressForm;
import com.vaadin.database.frontend.forms.BalanceForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("balances")
public class BalancesView extends VerticalLayout {

    private BalancesService balancesService;
    Grid<Balances> grid = new Grid<>(Balances.class);
    TextField filterText = new TextField();


    private BalanceForm form;

    public  BalancesView (BalancesService balancesService, CallerService callerService){

        this.balancesService = balancesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new BalanceForm(callerService.findAll());
        form.addListener(BalanceForm.SaveEvent.class, this::saveBalance);
        form.addListener(BalanceForm.DeleteEvent.class, this::deleteBalance);
        form.addListener(AddressForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о балансах пользователей"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {

        Button addBalance = new Button("Добавить баланс", click -> addBalance());

        HorizontalLayout toolbar = new HorizontalLayout(addBalance);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addBalance() {
        grid.asSingleSelect().clear();
        editBalance(new Balances());
    }


    private void saveBalance(BalanceForm.SaveEvent evt) {
        balancesService.save(evt.getBalance());
        updatelist();
        closeEditor();
    }

    private  void deleteBalance(BalanceForm.DeleteEvent evt) {
        balancesService.delete(evt.getBalance());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setBalance(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editBalance(Balances balances) {
        if (balances == null) {
            closeEditor();
        } else {
            form.setBalance(balances);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((balancesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("balance-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editBalance(gridAddressComponentValueChangeEvent.getValue()));


    }
}
