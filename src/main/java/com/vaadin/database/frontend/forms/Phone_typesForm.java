package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Phone_types;
import com.vaadin.database.data.entity.Phones;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class Phone_typesForm extends FormLayout {
    TextField type_name = new TextField("Название типа");
    ComboBox<Phones> phone = new ComboBox("Id телефона");



    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Phone_types> binder = new BeanValidationBinder<>(Phone_types.class);


    public Phone_typesForm(List<Phones> phones) {
        addClassName("contact-form");


        phone.setItems(phones);
        phone.setItemLabelGenerator(Phones::getIdStr);

        binder.bindInstanceFields(this);


        add(phone,
                type_name,
                phone,
                createButtonsLayout());
    }

    public void setPhone_types (Phone_types ldc){
        binder.setBean(ldc);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Phone_typesForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Phone_typesForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Phone_typesForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Phone_typesFormEvent extends ComponentEvent<Phone_typesForm> {
        private Phone_types phone_types;

        protected Phone_typesFormEvent(Phone_typesForm source, Phone_types phone_types) {
            super(source, false);
            this.phone_types = phone_types;
        }

        public Phone_types getPhone_types() {
            return phone_types;
        }
    }

    public static class SaveEvent extends Phone_typesForm.Phone_typesFormEvent {
        SaveEvent(Phone_typesForm source, Phone_types phone_types) {
            super(source, phone_types);
        }
    }

    public static class DeleteEvent extends Phone_typesForm.Phone_typesFormEvent {
        DeleteEvent(Phone_typesForm source, Phone_types a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Phone_typesForm.Phone_typesFormEvent {
        CloseEvent(Phone_typesForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}