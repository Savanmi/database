package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Subscription_fees;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.database.data.service.Telephone_exchangesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.forms.Subscription_feeForm;
import com.vaadin.database.frontend.forms.Telephone_exchangesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="tex", layout = MainView.class)
@PageTitle("telephone exchanges")
public class Telephone_exchangesView extends VerticalLayout {

    private Telephone_exchangesService telephone_exchangesService;
    Grid<Telephone_exchanges> grid = new Grid<>(Telephone_exchanges.class);

    Telephone_exchangesForm form;

    public Telephone_exchangesView(Telephone_exchangesService telephone_exchangesService){
        this.telephone_exchangesService = telephone_exchangesService;

        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new Telephone_exchangesForm(telephone_exchangesService.findAll());
        form.addListener(Telephone_exchangesForm.SaveEvent.class, this::saveTelephone_exchanges);
        form.addListener(Telephone_exchangesForm.DeleteEvent.class, this::deleteTelephone_exchanges);
        form.addListener(Telephone_exchangesForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные об АТС"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();

    }

    private HorizontalLayout getToolBar() {

        Button addTelephone_exchanges = new Button("Добавить АТС", click -> addTelephone_exchanges());

        HorizontalLayout toolbar = new HorizontalLayout(addTelephone_exchanges);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addTelephone_exchanges() {
        grid.asSingleSelect().clear();
        editTelephone_exchanges(new Telephone_exchanges());
    }


    private void saveTelephone_exchanges(Telephone_exchangesForm.SaveEvent evt) {
        telephone_exchangesService.save(evt.getTelephone_exchanges());
        updatelist();
        closeEditor();
    }

    private  void deleteTelephone_exchanges(Telephone_exchangesForm.DeleteEvent evt) {
        telephone_exchangesService.delete(evt.getTelephone_exchanges());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setTelephone_exchanges(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editTelephone_exchanges(Telephone_exchanges telephone_exchanges) {
        if (telephone_exchanges == null) {
            closeEditor();
        } else {
            form.setTelephone_exchanges(telephone_exchanges);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }


    private void updatelist() {
        grid.setItems((telephone_exchangesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("subscription_fee-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(telephone_exchangesColumn -> telephone_exchangesColumn.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editTelephone_exchanges(gridAddressComponentValueChangeEvent.getValue()));

    }
}
