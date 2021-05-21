package com.vaadin.database.frontend.forms;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Long_distance_call_prices;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class Ldc_pricesForm extends FormLayout {
    TextField Price_name = new TextField("Название");
    IntegerField Price_per_minute = new IntegerField("цена за минуту");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Long_distance_call_prices> binder = new BeanValidationBinder<>(Long_distance_call_prices.class);


    public Ldc_pricesForm(List<Long_distance_call_prices> all) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);


        add(Price_name,
                Price_per_minute,
                createButtonsLayout());
    }

    public void setLdc_prices (Long_distance_call_prices ldc_prices){
        binder.setBean(ldc_prices);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new Ldc_pricesForm.DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new Ldc_pricesForm.CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new Ldc_pricesForm.SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class Ldc_pricesFormEvent extends ComponentEvent<Ldc_pricesForm> {
        private Long_distance_call_prices long_distance_call_prices;

        protected Ldc_pricesFormEvent(Ldc_pricesForm source, Long_distance_call_prices long_distance_call_prices) {
            super(source, false);
            this.long_distance_call_prices = long_distance_call_prices;
        }

        public Long_distance_call_prices getLdc_prices() {
            return long_distance_call_prices;
        }
    }

    public static class SaveEvent extends Ldc_pricesForm.Ldc_pricesFormEvent {
        SaveEvent(Ldc_pricesForm source, Long_distance_call_prices long_distance_call_prices) {
            super(source, long_distance_call_prices);
        }
    }

    public static class DeleteEvent extends Ldc_pricesForm.Ldc_pricesFormEvent {
        DeleteEvent(Ldc_pricesForm source, Long_distance_call_prices a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends Ldc_pricesForm.Ldc_pricesFormEvent {
        CloseEvent(Ldc_pricesForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
