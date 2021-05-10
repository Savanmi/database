package com.vaadin.database.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "Institutional_exchanges")
public class Institutional_exchanges {
    @Id
    @Column(name = "Telephone_exchange_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telephone_exchange_id")
    private Telephone_exchanges id;

    @Column(name = "Institution_name", nullable = false)
    private String name;

    public Institutional_exchanges(){}

    public Institutional_exchanges(Telephone_exchanges id, String name) {
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
        return "Institutional_exchanges{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
