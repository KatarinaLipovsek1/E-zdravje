package dao;

import jakarta.ejb.Local;
import vao.Obisk;
import vao.Pacient;
import vao.Zdravnik;

import java.util.List;

@Local
public interface PacientDao {
    List<Pacient> getAll();
    Pacient find(String email);
    void save(Pacient p);
    void delete(String email);
    List<Pacient> getPacientiBrezZdravnika();
    long stPacientovPoZdravnikih(Zdravnik z);

    List<Pacient> zdravnikoviPacienti(Zdravnik z);

    void shraniPacientaPoIzbiriZdravnika(String mailPacienta, String mailZdravnika) throws Exception;

    void setStariZdravnikMail(String stariMail);

    List<Obisk> obiski(Pacient pacient);

}

