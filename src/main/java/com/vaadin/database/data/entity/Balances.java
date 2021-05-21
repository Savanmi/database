package com.vaadin.database.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Balances{

    @Id
    @SequenceGenerator(name = "balances_generator", sequenceName = "balances_seq", initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "balances_generator")
    private int balance_id;

    @OneToOne()
    @JoinColumn(name = "caller_ID", referencedColumnName = "caller_id")
    private Callers caller_ID;

    @Column(nullable = false)
    @Min(0)
    private int long_distance_calls_debt;

    @Column(nullable = false)
    @Min(0)
    private int penalty_interest;

    @Column(nullable = false)
    @Min(0)
    private int subscription_debt;

    private LocalDate subscription_debt_date;

    private LocalDate long_dist_debt_date;

    public Balances(){}

    public int getSubscription_debt() {
        return subscription_debt;
    }

    public void setSubscription_debt(int subscription_debt) {
        this.subscription_debt = subscription_debt;
    }

    public Balances(int balance_id, Callers caller_ID, @Min(0) int long_distance_calls_debt, @Min(0) int penalty_interest, @Min(0) int subscription_debt, LocalDate subscription_debt_date, LocalDate long_dist_debt_date) {
        this.balance_id = balance_id;
        this.caller_ID = caller_ID;
        this.long_distance_calls_debt = long_distance_calls_debt;
        this.penalty_interest = penalty_interest;
        this.subscription_debt = subscription_debt;
        this.subscription_debt_date = subscription_debt_date;
        this.long_dist_debt_date = long_dist_debt_date;
    }

    public int getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(int balance_id) {
        this.balance_id = balance_id;
    }

    public Callers getCaller_ID() {
        return caller_ID;
    }

    public void setCaller_ID(Callers caller_ID) {
        this.caller_ID = caller_ID;
    }

    public int getLong_distance_calls_debt() {
        return long_distance_calls_debt;
    }

    public void setLong_distance_calls_debt(int long_distance_calls_debt) {
        this.long_distance_calls_debt = long_distance_calls_debt;
    }

    public int getPenalty_interest() {
        return penalty_interest;
    }

    public void setPenalty_interest(int penalty_interest) {
        this.penalty_interest = penalty_interest;
    }

    public LocalDate getSubscription_debt_date() {
        return subscription_debt_date;
    }

    public void setSubscription_debt_date(LocalDate subscription_debt_date) {
        this.subscription_debt_date = subscription_debt_date;
    }

    public LocalDate getLong_dist_debt_date() {
        return long_dist_debt_date;
    }

    public void setLong_dist_debt_date(LocalDate long_dist_debt_date) {
        this.long_dist_debt_date = long_dist_debt_date;
    }

    @Override
    public String toString() {
        return "Balances{" +
                "balance_id=" + balance_id +
                ", caller_ID=" + caller_ID +
                ", long_distance_calls_debt=" + long_distance_calls_debt +
                ", penalty_interest=" + penalty_interest +
                ", subscription_debt=" + subscription_debt +
                ", subscription_debt_date=" + subscription_debt_date +
                ", long_dist_debt_date=" + long_dist_debt_date +
                '}';
    }
}
