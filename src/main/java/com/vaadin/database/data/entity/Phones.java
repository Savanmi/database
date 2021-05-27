package com.vaadin.database.data.entity;

import javax.persistence.*;

@Entity
public class Phones {

    @Id
    @SequenceGenerator(name = "phones_generator", sequenceName = "phones_seq", initialValue = 15)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phones_generator")
    private Integer phone_ID;

    private Integer Apartment_number;

    @OneToOne
    @JoinColumn(name = "phone_number_ID", referencedColumnName = "phone_number_ID")
    private Phone_numbers phone_number_ID;

    @OneToOne
    @JoinColumn(name = "phone_type_ID", referencedColumnName = "phone_type_ID")
    private Phone_types phone_type_ID;

    @ManyToOne
    @JoinColumn(name = "caller_ID", referencedColumnName = "caller_id")
    private Callers caller_ID;

    @ManyToOne
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID")
    private Address address_ID;

    @OneToOne(fetch = FetchType.LAZY)
    private Long_distance_calls long_distance_calls;

    @OneToOne(fetch = FetchType.LAZY)
    private Long_distance_calls distance_calls;

    public Phones(){}

    public Phones(Integer phone_ID, Integer apartment_number, Phone_numbers phone_number_ID, Phone_types phone_type_ID, Callers caller_ID, Address address_ID, Long_distance_calls long_distance_calls, Long_distance_calls distance_calls) {
        this.phone_ID = phone_ID;
        Apartment_number = apartment_number;
        this.phone_number_ID = phone_number_ID;
        this.phone_type_ID = phone_type_ID;
        this.caller_ID = caller_ID;
        this.address_ID = address_ID;
        this.long_distance_calls = long_distance_calls;
        this.distance_calls = distance_calls;
    }

    public void setPhone_ID(Integer phone_ID) {
        this.phone_ID = phone_ID;
    }

    public Integer getApartment_number() {
        return Apartment_number;
    }

    public void setApartment_number(Integer apartment_number) {
        Apartment_number = apartment_number;
    }

    public Integer getPhone_ID() {
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

    public String getIdStr(){return phone_ID.toString();}


    @Override
    public String toString() {
        return String.format("%d ", this.phone_ID);
    }
//    @Override
//    public String toString() {
//        return "Phones{" +
//                "phone_ID=" + phone_ID +
//                ", phone_number_ID=" + phone_number_ID +
//                ", phone_type_ID=" + phone_type_ID +
//                ", caller_ID=" + caller_ID +
//                ", address_ID=" + address_ID +
//                '}';
//    }
}
