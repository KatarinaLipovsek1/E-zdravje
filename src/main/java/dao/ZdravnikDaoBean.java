package dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import vao.Pacient;
import vao.Zdravnik;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ZdravnikDaoBean implements ZdravnikDao{
        Logger log=Logger.getLogger(PacientDao.class.toString());
        @PersistenceContext
        EntityManager em;

        @Override
        public List getAll() {
            log.info("DAO: get all");
            return em.createQuery("select z from Zdravnik z").getResultList();
        }

        @Override
        public Zdravnik find(String email)  {
            log.info("DAO: finding "+email);
            return em.find(Zdravnik.class, email);
        }

        @Override
        public void save(Zdravnik z) {
            log.info("DAO: saving "+z);
            Zdravnik existing = find(z.getEmail());
            if(existing != null) {
                log.info("DAO: editing "+z);
                existing.setIme(z.getIme());
                existing.setPriimek(z.getPriimek());
                existing.setKvotaPacientov(z.getKvotaPacientov());
                em.merge(existing);
            } else {
                em.persist(z);
            }
        }


    @Override
        public void delete(String email) {
            log.info("DAO: deleting "+email);
            Query query = em.createQuery("DELETE FROM Zdravnik z WHERE z.email = :email");
            query.setParameter("email", email);
            query.executeUpdate();

        }


}



