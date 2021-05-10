package com.vaadin.database.data.entity;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "Connection_prices")
public class Connection_prices {
    @Id
    @Column(name = "Connection_price_ID")
    private int id;

    @Column(name = "Price_name", nullable = false, unique = true)
    private String name;

    @Column(name = "Price", nullable = false)
    @Min(0)
    private int price;

    public Connection_prices(){}

    public Connection_prices(int id, String name, @Min(0) int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Connection_prices{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection_prices)) return false;
        Connection_prices that = (Connection_prices) o;
        return getId() == that.getId() && getPrice() == that.getPrice() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }
}
