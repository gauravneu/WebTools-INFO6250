package com.example.onlinegamingsite.controller;

import com.example.onlinegamingsite.dao.GameDAO;
import com.example.onlinegamingsite.dao.GenreDAO;
import com.example.onlinegamingsite.dao.PlacedOrderDAO;
import com.example.onlinegamingsite.dao.UserDAO;
import com.example.onlinegamingsite.exception.GamingException;
import com.example.onlinegamingsite.pojo.Games;
import com.example.onlinegamingsite.pojo.PlacedOrder;
import com.example.onlinegamingsite.pojo.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class PlacedOrderController {

    private final String ADDPRODUCT = "addGame";
    private final String ADMINORDERS = "showorderstoadmin";
    private final String LOGINPAGE = "login-view";
    private final String ORDERS = "orders";
    private final String ORDER = "order";


    @PostMapping("/cancelorder.htm")
    public String saveGameGet(HttpServletRequest request, @ModelAttribute("games") Games games,
                              BindingResult result, SessionStatus status, UserDAO userDao,
                              GameDAO gameDAO, PlacedOrderDAO placedorderdao, GenreDAO genreDao, Model model) throws GamingException, IOException {


        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {

                    PlacedOrder po = placedorderdao.getPlacedOrder(Long.parseLong(request.getParameter("hiddenorderid")));

                    for (Games g : po.getOrders().keySet()) {
                        g.setQuantity(g.getQuantity() + po.getOrders().get(g));
                        gameDAO.updateGame(g);
                    }

                    placedorderdao.deletePlacedOrder(po);
                    List<PlacedOrder> orderList = userDao.getUser(po.getUser().getEmail()).getOrderedGamesList();
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    request.setAttribute("orderList", orderList);
                    request.setAttribute("ordercancel", "Order Cancelled!!");
                    return ORDERS;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


    @GetMapping("/checkorder.htm")
    public String checkOrder(HttpServletRequest request, @ModelAttribute("games") Games games,
                             BindingResult result, SessionStatus status, UserDAO userDao,
                             GameDAO gameDAO, PlacedOrderDAO placedorderdao, GenreDAO genreDao, Model model) throws GamingException, IOException {


        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    PlacedOrder po = placedorderdao.getPlacedOrder(Long.parseLong(request.getParameter("hiddenorderid")));
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    for (Games games1 : po.getOrders().keySet()) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }

                    request.setAttribute("currentorder", po);
                    return ADMINORDERS;
                } else {
                    PlacedOrder po = placedorderdao.getPlacedOrder(Long.parseLong(request.getParameter("hiddenorderid")));
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    for (Games games1 : po.getOrders().keySet()) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }
                    request.setAttribute("currentorder", po);
                    return ORDER;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }

    @PostMapping("/acceptorder.htm")
    public String acceptOrder(HttpServletRequest request, @ModelAttribute("games") Games games,
                              BindingResult result, SessionStatus status, UserDAO userDao,
                              GameDAO gameDAO, PlacedOrderDAO placedorderdao, GenreDAO genreDao, Model model) throws GamingException, IOException {


        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {

                    PlacedOrder po = placedorderdao.getPlacedOrder(Long.parseLong(request.getParameter("hiddenorderid")));
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    po.setDelivered(true);
                    placedorderdao.updatePlacedOrder(po);
                    for (Games games1 : po.getOrders().keySet()) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }
                    request.setAttribute("currentorder", po);
                    return ORDER;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


}
