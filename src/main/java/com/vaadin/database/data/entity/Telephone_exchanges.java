package com.vaadin.database.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "telephone_exchanges")
public class Telephone_exchanges {
    @Id
    @Column(name = "telephone_exchange_id")
    private int id;

    public Telephone_exchanges(int id) {
        this.id = id;
    }

    public Telephone_exchanges() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telephone_exchanges)) return false;
        Telephone_exchanges that = (Telephone_exchanges) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
