package jsf;

import dao.ZdravnikDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import vao.Zdravnik;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("demoZdravnik")
@SessionScoped
public class ZdravnikJSFBean implements Serializable {
    @EJB
    private ZdravnikDao ejb;

    Logger log = Logger.getLogger(ZdravnikJSFBean.class.toString());


    private Zdravnik izbranZdravnik = new Zdravnik();

    private String izbranEmail;

    private List<Zdravnik> vsiZdravniki;



    public List<Zdravnik> getVsiZdravniki() throws Exception {
        vsiZdravniki = ejb.getAll();
        return vsiZdravniki;
    }

    public String shraniZdravnika() throws Exception {
        ejb.save(izbranZdravnik);
        return "vsi";
    }

    public void izbrisiZdravnika(Zdravnik z) throws Exception {
        ejb.delete(z.getEmail());
    }


    public void setIzbranEmail(String email) throws Exception {
        izbranEmail = email;
        izbranZdravnik = ejb.find(email);
        if (izbranZdravnik == null)
            izbranZdravnik = new Zdravnik();
    }

    public String getIzbranEmail() {
        return izbranEmail;
    }

    public Zdravnik getIzbranZdravnik() {
        return izbranZdravnik;
    }

    public void setIzbranZdravnik(Zdravnik izbranZdravnik) {
        this.izbranZdravnik = izbranZdravnik;
    }




}
