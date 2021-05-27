package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.CallerService;
import com.vaadin.database.data.service.ClientsService;
import com.vaadin.database.data.service.Telephone_exchangesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.forms.AddressForm;
import com.vaadin.database.frontend.forms.BalanceForm;
import com.vaadin.database.frontend.forms.CallerForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="callers", layout = MainView.class)
@PageTitle("callers")
public class CallersView extends VerticalLayout {

    private CallerService callerService;
    Grid<Callers> grid = new Grid<>(Callers.class);

    private CallerForm form;

    public  CallersView (CallerService callerService, Telephone_exchangesService telephone_exchangesService, ClientsService clientsService){

        this.callerService = callerService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new CallerForm(telephone_exchangesService.findAll(), clientsService.findAll());
        form.addListener(CallerForm.SaveEvent.class, this::saveCaller);
        form.addListener(CallerForm.DeleteEvent.class, this::deleteCaller);
        form.addListener(CallerForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о звонящих"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {

        Button addCaller = new Button("Добавить звонящего", click -> addCaller());

        HorizontalLayout toolbar = new HorizontalLayout(addCaller);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addCaller() {
        grid.asSingleSelect().clear();
        editCaller(new Callers());
    }


    private void saveCaller(CallerForm.SaveEvent evt) {
        callerService.save(evt.getCaller());
        updatelist();
        closeEditor();
    }

    private  void deleteCaller(CallerForm.DeleteEvent evt) {
        callerService.delete(evt.getCaller());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setCaller(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editCaller(Callers callers) {
        if (callers == null) {
            closeEditor();
        } else {
            form.setCaller(callers);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((callerService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();
        grid.setColumns("caller_id", "client_id", "has_long_distance_calls", "is_blocked", "telephone_exchange_ID");

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editCaller(gridAddressComponentValueChangeEvent.getValue()));

    }
}

