package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Long_distance_calls;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.entity.Phones;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class Phone_numbersForm extends FormLayout {

    TextField phone_number = new TextField("номер телефона");
    ComboBox<Telephone_exchanges> telephone_exchange_id = new ComboBox("Id атс");
    ComboBox<Phones> phone = new ComboBox("Id телефона");



    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Phone_numbers> binder = new BeanValidationBinder<>(Phone_numbers.class);


    public Phone_numbersForm(List<Phones> phones, List<Telephone_exchanges> telephone_exchanges) {
        addClassName("contact-form");

        telephone_exchange_id.setItems(telephone_exchanges);
        telephone_exchange_id.setItemLabelGenerator(Telephone_exchanges::getIdStr);

        phone.setItems(phones);
        phone.setItemLabelGenerator(Phones::getIdStr);

        binder.bindInstanceFields(this);


        add(phone,
                telephone_exchange_id,
                phone_number,
                createButtonsLayout());
    }

    public void setPhone_numbers (Phone_numbers ldc){
        binder.setBean(ldc);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Phone_numbersForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Phone_numbersForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Phone_numbersForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Phone_numbersFormEvent extends ComponentEvent<Phone_numbersForm> {
        private Phone_numbers phone_numbers;

        protected Phone_numbersFormEvent(Phone_numbersForm source, Phone_numbers phone_numbers) {
            super(source, false);
            this.phone_numbers = phone_numbers;
        }

        public Phone_numbers getPhone_numbers() {
            return phone_numbers;
        }
    }

    public static class SaveEvent extends Phone_numbersForm.Phone_numbersFormEvent {
        SaveEvent(Phone_numbersForm source, Phone_numbers phone_numbers) {
            super(source, phone_numbers);
        }
    }

    public static class DeleteEvent extends Phone_numbersForm.Phone_numbersFormEvent {
        DeleteEvent(Phone_numbersForm source, Phone_numbers a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Phone_numbersForm.Phone_numbersFormEvent {
        CloseEvent(Phone_numbersForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
