package com.example.onlinegamingsite.dao;

import com.example.onlinegamingsite.exception.GamingException;
import com.example.onlinegamingsite.pojo.Genre;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.query.Query;

import java.util.List;

public class GenreDAO extends DAO {
    public GenreDAO() {
    }

    public void saveGenre(Genre genre) throws GamingException {
        try {
            begin();
            getSession().save(genre);
            commit();
            close();
        } catch (HibernateException ex) {
            rollback();
            throw new GamingException("Could not add Genre", ex);
        }
    }

    public void updateGenre(Genre genre) throws GamingException {
        try {
            begin();
            getSession().update(genre);
            commit();
            close();
        } catch (HibernateException ex) {
            rollback();
            throw new GamingException("Could not update Genre", ex);
        }
    }

    public void deletegenre(Genre genre) throws GamingException {
        try {
            begin();
            getSession().delete(genre);
            commit();
            close();
        } catch (HibernateException ex) {
            rollback();
            throw new GamingException("Could not delete Genre", ex);
        }
    }

    public Genre getGenre(String name) throws NonUniqueResultException {
        begin();
        Query query = getSession().createQuery("from Genre where name =: name");
        query.setString("name", name);
        Genre genre = (Genre) query.uniqueResult();
        close();
        return genre;

    }

    public List<Genre> getAllGenre() {
        begin();
        Query query = getSession().createQuery("from Genre");
        List<Genre> genreList = query.list();
        close();
        return genreList;

    }


}
