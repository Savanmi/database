package com.vaadin.database.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phones {

    @Id
    private int phone_ID;

    @ManyToOne
    @JoinColumn(name = "phone_number_ID", referencedColumnName = "phone_number_ID")
    private Phone_numbers phone_number_ID;

    @ManyToOne
    @JoinColumn(name = "phone_type_ID", referencedColumnName = "phone_type_ID")
    private Phone_types phone_type_ID;

    @ManyToOne
    @JoinColumn(name = "caller_ID", referencedColumnName = "caller_id")
    private Callers caller_ID;

    @ManyToOne
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID")
    private Address address_ID;

}
