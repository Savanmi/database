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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class Installing_possibilitiesForm extends FormLayout {

    ComboBox<Address> address_ID = new ComboBox<>("Id адреса");
    ComboBox<Telephone_exchanges> telephone_exchange_ID = new ComboBox<>("Id гтс");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Installing_possibilities> binder = new BeanValidationBinder<>(Installing_possibilities.class);


    public Installing_possibilitiesForm(List<Address> addresses, List<Telephone_exchanges> telephone_exchanges) {
        addClassName("contact-form");

        telephone_exchange_ID.setItems(telephone_exchanges);
        telephone_exchange_ID.setItemLabelGenerator(Telephone_exchanges::getIdStr);


        address_ID.setItems(addresses);
        address_ID.setItemLabelGenerator(Address::getIdStr);


        binder.bindInstanceFields(this);


        add(address_ID,
                telephone_exchange_ID,
                createButtonsLayout());
    }

    public void setInstalling_possibilities (Installing_possibilities installing_possibilities){
        binder.setBean(installing_possibilities);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Installing_possibilitiesForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Installing_possibilitiesForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Installing_possibilitiesForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Installing_possibilitiesFormEvent extends ComponentEvent<Installing_possibilitiesForm> {
        private Installing_possibilities installing_possibilities;

        protected Installing_possibilitiesFormEvent(Installing_possibilitiesForm source, Installing_possibilities installing_possibilities) {
            super(source, false);
            this.installing_possibilities = installing_possibilities;
        }

        public Installing_possibilities getInstalling_possibilities() {
            return installing_possibilities;
        }
    }

    public static class SaveEvent extends Installing_possibilitiesForm.Installing_possibilitiesFormEvent {
        SaveEvent(Installing_possibilitiesForm source, Installing_possibilities installing_possibilities) {
            super(source, installing_possibilities);
        }
    }

    public static class DeleteEvent extends Installing_possibilitiesForm.Installing_possibilitiesFormEvent {
        DeleteEvent(Installing_possibilitiesForm source, Installing_possibilities a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Installing_possibilitiesForm.Installing_possibilitiesFormEvent {
        CloseEvent(Installing_possibilitiesForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
