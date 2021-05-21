package com.vaadin.database.data.entity;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Callers")
public class Callers {
    @Id
    @SequenceGenerator(name = "callers_generator", sequenceName = "callers_seq", initialValue = 15)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "callers_generator")
    private Integer caller_id;

    @ManyToOne
    @JoinColumn(name = "telephone_exchange_ID", referencedColumnName = "Telephone_exchange_ID")
    private Telephone_exchanges telephone_exchange_ID;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Clients client_id;

    private boolean is_blocked;

    private boolean has_long_distance_calls;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Phones> phones;

    @OneToOne(fetch = FetchType.LAZY)
    private Balances balances;

    public Callers(Integer caller_id, Telephone_exchanges telephone_exchange_ID, Clients client_id, boolean is_blocked, boolean has_long_distance_calls, Set<Phones> phones, Balances balances) {
        this.caller_id = caller_id;
        this.telephone_exchange_ID = telephone_exchange_ID;
        this.client_id = client_id;
        this.is_blocked = is_blocked;
        this.has_long_distance_calls = has_long_distance_calls;
        this.phones = phones;
        this.balances = balances;
    }

    public Callers() {}

    public Integer getCaller_id() {
        return caller_id;
    }

    public void setCaller_id(Integer caller_id) {
        this.caller_id = caller_id;
    }

    public Telephone_exchanges getTelephone_exchange_ID() {
        return telephone_exchange_ID;
    }

    public void setTelephone_exchange_ID(Telephone_exchanges telephone_exchange_ID) {
        this.telephone_exchange_ID = telephone_exchange_ID;
    }

    public Clients getClient_id() {
        return client_id;
    }

    public void setClient_id(Clients client_id) {
        this.client_id = client_id;
    }

    public boolean getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public boolean isHas_long_distance_calls() {
        return has_long_distance_calls;
    }

    public void setHas_long_distance_calls(boolean has_long_distance_calls) {
        this.has_long_distance_calls = has_long_distance_calls;
    }

    public Set<Phones> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phones> phones) {
        this.phones = phones;
    }

    public Balances getBalances() {
        return balances;
    }

    public void setBalances(Balances balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return String.format("%d ", this.caller_id);
    }


    public String getIdStr(){
        return caller_id.toString();
    }
}
