package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Phone_types;
import com.vaadin.database.data.entity.Subscription_fees;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class Telephone_exchangesForm extends FormLayout {

    TextField exchange_name = new TextField("Название АТС");
    TextField exchange_type = new TextField("Тип АТС");



    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Telephone_exchanges> binder = new BeanValidationBinder<>(Telephone_exchanges.class);



    public Telephone_exchangesForm(List<Telephone_exchanges> all) {
        addClassName("contact-form");


        binder.bindInstanceFields(this);



        add(exchange_name,
                exchange_type,
                createButtonsLayout());
    }

    public void setTelephone_exchanges (Telephone_exchanges telephone_exchanges){
        binder.setBean(telephone_exchanges);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Telephone_exchangesForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Telephone_exchangesForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Telephone_exchangesForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Telephone_exchangesFormEvent extends ComponentEvent<Telephone_exchangesForm> {
        private Telephone_exchanges telephone_exchanges;

        protected Telephone_exchangesFormEvent(Telephone_exchangesForm source, Telephone_exchanges telephone_exchanges) {
            super(source, false);
            this.telephone_exchanges = telephone_exchanges;
        }

        public Telephone_exchanges getTelephone_exchanges() {
            return telephone_exchanges;
        }
    }

    public static class SaveEvent extends Telephone_exchangesForm.Telephone_exchangesFormEvent {
        SaveEvent(Telephone_exchangesForm source, Telephone_exchanges telephone_exchanges) {
            super(source, telephone_exchanges);
        }
    }

    public static class DeleteEvent extends Telephone_exchangesForm.Telephone_exchangesFormEvent {
        DeleteEvent(Telephone_exchangesForm source, Telephone_exchanges a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Telephone_exchangesForm.Telephone_exchangesFormEvent {
        CloseEvent(Telephone_exchangesForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
