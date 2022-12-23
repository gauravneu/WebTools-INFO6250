package com.example.onlinegamingsite.controller;

import com.example.onlinegamingsite.dao.GameDAO;
import com.example.onlinegamingsite.dao.GenreDAO;
import com.example.onlinegamingsite.dao.PlacedOrderDAO;
import com.example.onlinegamingsite.dao.UserDAO;
import com.example.onlinegamingsite.exception.GamingException;
import com.example.onlinegamingsite.pojo.Games;
import com.example.onlinegamingsite.pojo.Genre;
import com.example.onlinegamingsite.pojo.PlacedOrder;
import com.example.onlinegamingsite.pojo.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private final String ADDPRODUCT = "addGame";
    private final String PRODUCTADDED = "productAdded";
    private final String GAMEPAGE = "seeGamePage";
    private final String LOGINPAGE = "login-view";
    private final String SHOPPING = "shopping-view";
    private final String CART = "cart";
    private final String CHECKOUT = "checkout";
    private final String ORDERS = "orders";
    private final String ADMINORDERS = "adminorders";

    @PostMapping("/savegame.htm")
    public String savegame(HttpServletRequest request, @ModelAttribute("games") Games games,
                           BindingResult result, SessionStatus status,
                           GameDAO gameDAO, GenreDAO genreDAO, Model model, @RequestParam("gameImage") MultipartFile img)
            throws GamingException, IOException {

        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (!httpSession.getAttribute("role").equals("admin")) {
                    List<Games> allGamesList = gameDAO.getAllGames();
                    for (Games games1 : allGamesList) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }
                    request.setAttribute("allGenres", genreDAO.getAllGenre());
                    request.setAttribute("allGames", allGamesList);
                    return SHOPPING;
                } else {

                    if (null != gameDAO.getGame(games.getName())) {
                        request.setAttribute("ExistingGame", "GameAlreadyExists");
                        return ADDPRODUCT;
                    }
                    byte[] bytes = img.getBytes();
                    games.setGameImage(bytes);
                    byte[] encodeBase64 = Base64.encodeBase64(bytes);
                    String base64Encoded = new String(encodeBase64, "UTF-8");
                    games.setBase64Encoded(base64Encoded);
                    games.setPrice(Double.parseDouble(request.getParameter("gamePrice")));
                    games.setQuantity(Integer.parseInt(request.getParameter("gameQuantity")));
                    Genre genre = genreDAO.getGenre(request.getParameter("gamegenre"));
                    games.setGenre(genre);
                    gameDAO.saveGame(games);
                    status.setComplete();
                    return PRODUCTADDED;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


    @GetMapping("/savegame.htm")
    public String saveGameGet(HttpServletRequest request, @ModelAttribute("games") Games games,
                              BindingResult result, SessionStatus status,
                              GameDAO gameDAO, GenreDAO genreDao, Model model) throws GamingException, IOException {


        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {
                    List<Games> allGamesList = gameDAO.getAllGames();
                    for (Games games1 : allGamesList) {
                        //              System.out.println(" lo " + games1.getName());
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    request.setAttribute("allGames", allGamesList);
                    return SHOPPING;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


    @GetMapping("/guest.htm")
    public String guestView(HttpServletRequest request, @ModelAttribute("games") Games games,
                            BindingResult result, SessionStatus status,
                            GameDAO gameDAO, GenreDAO genreDao, Model model) throws GamingException, IOException {


        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {
                    List<Games> allGamesList = gameDAO.getAllGames();
                    for (Games games1 : allGamesList) {
                        //              System.out.println(" lo " + games1.getName());
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    request.setAttribute("allGames", allGamesList);
                    return SHOPPING;
                }
            }

        }
        List<Games> allGamesList = gameDAO.getAllGames();
        for (Games games1 : allGamesList) {
            //              System.out.println(" lo " + games1.getName());
            byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");
            games1.setBase64Encoded(base64Encoded);
        }
        request.setAttribute("allGenres", genreDao.getAllGenre());
        request.setAttribute("allGames", allGamesList);
        return SHOPPING;
    }


    @GetMapping("/addGame.htm")
    public String addMoreGames(HttpServletRequest request, @ModelAttribute("games") Games games,
                               BindingResult result, SessionStatus status,
                               GameDAO gameDAO, GenreDAO genreDao, Model model) throws GamingException, UnsupportedEncodingException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {
                    List<Games> allGamesList = gameDAO.getAllGames();
                    for (Games games1 : allGamesList) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    request.setAttribute("allGames", allGamesList);
                    return SHOPPING;
                }
            }
            return ADDPRODUCT;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }

    @GetMapping("/searchgame.htm")
    public String searchGame(HttpServletRequest request, @ModelAttribute("games") Games games,
                             BindingResult result, SessionStatus status,
                             GameDAO gameDAO, GenreDAO genreDao, Model model,
                             @RequestParam("gameName") String gName)
            throws GamingException, UnsupportedEncodingException {

        //check session here
        Games game1 = gameDAO.getGame(gName);
        if (null != game1) {
            //     System.out.println(gName);
            byte[] encodeBase64 = Base64.encodeBase64(game1.getGameImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");
            game1.setBase64Encoded(base64Encoded);
            model.addAttribute("game", game1);
            return GAMEPAGE;
        }
        List<Games> allGamesList = gameDAO.getAllGames();
        for (Games games1 : allGamesList) {
            //        System.out.println(" lo " + games1.getName());
            byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");
            games1.setBase64Encoded(base64Encoded);
        }
        request.setAttribute("allGenres", genreDao.getAllGenre());
        request.setAttribute("allGames", allGamesList);
        return SHOPPING;
    }


    @PostMapping("/addGameToCart.htm")
    public String updateCart(HttpServletRequest request, @ModelAttribute("games") Games games,
                             BindingResult result, SessionStatus status,
                             GameDAO gameDAO, GenreDAO genreDao, UserDAO userdao, Model model,
                             @RequestParam("hiddenGame") String gName) throws GamingException, UnsupportedEncodingException {
        //    System.out.println("line 162");
        HttpSession httpSession = request.getSession(false);
        User user = (User) httpSession.getAttribute("user");
        User user1 = userdao.getUser(user.getEmail());
        int quantityAdded = 0;
        boolean update = true;
        boolean add = true;
        if (null != request.getParameter("gameQuantityToAdd")) {
            quantityAdded = Integer.parseInt(request.getParameter("gameQuantityToAdd"));
        }
        if (null != request.getParameter("gameQuantityToRemove")) {
            add = false;
            quantityAdded = (-1) * Integer.parseInt(request.getParameter("gameQuantityToRemove"));
        }
        Games game1 = gameDAO.getGame(gName);
        int i = user1.getCartGameMap().getOrDefault(game1, 0) + quantityAdded;
        //     System.out.println("line 176 : "+user1.getCartGameMap().getOrDefault(game1, 0));
        if (add && (i > game1.getQuantity())) {
            request.setAttribute("InvalidRequest", "Can't Add  Given No Of Products.. Please Try Again!!");
            update = false;
            //        System.out.println("add line 179");
        }
        //     System.out.println("add " +add + " i : "+i +" line 181"+" game quan : "+game1.getQuantity());
        if (!add && (i < 0)) {
            request.setAttribute("InvalidRequest", "Can't Remove Given No Of Products.. Please Try Again!!");
            update = false;
            //        System.out.println("remove line 184");
        }


        if (update) {
            user1.getCartGameMap().put(game1, user1.getCartGameMap().getOrDefault(game1, 0) + quantityAdded);
            List<Games> newList = new ArrayList<>();
            for (Games g : user1.getCartGameMap().keySet()) {
                if (user1.getCartGameMap().get(g) == 0) {
                    newList.add(g);
                }
            }

            for (Games g1 : newList) {
                user1.getCartGameMap().remove(g1);
            }


            userdao.updateUser(user1);

        }
        List<Games> allGamesList = gameDAO.getAllGames();
        for (Games games1 : allGamesList) {
            byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");
            games1.setBase64Encoded(base64Encoded);
        }
        request.setAttribute("allGenres", genreDao.getAllGenre());
        request.setAttribute("allGames", allGamesList);
        User user2 = userdao.getUser(user1.getEmail());
        request.setAttribute("userCart", user2.getCartGameMap());
        return CART;
    }

    @GetMapping("/searchbygenre.htm")
    public String gamesByGenreGet(HttpServletRequest request, @ModelAttribute("games") Games games,
                                  BindingResult result, SessionStatus status,
                                  GameDAO gameDAO, GenreDAO genreDao, Model model,
                                  @RequestParam("gamegenre") String gameGenre) throws UnsupportedEncodingException {

        List<Games> allGamesList = gameDAO.getGamesByGenre(gameGenre);
        for (Games games1 : allGamesList) {
            //     System.out.println(" lo " + games1.getName());
            byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");
            games1.setBase64Encoded(base64Encoded);
        }
        request.setAttribute("allGames", allGamesList);
        request.setAttribute("allGenres", genreDao.getAllGenre());
        return SHOPPING;

    }

    @GetMapping("/searchbypricerange.htm")
    public String gamesByPriceGet(HttpServletRequest request, @ModelAttribute("games") Games games,
                                  BindingResult result, SessionStatus status,
                                  GameDAO gameDAO, Model model, GenreDAO genreDao,
                                  @RequestParam("pricerange") String gameprice) throws UnsupportedEncodingException {

        List<Games> allGamesList = gameDAO.getGamesByPriceRange(gameprice);
        for (Games games1 : allGamesList) {

            byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");
            games1.setBase64Encoded(base64Encoded);
        }
        request.setAttribute("allGames", allGamesList);
        request.setAttribute("allGenres", genreDao.getAllGenre());
        return SHOPPING;


    }

    @GetMapping("/searchbyname.htm")
    public String gamesByNameGet(HttpServletRequest request, @ModelAttribute("games") Games games,
                                 BindingResult result, SessionStatus status,
                                 GameDAO gameDAO, Model model, GenreDAO genreDao,
                                 @RequestParam("nameSearch") String gameName) throws UnsupportedEncodingException {

        List<Games> allGamesList = gameDAO.getGamesByName(gameName);
        for (Games games1 : allGamesList) {
            byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
            String base64Encoded = new String(encodeBase64, "UTF-8");
            games1.setBase64Encoded(base64Encoded);
        }
        request.setAttribute("allGames", allGamesList);
        request.setAttribute("allGenres", genreDao.getAllGenre());
        return SHOPPING;

    }


    @GetMapping("/checkout.htm")
    public String checkOutGet(HttpServletRequest request, @ModelAttribute("userCheckingOut") String user,
                              BindingResult result, SessionStatus status,
                              UserDAO userDao, Model model, GenreDAO genreDao, GameDAO gameDAO) throws UnsupportedEncodingException, GamingException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {
                    User user1 = userDao.getUser(((User) httpSession.getAttribute("user")).getEmail());
                    Map<Games, Integer> gamesMap = user1.getCartGameMap();
                    List<Games> allGameswithUser = new ArrayList<>();
                    for (Games games1 : gamesMap.keySet()) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                        allGameswithUser.add(games1);
                    }
                    List<Games> allGamesList = gameDAO.getAllGames();
                    for (Games games1 : allGamesList) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }

                    boolean flag = true;
                    double discount = 0.0;
                    if (user1.getRole().equals("Premium")) {
                        discount = 5.0;
                    }
                    request.setAttribute("discount", discount);
                    double payableAmount = 0.0;

                    //check if payment is not possible
                    for (Games parent : allGamesList) {
                        for (Games child : allGameswithUser) {
                            if (child.equals(parent)) {
                                if (user1.getCartGameMap().get(child) <= parent.getQuantity()) {
                                    payableAmount += gamesMap.get(child) * child.getPrice();
                                } else {
                                    flag = false;
                                }
                            }
                        }
                    }
                    payableAmount = payableAmount - payableAmount * (discount / 100);
                    if (flag) {
                        request.setAttribute("payableAmount", payableAmount);
                        request.setAttribute("allGames", allGamesList);
                        request.setAttribute("allGameswithUser", gamesMap);
                        request.setAttribute("allGenres", genreDao.getAllGenre());
                        request.setAttribute("someProductLess", "Some Products are not available in Repository. Please manage your Cart!");
                        return CHECKOUT;
                    }

                    request.setAttribute("userCart", gamesMap);
                    request.setAttribute("allGames", allGamesList);
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    return CART;

                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


    @PostMapping("/checkout.htm")
    public String checkOut(HttpServletRequest request, @ModelAttribute("userCheckingOut") String user,
                           BindingResult result, SessionStatus status,
                           UserDAO userDao, Model model, GenreDAO genreDao, GameDAO gameDAO) throws UnsupportedEncodingException, GamingException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {
                    User user1 = userDao.getUser(((User) httpSession.getAttribute("user")).getEmail());
                    Map<Games, Integer> gamesMap = user1.getCartGameMap();
                    List<Games> allGameswithUser = new ArrayList<>();
                    for (Games games1 : gamesMap.keySet()) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                        allGameswithUser.add(games1);
                    }
                    List<Games> allGamesList = gameDAO.getAllGames();
                    for (Games games1 : allGamesList) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }

                    boolean flag = true;
                    double discount = 0.0;
                    if (user1.getRole().equals("Premium")) {
                        discount = 5.0;
                    }
                    request.setAttribute("discount", discount);
                    double payableAmount = 0.0;

                    //check if payment is not possible
                    for (Games parent : allGamesList) {
                        for (Games child : allGameswithUser) {
                            if (child.equals(parent)) {
                                if (user1.getCartGameMap().get(child) <= parent.getQuantity()) {
                                    payableAmount += gamesMap.get(child) * child.getPrice();
                                } else {
                                    flag = false;
                                }
                            }
                        }
                    }
                    payableAmount = payableAmount - payableAmount * (discount / 100);
                    if (flag) {
                        request.setAttribute("payableAmount", payableAmount);
                        request.setAttribute("allGames", allGamesList);
                        request.setAttribute("allGameswithUser", gamesMap);
                        request.setAttribute("allGenres", genreDao.getAllGenre());
                        request.setAttribute("someProductLess", "Some Products are not available in Repository. Please manage your Cart!");
                        return CHECKOUT;
                    }

                    request.setAttribute("userCart", gamesMap);
                    request.setAttribute("allGames", allGamesList);
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    return CART;

                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


    @PostMapping("/updateorder.htm")
    public String updateOrder(HttpServletRequest request, @ModelAttribute("userCheckingOut") String user,
                              BindingResult result, SessionStatus status,
                              UserDAO userDao, Model model, GenreDAO genreDao, GameDAO gameDAO) throws UnsupportedEncodingException, GamingException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {
                    User user1 = userDao.getUser(((User) httpSession.getAttribute("user")).getEmail());
                    System.out.println(user1.getOrderedGamesList().size() + " Line 345");
                    List<Games> allGames = gameDAO.getAllGames();
                    double amountpaid = Double.parseDouble(request.getParameter("hiddenAmount"));
                    boolean allSet = true;
                    for (Games parent : allGames) {
                        for (Games child : user1.getCartGameMap().keySet()) {
                            if (child.equals(parent)) {
                                if (user1.getCartGameMap().get(child) > parent.getQuantity()) {
                                    allSet = false;
                                    break;
                                }
                            }
                        }
                    }
                    PlacedOrder p1 = new PlacedOrder();
                    List<Games> gameToUpdate = new ArrayList<>();
                    if (allSet) {
                        p1.setUser(user1);
                        p1.setAddress(user1.getAddress());
                        p1.setDelivered(false);

                        p1.setOrders(user1.getCartGameMap());
                        p1.setAmountPaid(amountpaid);
                        for (Games g1 : p1.getOrders().keySet()) {
                            g1.setQuantity(g1.getQuantity() - p1.getOrders().get(g1));
                            gameDAO.updateGame(g1);
                        }
                        gameDAO.addOrder(p1);
                    }
                    List<PlacedOrder> orderList = userDao.getUser(user1.getEmail()).getOrderedGamesList();
                    request.setAttribute("orderList", orderList);
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    return ORDERS;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }

    @GetMapping("/viewCart.htm")
    public String viewCart(HttpServletRequest request, @ModelAttribute("games") Games games,
                           GameDAO gameDAO, Model model, GenreDAO genreDao,
                           UserDAO userDao) throws GamingException, UnsupportedEncodingException {

        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {

                    User user1 = userDao.getUser(((User) httpSession.getAttribute("user")).getEmail());
                    Map<Games, Integer> gamesMap = user1.getCartGameMap();
                    List<Games> allGamesList = gameDAO.getAllGames();
                    for (Games games1 : allGamesList) {
                        byte[] encodeBase64 = Base64.encodeBase64(games1.getGameImage());
                        String base64Encoded = new String(encodeBase64, "UTF-8");
                        games1.setBase64Encoded(base64Encoded);
                    }
                    request.setAttribute("userCart", gamesMap);
                    request.setAttribute("allGames", allGamesList);
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    return CART;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }

//To Get User Specific Orders

    @GetMapping("/allUserorders.htm")
    public String allUserOrders(HttpServletRequest request, @ModelAttribute("userCheckingOut") String user,
                              BindingResult result, SessionStatus status,
                              UserDAO userDao, Model model, GenreDAO genreDao, GameDAO gameDAO) throws UnsupportedEncodingException, GamingException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    return ADDPRODUCT;
                } else {
                    User user1 = userDao.getUser(((User) httpSession.getAttribute("user")).getEmail());
                    List<PlacedOrder> orderList = userDao.getUser(user1.getEmail()).getOrderedGamesList();
                    request.setAttribute("orderList", orderList);
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    return ORDERS;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


    //To Get All The Orders For Admin

    @GetMapping("/allAdminOrders.htm")
    public String allAdminOrders(HttpServletRequest request, @ModelAttribute("userCheckingOut") String user,
                                 BindingResult result, SessionStatus status, PlacedOrderDAO placedOrderDao,
                                UserDAO userDao, Model model, GenreDAO genreDao, GameDAO gameDAO) throws UnsupportedEncodingException, GamingException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (!httpSession.getAttribute("role").equals("admin")) {
                    return SHOPPING;
                } else {
                    List<PlacedOrder> orderList = placedOrderDao.getAllOrders();
                    request.setAttribute("orderList", orderList);
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    return ADMINORDERS;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }

}
