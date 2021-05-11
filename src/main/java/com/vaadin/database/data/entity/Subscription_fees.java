package com.vaadin.database.data.entity;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Subscription_fees {

    @Id
    private int subscription_fee_ID;

    @ManyToOne
    @JoinColumn(name = "phone_number_type_ID", referencedColumnName = "phone_type_ID")
    private Phone_types phone_number_type_ID;

    @Column(nullable = false)
    @Min(0)
    @Max(1)
    private int is_deadhead;

    @Column(nullable = false)
    @Min(0)
    @Max(1)
    private int has_long_distance_calls;

    @Column(nullable = false)
    @Min(0)
    private long subscription_fee;

    public Subscription_fees(){}

    public Subscription_fees(int subscription_fee_ID, Phone_types phone_number_type_ID, @Min(0) @Max(1) int is_deadhead, @Min(0) @Max(1) int has_long_distance_calls, @Min(0) long subscription_fee) {
        this.subscription_fee_ID = subscription_fee_ID;
        this.phone_number_type_ID = phone_number_type_ID;
        this.is_deadhead = is_deadhead;
        this.has_long_distance_calls = has_long_distance_calls;
        this.subscription_fee = subscription_fee;
    }

    public int getSubscription_fee_ID() {
        return subscription_fee_ID;
    }

    public void setSubscription_fee_ID(int subscription_fee_ID) {
        this.subscription_fee_ID = subscription_fee_ID;
    }

    public Phone_types getPhone_number_type_ID() {
        return phone_number_type_ID;
    }

    public void setPhone_number_type_ID(Phone_types phone_number_type_ID) {
        this.phone_number_type_ID = phone_number_type_ID;
    }

    public int getIs_deadhead() {
        return is_deadhead;
    }

    public void setIs_deadhead(int is_deadhead) {
        this.is_deadhead = is_deadhead;
    }

    public int getHas_long_distance_calls() {
        return has_long_distance_calls;
    }

    public void setHas_long_distance_calls(int has_long_distance_calls) {
        this.has_long_distance_calls = has_long_distance_calls;
    }

    public long getSubscription_fee() {
        return subscription_fee;
    }

    public void setSubscription_fee(long subscription_fee) {
        this.subscription_fee = subscription_fee;
    }

    @Override
    public String toString() {
        return "Subscription_fees{" +
                "subscription_fee_ID=" + subscription_fee_ID +
                ", phone_number_type_ID=" + phone_number_type_ID +
                ", is_deadhead=" + is_deadhead +
                ", has_long_distance_calls=" + has_long_distance_calls +
                ", subscription_fee=" + subscription_fee +
                '}';
    }
}
