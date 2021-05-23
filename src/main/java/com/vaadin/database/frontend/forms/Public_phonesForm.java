package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.*;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class Public_phonesForm extends FormLayout {
    ComboBox<Telephone_exchanges> telephone_exchange_id = new ComboBox("Id атс");
    ComboBox<Address> address_ID = new ComboBox("Id адреса");



    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Public_phones> binder = new BeanValidationBinder<>(Public_phones.class);


    public Public_phonesForm(List<Telephone_exchanges> telephone_exchanges, List<Address> addresses) {
        addClassName("contact-form");

        telephone_exchange_id.setItems(telephone_exchanges);
        telephone_exchange_id.setItemLabelGenerator(Telephone_exchanges::getIdStr);

        address_ID.setItems(addresses);
        address_ID.setItemLabelGenerator(Address::getIdStr);

        binder.bindInstanceFields(this);


        add(telephone_exchange_id,
                address_ID,
                createButtonsLayout());
    }

    public void setPublic_phones (Public_phones ldc){
        binder.setBean(ldc);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Public_phonesForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Public_phonesForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Public_phonesForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Public_phonesFormEvent extends ComponentEvent<Public_phonesForm> {
        private Public_phones public_phones;

        protected Public_phonesFormEvent(Public_phonesForm source, Public_phones public_phones) {
            super(source, false);
            this.public_phones = public_phones;
        }

        public Public_phones getPublic_phones() {
            return public_phones;
        }
    }

    public static class SaveEvent extends Public_phonesForm.Public_phonesFormEvent {
        SaveEvent(Public_phonesForm source, Public_phones public_phones) {
            super(source, public_phones);
        }
    }

    public static class DeleteEvent extends Public_phonesForm.Public_phonesFormEvent {
        DeleteEvent(Public_phonesForm source, Public_phones a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Public_phonesForm.Public_phonesFormEvent {
        CloseEvent(Public_phonesForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
