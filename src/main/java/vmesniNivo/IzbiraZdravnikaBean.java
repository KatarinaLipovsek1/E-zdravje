package vmesniNivo;

import dao.PacientDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vao.Zdravnik;
import java.io.Serializable;

@Stateless
public class IzbiraZdravnikaBean implements Serializable, IzbiraZdravnika {

    private static final Logger LOGGER = LoggerFactory.getLogger(IzbiraZdravnikaBean.class);

    private PacientDao ejb;

    public IzbiraZdravnikaBean(PacientDao ejb) {
        this.ejb = ejb;
    }

    public IzbiraZdravnikaBean() {}

    private boolean zdravnikProst;

    public boolean preveriKvoto(Zdravnik z, int stPacientov) throws Exception {
        if (z.getKvotaPacientov() < stPacientov) {
            LOGGER.info("Zdravnik je zaseden");
            zdravnikProst = false;
        } else {
            LOGGER.info("Zdravnik je prost");
            zdravnikProst = true;
        }
        return zdravnikProst;
    }

    public void posljiEmail(Boolean zdravnikProst, String pacientMail, String zdravnikMail) throws Exception {
        String adminMail = "admin@ezdravje.com";
        if (zdravnikProst == true) {
            LOGGER.info("Sending emails for a free Zdravnik");
            MailSender.send(pacientMail, adminMail, "Nov zdravnik", "Uspesno ste si izbrali zdravnika.");
            MailSender.send(zdravnikMail, adminMail, "Nov pacient", "Imate novega pacienta " + pacientMail);
        } else {
            LOGGER.info("Sending emails for an occupied Zdravnik");
            MailSender.send(pacientMail, adminMail, "Zdravnik je zaseden", "Zdravnik, ki ga zelite izbrati ima polno kvoto pacientov. Izberite drugega zdravnika.");
        }
    }

    @Override
    public String sayHello() {
        return "hello world";
    }
}
