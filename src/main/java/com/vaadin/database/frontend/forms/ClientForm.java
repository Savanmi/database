package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.entity.Clients;
import com.vaadin.database.data.entity.Telephone_exchanges;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.component.textfield.TextField;


import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ClientForm extends FormLayout {
    ComboBox<String> gender = new ComboBox<>("Пол");
    DatePicker birth_date = new DatePicker("Дата рождения");
    Checkbox is_deadhead = new Checkbox("Есть льгота");
    TextField first_name = new TextField("Имя");
    TextField second_name = new TextField("Фамилия");
    TextField middle_name = new TextField("Отчество");





    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Clients> binder = new BeanValidationBinder<>(Clients.class);
    Clients clients;


    public ClientForm(List<Clients> all) {
        addClassName("contact-form");

        gender.setItems("M","F");


        binder.forField(birth_date)
                .withValidator(new DateRangeValidator(
                        "Date of birth must be between 1900-01-01 and 2021-01-01",
                        LocalDate.of(1900, 1, 1),
                        LocalDate.of(2021, 1, 1)))
                .bind(Clients::getBirth_date, Clients::setBirth_date);
        binder.setBean(clients);
        binder.bindInstanceFields(this);



        add(gender,
                birth_date,
                is_deadhead,
                first_name,
                second_name,
                middle_name,
                createButtonsLayout());
    }

    public void setClient (Clients clients){
        binder.setBean(clients);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new ClientForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new ClientForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new ClientForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class ClientFormEvent extends ComponentEvent<ClientForm> {
        private Clients clients;

        protected ClientFormEvent(ClientForm source, Clients clients) {
            super(source, false);
            this.clients = clients;
        }

        public Clients getClient() {
            return clients;
        }
    }

    public static class SaveEvent extends ClientForm.ClientFormEvent {
        SaveEvent(ClientForm source, Clients clients) {
            super(source, clients);
        }
    }

    public static class DeleteEvent extends ClientForm.ClientFormEvent {
        DeleteEvent(ClientForm source, Clients a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends ClientForm.ClientFormEvent {
        CloseEvent(ClientForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
