package jsf;

import dao.PacientDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import vao.Obisk;
import vao.Pacient;
import vao.Zdravnik;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
@Named("demoPacient")
@SessionScoped
public class PacientJSFBean implements Serializable {
    @EJB
    private PacientDao ejb;
    Logger log = Logger.getLogger(PacientJSFBean.class.toString());
    private Pacient izbranPacient = new Pacient();
    private String izbranEmail;
    private List<Pacient> vsiPacienti;
    private List<Pacient> pacientiBrezZdravnika;
    private long stPpoZ;
    private String mailOdZdr = "";

    private Obisk novObisk = new Obisk();


    public List<Pacient> getVsiPacienti() throws Exception {
        vsiPacienti = ejb.getAll();
        return vsiPacienti;
    }
    public List<Pacient> getPacientiBrezZdravnika() throws Exception {
        pacientiBrezZdravnika = ejb.getPacientiBrezZdravnika();
        return pacientiBrezZdravnika;
    }

    public long stPacientovPoZdravnikih(Zdravnik z){
        stPpoZ = ejb.stPacientovPoZdravnikih(z);
        return stPpoZ;
    }


    public void shraniPacienta() throws Exception {
        ejb.save(izbranPacient);
    }

    public void izbrisiPacienta(Pacient p) throws Exception {
        ejb.delete(p.getEmail());
    }

    public void setIzbranEmail(String email) throws Exception {
        izbranEmail = email;
        izbranPacient = ejb.find(email);
        if (izbranPacient == null)
            izbranPacient = new Pacient();
    }

    public String getIzbranEmail() {
        return izbranEmail;
    }

    public Pacient getIzbranPacient() {
        return izbranPacient;
    }

    public void setIzbranPacient(Pacient izbranPacient) {
        this.izbranPacient = izbranPacient;
    }

    public List<Pacient> katerePacienteImaZdravnik(Zdravnik z){
        return ejb.zdravnikoviPacienti(z);
    }


    public void pacientuShraniZdravnika(String pEmail, Zdravnik z) throws Exception {
        String zEmail = z.getEmail();
       ejb.shraniPacientaPoIzbiriZdravnika(pEmail,zEmail);
    }




    public void preveriCeJeZeImelZdravnika(Pacient p){
        Zdravnik zdravnik = p.getOsebniZdravnik();
        if (zdravnik != null) {
            String email = zdravnik.getEmail();
            ejb.setStariZdravnikMail(email);
        } else {
            ejb.setStariZdravnikMail(null);
        }

    }

    public void dobiMailOdZdr(String email){
        mailOdZdr = "ana";
        email = mailOdZdr;
        System.out.println(mailOdZdr);
    }

    public List<Obisk> obiskiZdravnika(Pacient pacient){
        return ejb.obiski(pacient);
    }






}
