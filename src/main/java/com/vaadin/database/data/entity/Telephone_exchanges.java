package com.vaadin.database.data.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "telephone_exchanges")
public class Telephone_exchanges {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int telephone_exchange_id;

    private String exchange_name;

    private String exchange_type;

    @OneToMany(mappedBy = "telephone_exchange_id")
    private Set<Phone_numbers> phone_numbers;

    @OneToMany(mappedBy = "telephone_exchange_ID")
    private Set<Public_phones> public_phones;

    @OneToMany(mappedBy = "telephone_exchange_ID")
    private Set<Connection_requests> connection_requests;

    @OneToOne(mappedBy = "telephone_exchange_ID")
    private Installing_possibilities installing_possibilities;

    public Telephone_exchanges(){}

    public Telephone_exchanges(int telephone_exchange_id, String exchange_name, String exchange_type) {
        this.telephone_exchange_id = telephone_exchange_id;
        this.exchange_name = exchange_name;
        this.exchange_type = exchange_type;
    }

    public int getTelephone_exchange_id() {
        return telephone_exchange_id;
    }

    public void setTelephone_exchange_id(int telephone_exchange_id) {
        this.telephone_exchange_id = telephone_exchange_id;
    }

    public String getExchange_name() {
        return exchange_name;
    }

    public void setExchange_name(String exchange_name) {
        this.exchange_name = exchange_name;
    }

    public String getExchange_type() {
        return exchange_type;
    }

    public void setExchange_type(String exchange_type) {
        this.exchange_type = exchange_type;
    }

    @Override
    public String toString() {
        return "Telephone_exchanges{" +
                "telephone_exchange_id=" + telephone_exchange_id +
                ", exchange_name='" + exchange_name + '\'' +
                ", exchange_type='" + exchange_type + '\'' +
                '}';
    }
}
