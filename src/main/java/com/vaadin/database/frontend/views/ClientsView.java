package com.vaadin.database.frontend.views;

import com.vaadin.database.data.entity.Clients;
import com.vaadin.database.data.service.ClientsService;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.database.frontend.forms.ClientForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="clients", layout = QueryView.class)
@PageTitle("clients")
public class ClientsView extends VerticalLayout {

    private ClientsService clientsService;
    Grid<Clients> grid = new Grid<>(Clients.class);

   TextField filterText = new TextField();

    private ClientForm form;



    public  ClientsView (ClientsService clientsService){

        this.clientsService = clientsService;
        addClassName("list-view");
        setSizeFull();

        configGrid();


        form = new ClientForm(clientsService.findAll());
        form.addListener(ClientForm.SaveEvent.class, this::saveClient);
        form.addListener(ClientForm.DeleteEvent.class, this::deleteClient);
        form.addListener(ClientForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form, grid);
        content.addClassName("content");
        content.setSizeFull();

        add(new H3("Данные о клиентах"));
        add(getToolBar(),content);
        updatelist();

        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Фильтр по ФИО или полу");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updatelist());

        Button addClient = new Button("Добавить клиента", click -> addClient());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addClient);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addClient() {
        grid.asSingleSelect().clear();
        editClient(new Clients());
    }


    private void saveClient(ClientForm.SaveEvent evt) {
        clientsService.save(evt.getClient());
        updatelist();
        closeEditor();
    }

    private  void deleteClient(ClientForm.DeleteEvent evt) {
        clientsService.delete(evt.getClient());
        updatelist();
        closeEditor();
    }

    private void closeEditor() {
        form.setClient(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void editClient(Clients clients) {
        if (clients == null) {
            closeEditor();
        } else {
            form.setClient(clients);
            form.setVisible(true);
            form.setClassName("editing");
        }
    }

    private void updatelist() {
        grid.setItems((clientsService.findAll(filterText.getValue())));
    }


    private void configGrid() {

        grid.addClassName("address-grid");
        grid.setSizeFull();

        grid.setColumns("client_ID","second_name", "first_name", "middle_name", "gender", "birth_date", "is_deadhead");

        grid.getColumns().forEach(subscription_feesColumn -> subscription_feesColumn.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridAddressComponentValueChangeEvent -> editClient(gridAddressComponentValueChangeEvent.getValue()));

    }
}