package vao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Obisk implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Zdravnik zdravnik;
    @ManyToOne
    private Pacient pacient;
    private Date datumInCas;
    private String posebnostiObiska;
    private String zdravila;

    public Obisk(){}

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Zdravnik getZdravnik() {
        return zdravnik;
    }

    public void setZdravnik(Zdravnik zdravnik) {
        this.zdravnik = zdravnik;
    }

    public Date getDatumInCas() {
        return datumInCas;
    }

    public void setDatumInCas(Date datumInCas) {
        this.datumInCas = datumInCas;
    }

    public String getPosebnostiObiska() {
        return posebnostiObiska;
    }

    public void setPosebnostiObiska(String posebnostiObiska) {
        if (posebnostiObiska != null && posebnostiObiska.trim().isEmpty()) {
            this.posebnostiObiska = null;
        } else {
            this.posebnostiObiska = posebnostiObiska;
        }
    }

    public String getZdravila() {
        return zdravila;
    }

    public void setZdravila(String zdravila) {
        if (zdravila != null && zdravila.trim().isEmpty()) {
            this.zdravila = null;
        } else {
            this.zdravila = zdravila;
        }
    }


}
