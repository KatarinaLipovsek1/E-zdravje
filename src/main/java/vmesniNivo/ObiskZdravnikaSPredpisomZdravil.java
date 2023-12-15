package vmesniNivo;

public class ObiskZdravnikaSPredpisomZdravil implements ObiskZdravnika {
    @Override
    public void obisk(String pacientMail, String zdravnikMail, String zdravila) {
        try {
            MailSender.send(pacientMail, "admin@ezdravje.com", "Obisk zdravnika - predpisana zdravila", "Vase predpisana zdravila so: " + zdravila);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
