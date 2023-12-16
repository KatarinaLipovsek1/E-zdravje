package dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vao.Obisk;
import vmesniNivo.*;



@Stateless
public class ObiskDaoBean {

    @PersistenceContext
    EntityManager em;


    public void save(Obisk obisk){

        if(obisk.getPosebnostiObiska() == null && obisk.getZdravila() == null){
            StrategijaObiskaZdravnika strategija = new StrategijaObiskaZdravnika(new ObiskZdravnikaBrezPosebnosti());
            strategija.obisk(obisk.getPacient().getEmail(), obisk.getZdravnik().getEmail(), null);
        }
        else if(obisk.getPosebnostiObiska() != null && obisk.getZdravila() == null){
            StrategijaObiskaZdravnika strategija = new StrategijaObiskaZdravnika(new ObiskZdravnikaSPosebnostmi());
            strategija.obisk(obisk.getPacient().getEmail(), obisk.getZdravnik().getEmail(), obisk.getPosebnostiObiska());
        }
        else if(obisk.getPosebnostiObiska() == null && obisk.getZdravila() != null){
            StrategijaObiskaZdravnika strategija = new StrategijaObiskaZdravnika(new ObiskZdravnikaSPredpisomZdravil());
            strategija.obisk(obisk.getPacient().getEmail(), obisk.getZdravnik().getEmail(), obisk.getZdravila());
        }
        else if(obisk.getPosebnostiObiska() != null && obisk.getZdravila() != null){
            StrategijaObiskaZdravnika strategijaPosebnosti = new StrategijaObiskaZdravnika(new ObiskZdravnikaSPosebnostmi());
            StrategijaObiskaZdravnika strategijaZdravila = new StrategijaObiskaZdravnika(new ObiskZdravnikaSPredpisomZdravil());
            strategijaPosebnosti.obisk(obisk.getPacient().getEmail(), obisk.getZdravnik().getEmail(), obisk.getPosebnostiObiska());
            strategijaZdravila.obisk(obisk.getPacient().getEmail(), obisk.getZdravnik().getEmail(), obisk.getZdravila());
        }

        em.merge(obisk);
        em.flush();
    }
}
