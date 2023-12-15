package vmesniNivo;

public class ObiskZdravnikaSPosebnostmi implements ObiskZdravnika {

    @Override
    public void obisk(String pacientMail, String zdravnikMail, String posebnosti) {
        try {
            MailSender.send(pacientMail, "admin@ezdravje.com", "Obisk zdravnika - posebnosti", "Vase posebnosti so: " + posebnosti);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
