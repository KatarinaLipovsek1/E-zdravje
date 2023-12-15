package vao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Zdravnik implements Serializable {
    @Id
    private String email;
    private String ime;
    private String priimek;
    private int kvotaPacientov;


    public Zdravnik(){}

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKvotaPacientov() {
        return kvotaPacientov;
    }

    public void setKvotaPacientov(int kvotaPacientov) {
        this.kvotaPacientov = kvotaPacientov;
    }

    public Zdravnik(String ime, String priimek, String email, int kvotaPacientov) {
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.kvotaPacientov = kvotaPacientov;
    }
}
