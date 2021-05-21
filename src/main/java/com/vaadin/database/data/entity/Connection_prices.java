package com.vaadin.database.data.entity;

import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "Connection_prices")
public class Connection_prices {

    @Id
    @SequenceGenerator(name = "connectionPrices_generator", sequenceName = "connectionPrices_seq", initialValue = 4)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connectionPrices_generator")
    private int connection_price_ID;

    private String Price_name;

    @Column(nullable = false)
    @Min(0)
    private int price;

    public Connection_prices(){}

    public int getConnection_price_ID() {
        return connection_price_ID;
    }

    public void setConnection_price_ID(int connection_price_ID) {
        this.connection_price_ID = connection_price_ID;
    }

    public String getPrice_name() {
        return Price_name;
    }

    public void setPrice_name(String price_name) {
        Price_name = price_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Connection_prices(int id, String name, @Min(0) int price) {
        this.connection_price_ID = id;
        this.Price_name = name;
        this.price = price;
    }



    @Override
    public String toString() {
        return "Connection_prices{" +
                "id=" + connection_price_ID +
                ", name='" + Price_name + '\'' +
                ", price=" + price +
                '}';
    }

}
