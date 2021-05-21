package com.vaadin.database.data.entity;

import javax.persistence.*;

@Entity
public class Installing_possibilities {

    @Id
    @SequenceGenerator(name = "installingPossibilities_generator", sequenceName = "installingPossibilities_seq", initialValue = 30)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "installingPossibilities_generator")
    private int INSTALLING_POSSIBILITY_ID;

    @OneToOne
    @JoinColumn(name = "address_ID", referencedColumnName = "address_ID")
    private Address address_ID;

    @OneToOne
    @JoinColumn(name = "telephone_exchange_ID", referencedColumnName = "telephone_exchange_ID")
    private Telephone_exchanges telephone_exchange_ID;


    public Installing_possibilities(){}

    public Installing_possibilities(int installing_possibilities_ID, Address address_ID, Telephone_exchanges telephone_exchange_ID) {
        this.INSTALLING_POSSIBILITY_ID = installing_possibilities_ID;
        this.address_ID = address_ID;
        this.telephone_exchange_ID = telephone_exchange_ID;
    }

    public int getINSTALLING_POSSIBILITY_ID() {
        return INSTALLING_POSSIBILITY_ID;
    }

    public void setINSTALLING_POSSIBILITY_ID(int installing_possibilities_ID) {
        this.INSTALLING_POSSIBILITY_ID = installing_possibilities_ID;
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
                "installing_possibilities_ID=" + INSTALLING_POSSIBILITY_ID +
                ", address_ID=" + address_ID +
                ", telephone_exchange_ID=" + telephone_exchange_ID +
                '}';
    }
}
