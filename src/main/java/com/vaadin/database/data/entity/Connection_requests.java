package com.vaadin.database.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Connection_requests {

    @Id
    private int connection_request_ID;

    @ManyToOne
    @JoinColumn(name = "telephone_exchange_ID", referencedColumnName = "Telephone_exchange_ID")
    private Telephone_exchanges telephone_exchange_ID;

    @ManyToOne
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID")
    private Address address_ID;

    @ManyToOne
    @JoinColumn(name = "client_ID", referencedColumnName = "client_ID")
    private Clients client_ID;

    @Column(nullable = false)
    @Min(1)
    private int apartment_number;

    @Column(nullable = false)
    private LocalDate request_date;

    public Connection_requests(){}

    public Connection_requests(int connection_request_ID, Telephone_exchanges telephone_exchange_ID, Address address_ID, Clients client_ID, @Min(1) int apartment_number, LocalDate request_date) {
        this.connection_request_ID = connection_request_ID;
        this.telephone_exchange_ID = telephone_exchange_ID;
        this.address_ID = address_ID;
        this.client_ID = client_ID;
        this.apartment_number = apartment_number;
        this.request_date = request_date;
    }

    public int getConnection_request_ID() {
        return connection_request_ID;
    }

    public void setConnection_request_ID(int connection_request_ID) {
        this.connection_request_ID = connection_request_ID;
    }

    public Telephone_exchanges getTelephone_exchange_ID() {
        return telephone_exchange_ID;
    }

    public void setTelephone_exchange_ID(Telephone_exchanges telephone_exchange_ID) {
        this.telephone_exchange_ID = telephone_exchange_ID;
    }

    public Address getAddress_ID() {
        return address_ID;
    }

    public void setAddress_ID(Address address_ID) {
        this.address_ID = address_ID;
    }

    public Clients getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(Clients client_ID) {
        this.client_ID = client_ID;
    }

    public int getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(int apartment_number) {
        this.apartment_number = apartment_number;
    }

    public LocalDate getRequest_date() {
        return request_date;
    }

    public void setRequest_date(LocalDate request_date) {
        this.request_date = request_date;
    }

    @Override
    public String toString() {
        return "Connection_requests{" +
                "connection_request_ID=" + connection_request_ID +
                ", telephone_exchange_ID=" + telephone_exchange_ID +
                ", address_ID=" + address_ID +
                ", client_ID=" + client_ID +
                ", apartment_number=" + apartment_number +
                ", request_date=" + request_date +
                '}';
    }
}
