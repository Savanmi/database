package com.vaadin.database.data.entity;

import javax.persistence.*;

@Entity
public class Installing_possibilities {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int installing_possibilities_ID;

    @OneToOne
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID")
    private Address address_ID;

    @OneToOne
    @JoinColumn(name = "telephone_exchange_ID", referencedColumnName = "telephone_exchange_ID")
    private Telephone_exchanges telephone_exchange_ID;


    public Installing_possibilities(){}

    public Installing_possibilities(int installing_possibilities_ID, Address address_ID, Telephone_exchanges telephone_exchange_ID) {
        this.installing_possibilities_ID = installing_possibilities_ID;
        this.address_ID = address_ID;
        this.telephone_exchange_ID = telephone_exchange_ID;
    }

    public int getInstalling_possibilities_ID() {
        return installing_possibilities_ID;
    }

    public void setInstalling_possibilities_ID(int installing_possibilities_ID) {
        this.installing_possibilities_ID = installing_possibilities_ID;
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
        return "Installing_possibilities{" +
                "installing_possibilities_ID=" + installing_possibilities_ID +
                ", address_ID=" + address_ID +
                ", telephone_exchange_ID=" + telephone_exchange_ID +
                '}';
    }
}
