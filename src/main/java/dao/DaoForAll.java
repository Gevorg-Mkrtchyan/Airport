package dao;

import java.util.Set;

public interface DaoForAll <T> {

    T findByID(int id);

    void create(T t);

    void deleteById(int id);

    void update(int id, T t);

    Set<T> findAll();

    Set<T> get(int offset, int perPage, String sort);
}
