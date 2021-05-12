package com.vaadin.database.data.entity;

import javax.persistence.*;

@Entity
public class Phones {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @OneToOne(mappedBy = "source_phone_ID")
    private Long_distance_calls long_distance_calls;

    @OneToOne(mappedBy = "destination_phone_ID")
    private Long_distance_calls distance_calls;

    public Phones(){}

    public Phones(int phone_ID, Phone_numbers phone_number_ID, Phone_types phone_type_ID, Callers caller_ID, Address address_ID) {
        this.phone_ID = phone_ID;
        this.phone_number_ID = phone_number_ID;
        this.phone_type_ID = phone_type_ID;
        this.caller_ID = caller_ID;
        this.address_ID = address_ID;
    }

    public int getPhone_ID() {
        return phone_ID;
    }

    public void setPhone_ID(int phone_ID) {
        this.phone_ID = phone_ID;
    }

    public Phone_numbers getPhone_number_ID() {
        return phone_number_ID;
    }

    public void setPhone_number_ID(Phone_numbers phone_number_ID) {
        this.phone_number_ID = phone_number_ID;
    }

    public Phone_types getPhone_type_ID() {
        return phone_type_ID;
    }

    public void setPhone_type_ID(Phone_types phone_type_ID) {
        this.phone_type_ID = phone_type_ID;
    }

    public Callers getCaller_ID() {
        return caller_ID;
    }

    public void setCaller_ID(Callers caller_ID) {
        this.caller_ID = caller_ID;
    }

    public Address getAddress_ID() {
        return address_ID;
    }

    public void setAddress_ID(Address address_ID) {
        this.address_ID = address_ID;
    }

    @Override
    public String toString() {
        return "Phones{" +
                "phone_ID=" + phone_ID +
                ", phone_number_ID=" + phone_number_ID +
                ", phone_type_ID=" + phone_type_ID +
                ", caller_ID=" + caller_ID +
                ", address_ID=" + address_ID +
                '}';
    }
}
