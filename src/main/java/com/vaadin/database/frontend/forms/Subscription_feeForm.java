package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Clients;
import com.vaadin.database.data.entity.Phone_types;
import com.vaadin.database.data.entity.Subscription_fees;
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

public class Subscription_feeForm extends FormLayout {
    ComboBox<Phone_types> phone_number_type_ID = new ComboBox<>("Id типа телефона");
    Checkbox is_deadhead = new Checkbox("Есть льгота");
    Checkbox has_long_distance_calls = new Checkbox("Есть связь с межгородом");
    IntegerField subscription_fee = new IntegerField("Абонентская плата");



    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Subscription_fees> binder = new BeanValidationBinder<>(Subscription_fees.class);



    public Subscription_feeForm(List<Phone_types> phone_types) {
        addClassName("contact-form");

        phone_number_type_ID.setItems(phone_types);
        phone_number_type_ID.setItemLabelGenerator(Phone_types::getIdStr);


        binder.bindInstanceFields(this);



        add(phone_number_type_ID,
                has_long_distance_calls,
                is_deadhead,
                subscription_fee,
                createButtonsLayout());
    }

    public void setSubscription_fee (Subscription_fees subscription_fee){
        binder.setBean(subscription_fee);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Subscription_feeForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Subscription_feeForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Subscription_feeForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Subscription_feeFormEvent extends ComponentEvent<Subscription_feeForm> {
        private Subscription_fees subscription_fees;

        protected Subscription_feeFormEvent(Subscription_feeForm source, Subscription_fees subscription_fees) {
            super(source, false);
            this.subscription_fees = subscription_fees;
        }

        public Subscription_fees getSubscription_fee() {
            return subscription_fees;
        }
    }

    public static class SaveEvent extends Subscription_feeForm.Subscription_feeFormEvent {
        SaveEvent(Subscription_feeForm source, Subscription_fees subscription_fees) {
            super(source, subscription_fees);
        }
    }

    public static class DeleteEvent extends Subscription_feeForm.Subscription_feeFormEvent {
        DeleteEvent(Subscription_feeForm source, Subscription_fees a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Subscription_feeForm.Subscription_feeFormEvent {
        CloseEvent(Subscription_feeForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
