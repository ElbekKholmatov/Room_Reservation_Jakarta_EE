package dev.abdullo.roomreservation.dao;

import dev.abdullo.roomreservation.domains.User;
import dev.abdullo.roomreservation.enums.Roles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDAO extends BaseDAO<User, Long> {
    private static final ThreadLocal<UserDAO> USER_DAO_THREAD_LOCAL = ThreadLocal.withInitial(UserDAO::new);

    public static UserDAO getInstance() {
        return USER_DAO_THREAD_LOCAL.get();
    }

    public User findByUsername(String username) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select u from Users u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean changeRole(Integer id, String path) {
        int i;
        try (EntityManager em = emf.createEntityManager()) {
            Roles role = null;
            switch (path) {
                case "admin" -> role = Roles.ADMIN;
                case "user" -> role = Roles.USER;
            }

            em.getTransaction().begin();
            i = em.createQuery("update Users set role = :role where id = :id")
                    .setParameter("role", role)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
        return i > 0;
    }

    @Override
    public boolean deleteById(Long aLong) {
        boolean deleted;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            deleted = em.createQuery("delete from Users where id = :id")
                    .setParameter("id", aLong)
                    .executeUpdate() == 0;
            em.getTransaction().commit();
        }
        return deleted;
    }

    public List<User> usersList(Long adminId) {
        // todo check page and size
        List<User> users = new UserDAO().getPage(adminId);
        return users;
    }

    public List<User> getPage(Long adminId) {
        List<User> users = new ArrayList<>();
        TypedQuery<User> query;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            query = em.createQuery("from Users where id != :adminId", User.class);
            query.setParameter("adminId", adminId);
            em.getTransaction().commit();
            users = query.getResultList();
        }
        return users;
    }

    @Override
    public boolean changeDeleted(Long aLong, Boolean deleted) {
        int i;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            i = em.createQuery("update Users set deleted = :deleted where id = :id")
                    .setParameter("deleted", true)
                    .setParameter("id", aLong)
                    .executeUpdate();
            em.getTransaction().commit();
        }
        return i > 0;
    }

    @Override
    public List<User> findAll() {
        List<User> users;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            users = em.createQuery("select u from Users u", User.class)
                    .getResultList();
            em.getTransaction().commit();
        }
        return users;
    }
}
