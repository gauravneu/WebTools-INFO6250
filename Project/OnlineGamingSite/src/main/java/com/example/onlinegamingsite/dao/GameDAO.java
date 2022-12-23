package com.example.onlinegamingsite.dao;

import com.example.onlinegamingsite.exception.GamingException;
import com.example.onlinegamingsite.pojo.Games;
import com.example.onlinegamingsite.pojo.PlacedOrder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GameDAO extends DAO {

    public GameDAO() {
    }

    public void saveGame(Games games) throws GamingException {
        try {
            begin();
            getSession().save(games);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new GamingException("Could not add user " + games.getGenre(), e);
        }
    }

    public Games getGame(String name) {
        begin();
        Query query = getSession().createQuery("from Games where name =: name");
        query.setString("name", name);
        commit();
        Games game = (Games) query.uniqueResult();
        close();
        return game;

    }

    public List<Games> getAllGames() {
        begin();
        Query query = getSession().createQuery("from Games");
        commit();
        List<Games> list = query.list();
        close();
        return list;
    }

    public List<Games> getAllGamesWithValues() {
        List<Games> allGamesList = new ArrayList<>();
        for (Games games1 : getAllGames()) {
            byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
            String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            games1.setBase64Encoded(base64Encoded);
            allGamesList.add(games1);
        }
        return allGamesList;
    }

    public List<Games> getGamesByGenre(String genre) {
        begin();
        Query query = getSession().createQuery("from Games");
        List<Games> gamesList = query.list();
        List<Games> gameListFinal = new ArrayList<>();
        for (Games games : gamesList) {
            if (games.getGenre().getName().equals(genre)) {
                gameListFinal.add(games);
            }
        }
        commit();
        close();
        return gameListFinal;
    }

    public List<Games> getGamesByName(String gName) {
        begin();
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Games> query = builder.createQuery(Games.class);
        Root<Games> root = query.from(Games.class);
        query.select(root);
        query.where(builder.like(builder.upper(root.get("name")), "%" + gName.toUpperCase(Locale.ROOT) + "%"));

        List<Games> gamesList = getSession().createQuery(query).getResultList();
        close();
        return gamesList;

    }

    public List<Games> getGamesByPriceRange(String priceRange) {
        begin();
        double lowRange = Double.parseDouble(priceRange.split("-")[0]);
        double highRange = Double.parseDouble(priceRange.split("-")[1]);
        Query query = getSession().createQuery("from Games where price >: lowRange and price <=: highRange");
        query.setString("lowRange", String.valueOf(lowRange));
        query.setString("highRange", String.valueOf(highRange));
        List<Games> gamesList = query.list();
        commit();
        close();
        return gamesList;
    }

    public void updateGame(Games game) {
        begin();
        getSession().update(game);
        commit();
        close();

    }

    public void addOrder(PlacedOrder p1) {
        begin();
        Session s1 = getSession();
        s1.save(p1);
        commit();
        close();

    }

}