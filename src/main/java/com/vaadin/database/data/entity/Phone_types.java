package com.vaadin.database.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class Phone_types {

    @Id
    private int phone_type_ID;

    private String type_name;

    @OneToMany(mappedBy = "phone_number_type_ID")
    private Set<Subscription_fees> subscription_fees;

    @OneToMany(mappedBy = "phone_type_ID")
    private Set<Phones> phones;

    public Phone_types(){}

    public Phone_types(int phone_type_ID, String type_name) {
        this.phone_type_ID = phone_type_ID;
        this.type_name = type_name;
    }

    public int getPhone_type_ID() {
        return phone_type_ID;
    }

    public void setPhone_type_ID(int phone_type_ID) {
        this.phone_type_ID = phone_type_ID;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "Phone_types{" +
                "phone_type_ID=" + phone_type_ID +
                ", type_name='" + type_name + '\'' +
                '}';
    }
}
