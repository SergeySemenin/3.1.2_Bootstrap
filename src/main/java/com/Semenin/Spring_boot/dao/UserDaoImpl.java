package com.Semenin.Spring_boot.dao;

import com.Semenin.Spring_boot.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public User getUserByName(String name) {
        return em.createQuery(
                        "SELECT u from User u WHERE u.name = :name", User.class).
                setParameter("name", name).getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        return em.createQuery(
                        "SELECT u from User u WHERE u.email = :email", User.class).
                setParameter("email", email).getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return em
                .createQuery("Select u from User u", User.class)
                .getResultList();
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        em.createQuery("DELETE FROM User u WHERE u.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public User getUserById(long id) {
        return em
                .createQuery("Select u from User u WHERE u.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

