package com.vaadin.database.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Public_phones {

    @Id
    private int public_phone_ID;

    @ManyToOne
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID", nullable = false)
    private Address address_ID;

    @ManyToOne
    @JoinColumn(name = "telephone_exchange_id", referencedColumnName = "telephone_exchange_id")
    private Telephone_exchanges telephone_exchange_ID;

    public Public_phones(){}

    public Public_phones(int public_phone_ID, Address address_ID, Telephone_exchanges telephone_exchange_ID) {
        this.public_phone_ID = public_phone_ID;
        this.address_ID = address_ID;
        this.telephone_exchange_ID = telephone_exchange_ID;
    }

    public int getPublic_phone_ID() {
        return public_phone_ID;
    }

    public void setPublic_phone_ID(int public_phone_ID) {
        this.public_phone_ID = public_phone_ID;
    }

    public Address getAddress_ID() {
        return address_ID;
    }

    public void setAddress_ID(Address address_ID) {
        this.address_ID = address_ID;
    }

    public Telephone_exchanges getTelephone_exchange_ID() {
        return telephone_exchange_ID;
    }

    public void setTelephone_exchange_ID(Telephone_exchanges telephone_exchange_ID) {
        this.telephone_exchange_ID = telephone_exchange_ID;
    }

    @Override
    public String toString() {
        return "Public_phones{" +
                "public_phone_ID=" + public_phone_ID +
                ", address_ID=" + address_ID +
                ", telephone_exchange_ID=" + telephone_exchange_ID +
                '}';
    }
}
