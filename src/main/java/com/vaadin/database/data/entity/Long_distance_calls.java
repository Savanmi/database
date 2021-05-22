package com.vaadin.database.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Long_distance_calls {
    @Id
    @SequenceGenerator(name = "ldc_generator", sequenceName = "ldc_seq", initialValue = 15)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ldc_generator")
    private int long_distance_call_ID;

    @OneToOne
    @JoinColumn(name = "source_phone_ID", referencedColumnName = "phone_ID", nullable = false)
    private Phones source_phone_ID;

    @OneToOne
    @JoinColumn(name = "destination_phone_ID", referencedColumnName = "phone_ID", nullable = false)
    private Phones destination_phone_ID;

    @Column(nullable = false)
    private LocalDateTime start_date;

    @Column(nullable = false)
    private LocalDateTime end_date;

    private Integer call_price;

    public Long_distance_calls(){}

    public Long_distance_calls(int long_distance_call_ID, Phones source_phone_ID, Phones destination_phone_ID, LocalDateTime start_date, LocalDateTime end_date, @Min(0) Integer call_price) {
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

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Integer getCall_price() {
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
