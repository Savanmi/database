package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Public_phones;
import com.vaadin.database.data.entity.Subscription_fees;
import com.vaadin.database.data.service.Phone_typesService;
import com.vaadin.database.data.service.Subscription_feesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.database.frontend.forms.Public_phonesForm;
import com.vaadin.database.frontend.forms.Subscription_feeForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value="subscription_fee", layout = QueryView.class)
@PageTitle("subscription fees")
public class Subscription_feeView extends VerticalLayout {

    private Subscription_feesService subscription_feesService;
    Grid<Subscription_fees> grid = new Grid<>(Subscription_fees.class);

    Subscription_feeForm form;

    public Subscription_feeView(Subscription_feesService subscription_feesService, Phone_typesService phone_typesService){

        this.subscription_feesService = subscription_feesService;

        addClassName("list-view");
        setSizeFull();

        configGrid();

        form = new Subscription_feeForm(phone_typesService.findAll());
        form.addListener(Subscription_feeForm.SaveEvent.class, this::saveSubscription_fee);
        form.addListener(Subscription_feeForm.DeleteEvent.class, this::deleteSubscription_fee);
        form.addListener(Subscription_feeForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные об абонентской плате"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();

    }

    private HorizontalLayout getToolBar() {

        Button addSubscription_fee = new Button("Добавить абонентскую плату", click -> addSubscription_fee());

        HorizontalLayout toolbar = new HorizontalLayout(addSubscription_fee);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addSubscription_fee() {
        grid.asSingleSelect().clear();
        editSubscription_fee(new Subscription_fees());
    }


    private void saveSubscription_fee(Subscription_feeForm.SaveEvent evt) {
        subscription_feesService.save(evt.getSubscription_fee());
        updatelist();
        closeEditor();
    }

    private  void deleteSubscription_fee(Subscription_feeForm.DeleteEvent evt) {
        subscription_feesService.delete(evt.getSubscription_fee());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setSubscription_fee(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editSubscription_fee(Subscription_fees subscription_fees) {
        if (subscription_fees == null) {
            closeEditor();
        } else {
            form.setSubscription_fee(subscription_fees);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((subscription_feesService.findAll()));
    }

    private void configGrid() {

        grid.addClassName("subscription_fee-grid");
        grid.setSizeFull();

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editSubscription_fee(gridAddressComponentValueChangeEvent.getValue()));

    }
}
