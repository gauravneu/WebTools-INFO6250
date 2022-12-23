package com.example.onlinegamingsite.dao;

import com.example.onlinegamingsite.exception.GamingException;
import com.example.onlinegamingsite.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public class UserDAO extends DAO {

    public UserDAO() {
    }

    public void save(User user) throws GamingException {
        try {
            begin();
            getSession().save(user);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new GamingException("Could not add user " + user.getEmail(), e);
        }
    }

    public void delete(User u) throws GamingException {
        begin();
        getSession().delete(u);
        commit();
        close();
    }

    public User getUser(String email) throws GamingException {
        begin();
        Query query = getSession().createQuery("from User where email =: email");
        query.setString("email", email);
        User user = (User) query.uniqueResult();
        close();
        return user;
    }

    public void updateUser(User user) throws GamingException {
        begin();
        getSession().merge(user);
        commit();
        close();
    }
}