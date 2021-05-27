package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.*;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class PhonesForm extends FormLayout {
    IntegerField Apartment_number = new IntegerField("номер квартиры");
    ComboBox<Phone_numbers> phone_number_ID = new ComboBox("Id номера телефона");
    ComboBox<Phone_types> phone_type_ID = new ComboBox("Id типа телефона");
    ComboBox<Address> address_ID = new ComboBox("Id адреса");
    ComboBox<Callers> caller_ID = new ComboBox("Id звонящего");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Phones> binder = new BeanValidationBinder<>(Phones.class);


    public PhonesForm(List<Phone_numbers> phone_numbers, List<Phone_types> phone_types, List<Address> address,List<Callers> caller ) {
        addClassName("contact-form");

        phone_number_ID.setItems(phone_numbers);
        phone_number_ID.setItemLabelGenerator(Phone_numbers::getIdStr);

        phone_type_ID.setItems(phone_types);
        phone_type_ID.setItemLabelGenerator(Phone_types::getIdStr);

        address_ID.setItems(address);
        address_ID.setItemLabelGenerator(Address::getIdStr);

        caller_ID.setItems(caller);
        caller_ID.setItemLabelGenerator(Callers::getIdStr);

        binder.bindInstanceFields(this);


        add(Apartment_number,
                phone_number_ID,
                phone_type_ID,
                address_ID,
                caller_ID,
                createButtonsLayout());
    }

    public void setPhones (Phones ldc){
        binder.setBean(ldc);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new PhonesForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new PhonesForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new PhonesForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class PhonesFormEvent extends ComponentEvent<PhonesForm> {
        private Phones phones;

        protected PhonesFormEvent(PhonesForm source, Phones phones) {
            super(source, false);
            this.phones = phones;
        }

        public Phones getPhones() {
            return phones;
        }
    }

    public static class SaveEvent extends PhonesForm.PhonesFormEvent {
        SaveEvent(PhonesForm source, Phones phones) {
            super(source, phones);
        }
    }

    public static class DeleteEvent extends PhonesForm.PhonesFormEvent {
        DeleteEvent(PhonesForm source, Phones a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends PhonesForm.PhonesFormEvent {
        CloseEvent(PhonesForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}