package com.vaadin.database.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Long_distance_calls {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int long_distance_call_ID;

    @OneToOne
    @JoinColumn(name = "source_phone_ID", referencedColumnName = "phone_ID", nullable = false)
    private Phones source_phone_ID;

    @OneToOne
    @JoinColumn(name = "destination_phone_ID", referencedColumnName = "phone_ID", nullable = false)
    private Phones destination_phone_ID;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = false)
    private LocalDate end_date;

    private Integer call_price;

    public Long_distance_calls(){}

    public Long_distance_calls(int long_distance_call_ID, Phones source_phone_ID, Phones destination_phone_ID, LocalDate start_date, LocalDate end_date, @Min(0) Integer call_price) {
        this.long_distance_call_ID = long_distance_call_ID;
        this.source_phone_ID = source_phone_ID;
        this.destination_phone_ID = destination_phone_ID;
        this.start_date = start_date;
        this.end_date = end_date;
        this.call_price = call_price;
    }

    public int getLong_distance_call_ID() {
        return long_distance_call_ID;
    }

    public void setLong_distance_call_ID(int long_distance_call_ID) {
        this.long_distance_call_ID = long_distance_call_ID;
    }

    public Phones getSource_phone_ID() {
        return source_phone_ID;
    }

    public void setSource_phone_ID(Phones source_phone_ID) {
        this.source_phone_ID = source_phone_ID;
    }

    public Phones getDestination_phone_ID() {
        return destination_phone_ID;
    }

    public void setDestination_phone_ID(Phones destination_phone_ID) {
        this.destination_phone_ID = destination_phone_ID;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public long getCall_price() {
        return call_price;
    }

    public void setCall_price(Integer call_price) {
        this.call_price = call_price;
    }

    @Override
    public String toString() {
        return "Long_distance_calls{" +
                "long_distance_call_ID=" + long_distance_call_ID +
                ", source_phone_ID=" + source_phone_ID +
                ", destination_phone_ID=" + destination_phone_ID +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", call_price=" + call_price +
                '}';
    }
}
