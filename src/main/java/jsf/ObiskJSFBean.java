package jsf;

import dao.ObiskDaoBean;
import dao.ZdravnikDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import vao.Obisk;
import vao.Pacient;
import vao.Zdravnik;
import java.time.LocalDateTime;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("demoObisk")
@SessionScoped
public class ObiskJSFBean implements Serializable {
    @EJB
    private ObiskDaoBean ejb;

    Logger log = Logger.getLogger(ZdravnikJSFBean.class.toString());


    private Obisk izbranObisk= new Obisk();

    private String izbranEmail;


    public Obisk getIzbranObisk() {
        return izbranObisk;
    }

    public void setIzbranObisk(Obisk izbranObisk) {
        this.izbranObisk = izbranObisk;
    }

    public void shraniObisk() throws Exception {
        ejb.save(izbranObisk);
    }




}
