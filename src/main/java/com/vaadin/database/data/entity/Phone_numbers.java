package com.vaadin.database.data.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Phone_numbers {

    @Id
    @SequenceGenerator(name = "phoneNumbers_generator", sequenceName = "phoneNumbers_seq", initialValue = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phoneNumbers_generator")
    private Integer phone_number_ID;

    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "telephone_exchange_id", referencedColumnName = "telephone_exchange_id")
    private Telephone_exchanges telephone_exchange_id;

    @OneToOne(fetch = FetchType.LAZY)
    private Phones phones;

    public Phone_numbers(){}

    public Phone_numbers(Integer phone_number_ID, String phone_number, Telephone_exchanges telephone_exchange_id) {
        this.phone_number_ID = phone_number_ID;
        this.phone_number = phone_number;
        this.telephone_exchange_id = telephone_exchange_id;
    }

    public Integer getPhone_number_ID() {
        return phone_number_ID;
    }

    public void setPhone_number_ID(Integer phone_number_ID) {
        this.phone_number_ID = phone_number_ID;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Telephone_exchanges getTelephone_exchange_id() {
        return telephone_exchange_id;
    }

    public void setTelephone_exchange_id(Telephone_exchanges telephone_exchange_id) {
        this.telephone_exchange_id = telephone_exchange_id;
    }

    public String getIdStr(){return phone_number_ID.toString();}


    @Override
    public String toString() {
        return String.format("%d ", this.phone_number_ID);
    }

//    public String toString() {
//        return "Phone_numbers{" +
//                "phone_number_ID=" + phone_number_ID +
//                ", phone_number='" + phone_number + '\'' +
//                ", telephone_exchange_id=" + telephone_exchange_id +
//                '}';
//    }
}
