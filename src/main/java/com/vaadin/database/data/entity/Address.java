package com.vaadin.database.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
public class Address {

    @Id
    private int address_ID;

    @Min(1)
    @Column(nullable = false)
    private int Zip_code;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    @Min(1)
    private int building_number;

    @OneToMany(mappedBy = "address_ID")
    private Set<Phones> phones;

    @OneToMany(mappedBy = "address_ID")
    private  Set<Public_phones> public_phones;

    @OneToMany(mappedBy = "address_ID")
    private Set<Connection_requests> connection_requests;

    @OneToOne(mappedBy = "address_ID")
    private Installing_possibilities installing_possibilities;


    public Address(){}

    public Address(int address_ID, @Min(1) int zip_code, String city, String region, String street, @Min(1) int building_number) {
        this.address_ID = address_ID;
        Zip_code = zip_code;
        this.city = city;
        this.region = region;
        this.street = street;
        this.building_number = building_number;
    }

    public int getAddress_ID() {
        return address_ID;
    }

    public void setAddress_ID(int address_ID) {
        this.address_ID = address_ID;
    }

    public int getZip_code() {
        return Zip_code;
    }

    public void setZip_code(int zip_code) {
        Zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(int building_number) {
        this.building_number = building_number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_ID=" + address_ID +
                ", Zip_code=" + Zip_code +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                ", building_number=" + building_number +
                '}';
    }
}
