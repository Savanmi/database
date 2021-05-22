package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Long_distance_call_prices;
import com.vaadin.database.data.entity.Long_distance_calls;
import com.vaadin.database.data.entity.Phones;
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

public class LdcForm extends FormLayout {

    IntegerField call_price = new IntegerField("цена за минуту");
    ComboBox<Phones> source_phone_ID = new ComboBox("Исходящий абонент");
    ComboBox<Phones> destination_phone_ID = new ComboBox("Входящий абонент");
    DateTimePicker start_date = new DateTimePicker("Начало разговора");
    DateTimePicker end_date = new DateTimePicker("Конец разговора");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Long_distance_calls> binder = new BeanValidationBinder<>(Long_distance_calls.class);


    public LdcForm(List<Phones> phones) {
        addClassName("contact-form");

        source_phone_ID.setItems(phones);
        source_phone_ID.setItemLabelGenerator(Phones::getIdStr);

        destination_phone_ID.setItems(phones);
        destination_phone_ID.setItemLabelGenerator(Phones::getIdStr);

        binder.bindInstanceFields(this);


        add(call_price,
                source_phone_ID,
                destination_phone_ID,
                start_date,
                end_date,
                createButtonsLayout());
    }

    public void setLdc (Long_distance_calls ldc){
        binder.setBean(ldc);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new LdcForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new LdcForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new LdcForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class LdcFormEvent extends ComponentEvent<LdcForm> {
        private Long_distance_calls long_distance_calls;

        protected LdcFormEvent(LdcForm source, Long_distance_calls long_distance_calls) {
            super(source, false);
            this.long_distance_calls = long_distance_calls;
        }

        public Long_distance_calls getLdc() {
            return long_distance_calls;
        }
    }

    public static class SaveEvent extends LdcForm.LdcFormEvent {
        SaveEvent(LdcForm source, Long_distance_calls long_distance_calls) {
            super(source, long_distance_calls);
        }
    }

    public static class DeleteEvent extends LdcForm.LdcFormEvent {
        DeleteEvent(LdcForm source, Long_distance_calls a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends LdcForm.LdcFormEvent {
        CloseEvent(LdcForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
