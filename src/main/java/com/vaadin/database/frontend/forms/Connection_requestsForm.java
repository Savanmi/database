package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.*;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class Connection_requestsForm extends FormLayout {

    IntegerField apartment_number = new IntegerField("номер квртиры");
    ComboBox<Address> address_ID = new ComboBox<>("Id адреса");
    ComboBox<Telephone_exchanges> telephone_exchange_ID = new ComboBox<>("Id гтс");
    ComboBox<Clients> client_ID = new ComboBox<>("Id клиента");
    DatePicker request_date = new DatePicker("дата запроса");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Connection_requests> binder = new BeanValidationBinder<>(Connection_requests.class);


    public Connection_requestsForm(List<Address> addresses, List<Clients> clients, List<Telephone_exchanges> telephone_exchanges) {
        addClassName("contact-form");

        telephone_exchange_ID.setItems(telephone_exchanges);
        telephone_exchange_ID.setItemLabelGenerator(Telephone_exchanges::getIdStr);

        client_ID.setItems(clients);
        client_ID.setItemLabelGenerator(Clients::getIdStr);

        address_ID.setItems(addresses);
        address_ID.setItemLabelGenerator(Address::getIdStr);


        binder.bindInstanceFields(this);


        add(apartment_number,
                address_ID,
                telephone_exchange_ID,
                client_ID,
                request_date,
                createButtonsLayout());
    }

    public void setConnection_requests (Connection_requests connection_requests){
        binder.setBean(connection_requests);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Connection_requestsForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Connection_requestsForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Connection_requestsForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Connection_requestsFormEvent extends ComponentEvent<Connection_requestsForm> {
        private Connection_requests connection_requests;

        protected Connection_requestsFormEvent(Connection_requestsForm source, Connection_requests connection_requests) {
            super(source, false);
            this.connection_requests = connection_requests;
        }

        public Connection_requests getConnection_requests() {
            return connection_requests;
        }
    }

    public static class SaveEvent extends Connection_requestsForm.Connection_requestsFormEvent {
        SaveEvent(Connection_requestsForm source, Connection_requests connection_requests) {
            super(source, connection_requests);
        }
    }

    public static class DeleteEvent extends Connection_requestsForm.Connection_requestsFormEvent {
        DeleteEvent(Connection_requestsForm source, Connection_requests a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Connection_requestsForm.Connection_requestsFormEvent {
        CloseEvent(Connection_requestsForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
