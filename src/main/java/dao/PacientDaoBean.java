package dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vao.Obisk;
import vao.Opazovalec;
import vao.Pacient;
import vao.Zdravnik;
import vmesniNivo.OpazovalecDva;
import vmesniNivo.OpazovalecEna;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PacientDaoBean implements PacientDao{
    Logger log=Logger.getLogger(PacientDao.class.toString());
    private List<Pacient> pacientiBrezZdravnika;

    private List<Pacient> pacienti;

    private int stPpoZ;

    String stariZdravnikMail;


    @PersistenceContext
    EntityManager em;

    public String getStariZdravnikMail() {
        return stariZdravnikMail;
    }

    public void setStariZdravnikMail(String stariZdravnikMail) {
        this.stariZdravnikMail = stariZdravnikMail;
    }



    @Override
    public List getAll() {
        log.info("DAO: get all");
        return em.createQuery("select p from Pacient p").getResultList();

    }

    @Override
    public Pacient find(String email) {
        log.info("DAO: finding " + email);
        return em.find(Pacient.class, email);
    }

    @Override
    public void save(Pacient p)  {
        log.info("DAO: saving "+p.getIme());

        Pacient existing = find(p.getEmail());
        if(existing != null) {
            log.info("DAO: editing "+p);
            existing.setIme(p.getIme());
            existing.setPriimek(p.getPriimek());
            existing.setDatumRojstva(p.getDatumRojstva());
            existing.setPosebnosti(p.getPosebnosti());
            existing.setOsebniZdravnik(p.getOsebniZdravnik());
            em.merge(existing);
        } else {
            em.persist(p);
        }

        Opazovalec enka = new OpazovalecEna();
        Opazovalec dva = new OpazovalecDva();

        p.registrirajOpazovalca(enka);
        p.registrirajOpazovalca(dva);
    }

    @Override
    public void delete(String email) {
        log.info("DAO: deleting "+email);
        Query query = em.createQuery("DELETE FROM Pacient p WHERE p.email = :email");
        query.setParameter("email", email);
        query.executeUpdate();
    }

    @Override
    public List<Pacient> zdravnikoviPacienti(Zdravnik z) {
        TypedQuery<Pacient> query = em.createQuery("SELECT p FROM Pacient p WHERE p.osebniZdravnik = :zdravnik", Pacient.class);
        query.setParameter("zdravnik", z);
        return query.getResultList();
    }

    public List<Obisk> obiski(Pacient pacient) {
        TypedQuery<Obisk> query = em.createQuery("SELECT o FROM Obisk o WHERE o.pacient = :pacient", Obisk.class);
        query.setParameter("pacient", pacient);
        return query.getResultList();
    }

    @Override
    public List<Pacient> getPacientiBrezZdravnika() {
        TypedQuery<Pacient> query = em.createQuery("SELECT p FROM Pacient p WHERE p.osebniZdravnik IS NULL", Pacient.class);
        return query.getResultList();
    }

    @Override
    public long stPacientovPoZdravnikih(Zdravnik z) {
        Query query = em.createQuery("SELECT COUNT(p) FROM Pacient p WHERE p.osebniZdravnik = :zdravnik");
        query.setParameter("zdravnik", z);
        long count = (long) query.getSingleResult();
        return count;
    }


    @Override
    public void shraniPacientaPoIzbiriZdravnika(String pEmail, String zEmail) throws Exception {
        Query queryPacient = em.createQuery("SELECT p FROM Pacient p WHERE p.email =: pEmail");
        queryPacient.setParameter("pEmail", pEmail);
        Pacient p = (Pacient) queryPacient.getSingleResult();
        Query queryZdravnik = em.createQuery("SELECT z FROM Zdravnik z WHERE z.email =: zEmail");
        queryZdravnik.setParameter("zEmail", zEmail);
        Zdravnik z = (Zdravnik) queryZdravnik.getSingleResult();
        p.setOsebniZdravnik(z);
        p.obvestiOpazovalce(p, z.getEmail(), stariZdravnikMail);
    }

}

