package com.vaadin.database.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phone_numbers {

    @Id
    private int phone_number_ID;

    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "telephone_exchange_id", referencedColumnName = "telephone_exchange_id")
    private Telephone_exchanges telephone_exchange_id;

    public Phone_numbers(){}

    public Phone_numbers(int phone_number_ID, String phone_number, Telephone_exchanges telephone_exchange_id) {
        this.phone_number_ID = phone_number_ID;
        this.phone_number = phone_number;
        this.telephone_exchange_id = telephone_exchange_id;
    }

    public int getPhone_number_ID() {
        return phone_number_ID;
    }

    public void setPhone_number_ID(int phone_number_ID) {
        this.phone_number_ID = phone_number_ID;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Telephone_exchanges getTelephone_exchange_id() {
        return telephone_exchange_id;
    }

    public void setTelephone_exchange_id(Telephone_exchanges telephone_exchange_id) {
        this.telephone_exchange_id = telephone_exchange_id;
    }

    @Override
    public String toString() {
        return "Phone_numbers{" +
                "phone_number_ID=" + phone_number_ID +
                ", phone_number='" + phone_number + '\'' +
                ", telephone_exchange_id=" + telephone_exchange_id +
                '}';
    }
}
