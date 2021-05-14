package com.vaadin.database.frontend.forms;


import com.vaadin.database.data.entity.Address;
import com.vaadin.database.frontend.AddressView;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class AddressForm extends FormLayout {

   // TextField Zip_code = new TextField("Индекс");
    IntegerField Zip_code = new IntegerField("Индекс");
    TextField city = new TextField("Город");
    TextField region = new TextField("Район");
    TextField street = new TextField("Улица");
    IntegerField building_number = new IntegerField("Номер дома");


  //  TextField building_number = new TextField("Номер дома");


    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отмена");

    Binder<Address> binder = new BeanValidationBinder<>(Address.class);


    public AddressForm(List<Address> all) {
        addClassName("contact-form");

        binder.bindInstanceFields(this);

        add(city,
                region,
                street,
                Zip_code,
                building_number,
                createButtonsLayout());
    }

    public void setAddress (Address address){
        binder.setBean(address);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        save.addClickListener(buttonClickEvent -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new SaveEvent(this,binder.getBean()));
        }
    }

    // Events
    public static abstract class AddressFormEvent extends ComponentEvent<AddressForm> {
        private Address address;

        protected AddressFormEvent(AddressForm source, Address address) {
            super(source, false);
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

    public static class SaveEvent extends AddressFormEvent {
        SaveEvent(AddressForm source, Address address) {
            super(source, address);
        }
    }

    public static class DeleteEvent extends AddressFormEvent {
        DeleteEvent(AddressForm source, Address a) {
            super(source, a);
        }
    }

    public static class CloseEvent extends AddressFormEvent {
        CloseEvent(AddressForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
