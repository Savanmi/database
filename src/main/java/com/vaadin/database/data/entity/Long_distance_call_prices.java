package com.vaadin.database.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Long_distance_call_prices {

    @Id
    @SequenceGenerator(name = "ldcPrices_generator", sequenceName = "ldcPrices_seq", initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ldcPrices_generator")
    private int Long_distance_call_price_ID;

    @Column(nullable = false, unique = true)
    private String Price_name;

    @Column(nullable = false)
    @Min(0)
    private long Price_per_minute;

    public Long_distance_call_prices(){}

    public Long_distance_call_prices(int long_distance_call_price_ID, String price_name, @Min(0) long price_per_minute) {
        Long_distance_call_price_ID = long_distance_call_price_ID;
        Price_name = price_name;
        Price_per_minute = price_per_minute;
    }

    public int getLong_distance_call_price_ID() {
        return Long_distance_call_price_ID;
    }

    public void setLong_distance_call_price_ID(int long_distance_call_price_ID) {
        Long_distance_call_price_ID = long_distance_call_price_ID;
    }

    public String getPrice_name() {
        return Price_name;
    }

    public void setPrice_name(String price_name) {
        Price_name = price_name;
    }

    public long getPrice_per_minute() {
        return Price_per_minute;
    }

    public void setPrice_per_minute(long price_per_minute) {
        Price_per_minute = price_per_minute;
    }

    @Override
    public String toString() {
        return "Long_distance_call_prices{" +
                "Long_distance_call_price_ID=" + Long_distance_call_price_ID +
                ", Price_name='" + Price_name + '\'' +
                ", Price_per_minute=" + Price_per_minute +
                '}';
    }
}
