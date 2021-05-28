package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Long_distance_call_prices;
import com.vaadin.database.data.service.Long_distance_call_pricesService;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.database.frontend.forms.Ldc_pricesForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="ldcall_prices", layout = QueryView.class)
@PageTitle("ldcall prices")
public class Long_distance_call_pricesView extends VerticalLayout {

    private Long_distance_call_pricesService long_distance_call_pricesService;
    Grid<Long_distance_call_prices> grid = new Grid<>(Long_distance_call_prices.class);

    private Ldc_pricesForm form;


    public  Long_distance_call_pricesView (Long_distance_call_pricesService long_distance_call_pricesService){

        this.long_distance_call_pricesService = long_distance_call_pricesService;
        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new Ldc_pricesForm(long_distance_call_pricesService.findAll());
        form.addListener(Ldc_pricesForm.SaveEvent.class, this::saveLdc_prices);
        form.addListener(Ldc_pricesForm.DeleteEvent.class, this::deleteLdc_prices);
        form.addListener(Ldc_pricesForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные тарифах на междугородние звонки"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();

    }

    private HorizontalLayout getToolBar() {

        Button addLdc_prices = new Button("Добавить цену на межгород", click -> addLdc_prices());

        HorizontalLayout toolbar = new HorizontalLayout(addLdc_prices);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addLdc_prices() {
        grid.asSingleSelect().clear();
        editLdc_prices(new Long_distance_call_prices());
    }


    private void saveLdc_prices(Ldc_pricesForm.SaveEvent evt) {
        long_distance_call_pricesService.save(evt.getLdc_prices());
        updatelist();
        closeEditor();
    }

    private  void deleteLdc_prices(Ldc_pricesForm.DeleteEvent evt) {
        long_distance_call_pricesService.delete(evt.getLdc_prices());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setLdc_prices(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editLdc_prices(Long_distance_call_prices long_distance_call_prices) {
        if (long_distance_call_prices == null) {
            closeEditor();
        } else {
            form.setLdc_prices(long_distance_call_prices);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }


    private void updatelist() {
        grid.setItems((long_distance_call_pricesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editLdc_prices(gridAddressComponentValueChangeEvent.getValue()));

    }
}
