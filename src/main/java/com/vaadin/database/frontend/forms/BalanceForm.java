package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Address;
import com.vaadin.database.data.entity.Balances;
import com.vaadin.database.data.entity.Callers;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class BalanceForm extends FormLayout {
    ComboBox<Callers> caller_ID = new ComboBox<>("Id звонящего");
    DatePicker subscription_debt_date = new DatePicker("Дата задолженности");
    DatePicker long_dist_debt_date = new DatePicker("Дата задолженности межгород");
    IntegerField penalty_interest = new IntegerField("Пеня");
    IntegerField long_distance_calls_debt = new IntegerField("Задолженность за межгород");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Balances> binder = new BeanValidationBinder<>(Balances.class);


    public BalanceForm(List<Callers> callers) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        caller_ID.setItems(callers);
        caller_ID.setItemLabelGenerator(Callers::getIdStr);

        add(subscription_debt_date,
                long_dist_debt_date,
                penalty_interest,
                long_distance_calls_debt,
                caller_ID,
                createButtonsLayout());
    }

    public void setBalance (Balances balances){
        binder.setBean(balances);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new BalanceForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new BalanceForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new BalanceForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class BalanceFormEvent extends ComponentEvent<BalanceForm> {
        private Balances balances;

        protected BalanceFormEvent(BalanceForm source, Balances balances) {
            super(source, false);
            this.balances = balances;
        }

        public Balances getBalance() {
            return balances;
        }
    }

    public static class SaveEvent extends BalanceForm.BalanceFormEvent {
        SaveEvent(BalanceForm source, Balances balances) {
            super(source, balances);
        }
    }

    public static class DeleteEvent extends BalanceForm.BalanceFormEvent {
        DeleteEvent(BalanceForm source, Balances a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends BalanceForm.BalanceFormEvent {
        CloseEvent(BalanceForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
