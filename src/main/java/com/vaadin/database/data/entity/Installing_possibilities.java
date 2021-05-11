package com.vaadin.database.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Installing_possibilities {

    @Id
    private int installing_possibilities_ID;

    @OneToOne
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID")
    private Address address_ID;

    @OneToOne
    @JoinColumn(name = "telephone_exchange_ID", referencedColumnName = "telephone_exchange_ID")
    private Telephone_exchanges telephone_exchange_ID;


}
