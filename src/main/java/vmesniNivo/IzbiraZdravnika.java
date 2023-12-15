package vmesniNivo;

import jakarta.ejb.Remote;
import vao.Zdravnik;

@Remote
public interface IzbiraZdravnika {
    public boolean preveriKvoto(Zdravnik z, int stPacientov) throws Exception;
    public void posljiEmail(Boolean zdravnikProst, String pacientMail, String zdravnikMail) throws Exception;
    public String sayHello();


    }
