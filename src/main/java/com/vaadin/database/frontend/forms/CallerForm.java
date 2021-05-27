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
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class CallerForm extends FormLayout {

    ComboBox<Telephone_exchanges> telephone_exchanges_ID = new ComboBox<>("ID ГТС");
    ComboBox<Clients> client_ID = new ComboBox<>("ID клиента");
    Checkbox has_long_distance_calls = new Checkbox("Подключен межгород");
    Checkbox is_blocked = new Checkbox("Заблокирован");




    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Callers> binder = new BeanValidationBinder<>(Callers.class);



    public CallerForm(List<Telephone_exchanges> telephone_exchanges, List<Clients> clients) {
        addClassName("contact-form");



        telephone_exchanges_ID.setItems(telephone_exchanges);
        telephone_exchanges_ID.setItemLabelGenerator(Telephone_exchanges::getIdStr);

        client_ID.setItems(clients);
        client_ID.setItemLabelGenerator(Clients::getIdStr);



        binder.bindInstanceFields(this);



        add(is_blocked,
                has_long_distance_calls,
                client_ID,
                telephone_exchanges_ID,
                createButtonsLayout());
    }

    public void setCaller (Callers callers){
        binder.setBean(callers);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new CallerForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CallerForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new CallerForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class CallerFormEvent extends ComponentEvent<CallerForm> {
        private Callers callers;

        protected CallerFormEvent(CallerForm source, Callers callers) {
            super(source, false);
            this.callers = callers;
        }

        public Callers getCaller() {
            return callers;
        }
    }

    public static class SaveEvent extends CallerForm.CallerFormEvent {
        SaveEvent(CallerForm source, Callers callers) {
            super(source, callers);
        }
    }

    public static class DeleteEvent extends CallerForm.CallerFormEvent {
        DeleteEvent(CallerForm source, Callers a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends CallerForm.CallerFormEvent {
        CloseEvent(CallerForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
