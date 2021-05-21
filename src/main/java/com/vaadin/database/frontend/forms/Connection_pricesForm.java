package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.entity.Clients;
import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
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

public class Connection_pricesForm extends FormLayout {
    TextField Price_name = new TextField("Название");
    IntegerField price = new IntegerField("цена");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Connection_prices> binder = new BeanValidationBinder<>(Connection_prices.class);


    public Connection_pricesForm(List<Connection_prices> all) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);


        add(Price_name,
                price,
                createButtonsLayout());
    }

    public void setConnection_prices (Connection_prices connection_prices){
        binder.setBean(connection_prices);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Connection_pricesForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Connection_pricesForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Connection_pricesForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Connection_pricesFormEvent extends ComponentEvent<Connection_pricesForm> {
        private Connection_prices connection_prices;

        protected Connection_pricesFormEvent(Connection_pricesForm source, Connection_prices connection_prices) {
            super(source, false);
            this.connection_prices = connection_prices;
        }

        public Connection_prices getConnection_prices() {
            return connection_prices;
        }
    }

    public static class SaveEvent extends Connection_pricesForm.Connection_pricesFormEvent {
        SaveEvent(Connection_pricesForm source, Connection_prices connection_prices) {
            super(source, connection_prices);
        }
    }

    public static class DeleteEvent extends Connection_pricesForm.Connection_pricesFormEvent {
        DeleteEvent(Connection_pricesForm source, Connection_prices a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Connection_pricesForm.Connection_pricesFormEvent {
        CloseEvent(Connection_pricesForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
