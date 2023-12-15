package dao;

import jakarta.ejb.Local;
import vao.Pacient;
import vao.Zdravnik;

import java.util.List;

@Local
public interface ZdravnikDao {
    List<Zdravnik> getAll();
    Zdravnik find(String email);
    void save(Zdravnik z);
    void delete(String email);
}
