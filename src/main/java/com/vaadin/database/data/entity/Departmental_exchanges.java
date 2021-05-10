package com.vaadin.database.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "Departmental_exchanges")
public class Departmental_exchanges {
    @Id
    @Column(name = "Telephone_exchange_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telephone_exchange_id")
    private Telephone_exchanges id;

    @Column(name = "Department_name", nullable = false)
    private String name;

    public Departmental_exchanges(){}


    public Departmental_exchanges(Telephone_exchanges id, String name) {
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
        return "Departmental_exchanges{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
