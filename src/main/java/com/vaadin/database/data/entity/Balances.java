package com.vaadin.database.data.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Balances{

    @Id
    private int balance_id;

    @OneToOne()
    @JoinColumn(name = "caller_ID", referencedColumnName = "caller_id")
    private Callers caller_ID;

    @Column(nullable = false)
    @Min(0)
    private long long_distance_calls_debt;

    @Column(nullable = false)
    @Min(0)
    private long penalty_interest;

    private LocalDate subscription_debt_date;

    private LocalDate long_dist_debt_date;


}
