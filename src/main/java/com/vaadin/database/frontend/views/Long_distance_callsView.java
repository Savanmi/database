package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Long_distance_call_prices;
import com.vaadin.database.data.entity.Long_distance_calls;
import com.vaadin.database.data.entity.Phones;
import com.vaadin.database.data.service.BalancesService;
import com.vaadin.database.data.service.Long_distance_callsService;
import com.vaadin.database.data.service.PhonesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.database.frontend.forms.Connection_pricesForm;
import com.vaadin.database.frontend.forms.LdcForm;
import com.vaadin.database.frontend.forms.Ldc_pricesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="ldcalls", layout = QueryView.class)
@PageTitle("ld calls")
public class Long_distance_callsView extends VerticalLayout {

    private Long_distance_callsService long_distance_callsService;
    Grid<Long_distance_calls> grid = new Grid<>(Long_distance_calls.class);

    LdcForm form;

    public  Long_distance_callsView (Long_distance_callsService long_distance_callsService, PhonesService phonesService){

        this.long_distance_callsService = long_distance_callsService;
        addClassName("list-view");
        setSizeFull();

        configGrid();


        form = new LdcForm(phonesService.findAll());
        form.addListener(LdcForm.SaveEvent.class, this::saveLdc);
        form.addListener(LdcForm.DeleteEvent.class, this::deleteLdc);
        form.addListener(LdcForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о звонках"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();

    }

    private HorizontalLayout getToolBar() {

        Button addLdc = new Button("Добавить звонок", click -> addLdc());

        HorizontalLayout toolbar = new HorizontalLayout(addLdc);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addLdc() {
        grid.asSingleSelect().clear();
        editLdc(new Long_distance_calls());
    }


    private void saveLdc(LdcForm.SaveEvent evt) {
        long_distance_callsService.save(evt.getLdc());
        updatelist();
        closeEditor();
    }

    private  void deleteLdc(LdcForm.DeleteEvent evt) {
        long_distance_callsService.delete(evt.getLdc());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setLdc(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editLdc(Long_distance_calls long_distance_calls) {
        if (long_distance_calls == null) {
            closeEditor();
        } else {
            form.setLdc(long_distance_calls);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((long_distance_callsService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editLdc(gridAddressComponentValueChangeEvent.getValue()));

    }
}

