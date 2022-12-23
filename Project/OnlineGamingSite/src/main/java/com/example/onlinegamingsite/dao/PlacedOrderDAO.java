package com.example.onlinegamingsite.dao;

import com.example.onlinegamingsite.pojo.Games;
import com.example.onlinegamingsite.pojo.PlacedOrder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PlacedOrderDAO extends DAO {
    public PlacedOrderDAO() {
    }

    public void deletePlacedOrder(PlacedOrder po) {
        begin();
        getSession().delete(po);
        commit();
        close();
    }

    public void updatePlacedOrder(PlacedOrder po) {
        begin();
        getSession().update(po);
        commit();
        close();
    }

    public PlacedOrder getPlacedOrder(long id) {
        begin();
        PlacedOrder po = getSession().get(PlacedOrder.class, id);
        commit();
        close();
        return po;
    }
    public List<PlacedOrder> getAllOrders() {
        begin();
        Query query = getSession().createQuery("from PlacedOrder");
        List<PlacedOrder> placedOrderList = query.list();
        commit();
        close();
        return placedOrderList;
    }

}
