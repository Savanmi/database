package com.vaadin.database.data.entity;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "Callers")
public class Callers {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int caller_id;

    private int telephone_exchange_id;

    private int client_id;

    private int is_blocked;

    private boolean has_long_distance_calls;

    public Callers() {}

    public Callers(int caller_id, int telephone_exchange_id, int client_id, int is_blocked, boolean has_long_distance_calls) {
        this.caller_id = caller_id;
        this.telephone_exchange_id = telephone_exchange_id;
        this.client_id = client_id;
        this.is_blocked = is_blocked;
        this.has_long_distance_calls = has_long_distance_calls;
    }

    public int getCaller_id() {
        return caller_id;
    }

    public void setCaller_id(int caller_id) {
        this.caller_id = caller_id;
    }

    public int getTelephone_exchange_id() {
        return telephone_exchange_id;
    }

    public void setTelephone_exchange_id(int telephone_exchange_id) {
        this.telephone_exchange_id = telephone_exchange_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(int is_blocked) {
        this.is_blocked = is_blocked;
    }

    public boolean isHas_long_distance_calls() {
        return has_long_distance_calls;
    }

    public void setHas_long_distance_calls(boolean has_long_distance_calls) {
        this.has_long_distance_calls = has_long_distance_calls;
    }

    @Override
    public String toString() {
        return "Callers{" +
                "caller_id=" + caller_id +
                ", telephone_exchange_id=" + telephone_exchange_id +
                ", client_id=" + client_id +
                ", is_blocked=" + is_blocked +
                ", has_long_distance_calls=" + has_long_distance_calls +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Callers)) return false;
        Callers callers = (Callers) o;
        return getCaller_id() == callers.getCaller_id() && getTelephone_exchange_id() == callers.getTelephone_exchange_id() && getClient_id() == callers.getClient_id() && isIs_blocked() == callers.isIs_blocked() && isHas_long_distance_calls() == callers.isHas_long_distance_calls();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCaller_id(), getTelephone_exchange_id(), getClient_id(), isIs_blocked(), isHas_long_distance_calls());
    }
}
