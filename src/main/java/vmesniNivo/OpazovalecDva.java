package vmesniNivo;

import vao.Opazovalec;

public class OpazovalecDva implements Opazovalec {

    @Override
    public void akcija(String pacientMail, String zdravnikMail) throws Exception {
        MailSender.send(pacientMail, "admin@ezdravje.com", "Sprememba zdravnika", "Vas prejsnji zdravnik je bil " + zdravnikMail);
        System.out.println("stari zdravnik je bil"+ zdravnikMail);
    }
}
