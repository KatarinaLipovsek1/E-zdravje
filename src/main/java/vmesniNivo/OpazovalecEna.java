package vmesniNivo;

import vao.Opazovalec;

public class OpazovalecEna implements Opazovalec {

    @Override
    public void akcija(String pacientMail, String zdravnikMail) throws Exception {
        MailSender.send(pacientMail, "admin@ezdravje.com", "Ime vasega novega zdravnika", "Vas nov zdravnik je " + zdravnikMail);
        System.out.println("nov zdravnik je "+ zdravnikMail);
    }

}
