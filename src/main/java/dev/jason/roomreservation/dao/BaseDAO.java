package dev.jason.roomreservation.dao;

import dev.jason.roomreservation.domains.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDAO<T extends BaseEntity, ID extends Serializable> {
    protected final EntityManagerFactory emf;
    //    protected EntityManager em;
    private final Class<T> persistenceClass;

    protected BaseDAO() {
        this.emf = Persistence.createEntityManagerFactory("room-reservation");
//        this.em = emf.createEntityManager();
        this.persistenceClass = getEntityClass();

    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public T save(T t) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
        return t;
    }

    public T findById(ID id) {
        T t = null;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            t = em.find(persistenceClass, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public boolean update(T t) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        }
        return true;
    }

    public boolean delete(T t) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }
        return true;
    }

    public boolean deleteById(ID id) {
        boolean delete;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            delete = em.createQuery("delete from " + persistenceClass.getSimpleName() + " t where t.id = :id")
                    .setParameter("id", id)
                    .executeUpdate() == 0;
            em.getTransaction().commit();
        }
        return delete;
    }

    public boolean changeDeleted(ID id, Boolean deleted) {
        int i;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            i = em.createQuery("update " + persistenceClass.getSimpleName() + " t set t.deleted = :deleted where t.id = :id")
                    .setParameter("deleted", deleted)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
        return i > 0;
    }

    public List<T> findAll() {
        List<T> list;
        TypedQuery<T> query = null;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            query = em.createQuery("from " + persistenceClass.getSimpleName(), persistenceClass);
            list = query.getResultList();
            em.getTransaction().commit();
        }
        return list;
    }


//    protected void begin() {
//        em.getTransaction().begin();
//    }
//
//    protected void commit() {
//        em.getTransaction().commit();
//    }
}