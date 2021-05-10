package com.vaadin.database.data.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "City_exchanges")
public class City_exchanges {

    @Id
    @Column(name = "Telephone_exchange_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telephone_exchange_id")
    private Telephone_exchanges id;

    @Column(name = "City_name", nullable = false)
    private String name;

    public City_exchanges(){}

    public City_exchanges(Telephone_exchanges id, String name) {
        this.id = id;
        this.name = name;
    }

    public Telephone_exchanges getId() {
        return id;
    }

    public void setId(Telephone_exchanges id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City_exchanges{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
