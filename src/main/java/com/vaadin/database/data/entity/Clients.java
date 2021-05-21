package com.vaadin.database.data.entity;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Clients {

    @Id
    @SequenceGenerator(name = "clients_generator", sequenceName = "clients_seq", initialValue = 15)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "clients_generator")
    private Integer client_ID;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String second_name;

    @Column(nullable = false)
    private String middle_name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birth_date;

    @Column(nullable = false)
    private boolean is_deadhead;

    @OneToMany(mappedBy = "client_ID")
    private Set<Connection_requests> connection_requests;

    @OneToMany(mappedBy = "client_id")
    private Set<Callers> callers;

    public Clients(){}

    public Clients(int client_ID, String first_name, String second_name, String middle_name, String gender, LocalDate birth_date, boolean is_deadhead) {
        this.client_ID = client_ID;
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.is_deadhead = is_deadhead;
    }

    public String getIdStr(){
        return client_ID.toString();
    }
    public Integer getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public boolean getIs_deadhead() {
        return is_deadhead;
    }

    public void setIs_deadhead(boolean is_deadhead) {
        this.is_deadhead = is_deadhead;
    }


    @Override
    public String toString() {
        return String.format("%d ", this.client_ID);
    }

//    @Override
//    public String toString() {
//        return "Clients{" +
//                "client_ID=" + client_ID +
//                ", first_name='" + first_name + '\'' +
//                ", second_name='" + second_name + '\'' +
//                ", middle_name='" + middle_name + '\'' +
//                ", gender=" + gender +
//                ", birth_date=" + birth_date +
//                ", is_deadhead=" + is_deadhead +
//                '}';
//    }
}
