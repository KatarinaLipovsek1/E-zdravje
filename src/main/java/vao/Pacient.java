package vao;

import jakarta.ejb.Local;
import jakarta.persistence.*;
import vmesniNivo.OpazovalecDva;
import vmesniNivo.OpazovalecEna;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.now;

@Entity
public class Pacient implements Serializable {
    @Id
    private String email;
    private String ime;
    private String priimek;
    private String datumRojstva;
    private String posebnosti;
    @ManyToOne
    private Zdravnik osebniZdravnik;

    @Transient
    private static List<Opazovalec> opazovalci = new ArrayList<>();


    public Pacient(){}


    public void registrirajOpazovalca(Opazovalec o){
        opazovalci.add(o);
    }
    public void odstraniOpazovalca(Opazovalec o){
        opazovalci.remove(o);
    }
    public void obvestiOpazovalce(Pacient p, String mailNovegaZdravnika, String mailStaregaZdravnika) throws Exception {
        if (mailStaregaZdravnika!=null) {
            for(Opazovalec op : opazovalci) {
                if(op instanceof OpazovalecEna) {
                    op.akcija(p.getEmail(), mailNovegaZdravnika);
                }
                if(op instanceof OpazovalecDva) {
                    op.akcija(p.getEmail(), mailStaregaZdravnika);
                }
            }
        } else{
            for(Opazovalec op : opazovalci) {
                if(op instanceof OpazovalecEna){
                    op.akcija(p.getEmail(),p.getOsebniZdravnik().getEmail());
                }
            }
        }
    }

    public Zdravnik getOsebniZdravnik() {
        return osebniZdravnik;
    }

    public void setOsebniZdravnik(Zdravnik osebniZdravnik) {
        this.osebniZdravnik = osebniZdravnik;
    }

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
    public String getDatumRojstva() {

        return datumRojstva;
    }
    public void setDatumRojstva(String datumRojstva) {
        this.datumRojstva = datumRojstva;
    }
    public String getPosebnosti() {
        return posebnosti;
    }
    public void setPosebnosti(String posebnosti) {
        this.posebnosti = posebnosti;
    }
    public Pacient(String ime, String priimek, String email, String datumRojstva, String posebnosti, Zdravnik osebniZdravnik, List<Opazovalec> opazovalci) {
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.datumRojstva = datumRojstva;
        this.posebnosti = posebnosti;
        this.osebniZdravnik = osebniZdravnik;
        this.opazovalci=opazovalci;
    }
}
