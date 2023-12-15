package vmesniNivo;

public class StrategijaObiskaZdravnika {

        private ObiskZdravnika obiskZdravnika;

        public StrategijaObiskaZdravnika(ObiskZdravnika obiskZdravnika) {
            this.obiskZdravnika = obiskZdravnika;
        }

        public void setStrategijaObiskaZdravnika(ObiskZdravnika obiskZdravnika) {
            this.obiskZdravnika = obiskZdravnika;
        }

        public void obisk(String pacientMail, String zdravnikMail, String posebnostiInZdravila) {
            obiskZdravnika.obisk(pacientMail, zdravnikMail, posebnostiInZdravila);
        }

}
