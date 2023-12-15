package vmesniNivo;

import dao.PacientDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import vao.Zdravnik;
import java.io.Serializable;


public class IzbiraZdravnikaBean implements Serializable, IzbiraZdravnika {

    private PacientDao ejb;

    public IzbiraZdravnikaBean(PacientDao ejb) {
        this.ejb = ejb;
    }

    public IzbiraZdravnikaBean(){}

    private boolean zdravnikProst;

    public boolean preveriKvoto(Zdravnik z, int stPacientov) throws Exception {
        if (z.getKvotaPacientov() < stPacientov){
            System.out.println("Zdravnik je zaseden");
            zdravnikProst = false;
        } else{
            System.out.println("Zdravnik je prost");
            zdravnikProst = true;
        }
        return zdravnikProst;
    }

    public void posljiEmail(Boolean zdravnikProst, String pacientMail, String zdravnikMail) throws Exception {
        if (zdravnikProst == true) {
            MailSender.send(pacientMail, "admin@ezdravje.com", "Nov zdravnik", "Uspesno ste si izbrali zdravnika.");
            MailSender.send(zdravnikMail, "admin@ezdravje.com", "Nov pacient", "Imate novega pacienta " + pacientMail);
        } else {
            MailSender.send(pacientMail, "admin@ezdravje.com", "Zdravnik je zaseden", "Zdravnik, ki ga zelite izbrati ima polno kvoto pacientov. Izberite drugega zdravnika.");

        }
    }

    @Override
    public String sayHello() {
        return "hello world";
    }


}

