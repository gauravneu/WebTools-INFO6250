package com.example.onlinegamingsite.controller;

import com.example.onlinegamingsite.dao.GameDAO;
import com.example.onlinegamingsite.dao.GenreDAO;
import com.example.onlinegamingsite.dao.UserDAO;
import com.example.onlinegamingsite.exception.GamingException;
import com.example.onlinegamingsite.pojo.Games;
import com.example.onlinegamingsite.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class LoginPageController {
    private final String LOGINPAGE = "login-view";
    private final String SHOPPING = "shopping-view";
    private final String SIGNUP = "sign-up";
    private final String ADDPRODUCT = "addGame";
    private final String ADMIN = "admin";
    private final String ROLE = "role";
    private final String USER = "user";
    private final String USER2 = "user2";
    private final String GAMES = "games";
    private final String ALLGENRES = "allGenres";
    private final String GENRESLIST = "genreslist";
    private final String ALLGAMES = "allGames";
    private final String LANDINGPAGE = "/";
    private final String LOGINUSER = "/loginuser.htm";
    private final String SAVEUSER = "/saveuser.htm";
    private final String SIGNUPURL = "/signup.htm";
    private final String SIGNOUT = "/signout.htm";
    private final String REDIRECTTOSIGNUP = "/redirectToSignup.htm";
    private final String INVALIDPASSWORD = "InvalidPassword";
    private final String INVALIDUSERNAME = "InvalidUsername";
    private final String EXISTINGUSER = "ExistingUser";


    @GetMapping({LOGINUSER, SAVEUSER, SIGNUPURL, LANDINGPAGE})
    public String loginUserGet(HttpServletRequest request,
                               GenreDAO genreDao, Model model, GameDAO gameDAO) {

        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute(ROLE)) {
                if (httpSession.getAttribute(ROLE).equals(ADMIN)) {
                    model.addAttribute(GAMES, new Games());
                    return ADDPRODUCT;
                } else {
                    request.setAttribute(ALLGENRES, genreDao.getAllGenre());
                    request.setAttribute(ALLGAMES, gameDAO.getAllGamesWithValues());
                    return SHOPPING;
                }
            }
        }
        model.addAttribute(USER, new User());
        if (request.getServletPath().trim().equals(SIGNUPURL)) {
            return SIGNUP;
        }
        return LOGINPAGE;
    }


    @PostMapping(LOGINUSER)
    public String loginuser(HttpServletRequest request, @ModelAttribute(USER) User user,
                            SessionStatus status,
                            UserDAO userdao, GenreDAO genreDao, Model model, GameDAO gameDAO) throws GamingException, UnsupportedEncodingException {

        User user1 = userdao.getUser(user.getEmail());
        if (user1 == null) {
            request.setAttribute(INVALIDUSERNAME, INVALIDUSERNAME);
            return LOGINPAGE;
        }
        if (!user1.getPassword().equals(user.getPassword())) {
            request.setAttribute(INVALIDPASSWORD, INVALIDPASSWORD);
            return LOGINPAGE;
        }
        status.setComplete();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(USER, user1);
        httpSession.setAttribute(ROLE, user1.getRole());
        request.setAttribute(USER2, user1);
        if (user1.getRole().equals(ADMIN)) {
            httpSession.setAttribute(GENRESLIST, genreDao.getAllGenre());
            model.addAttribute(GAMES, new Games());
            return ADDPRODUCT;
        }
        request.setAttribute(ALLGAMES, gameDAO.getAllGamesWithValues());
        request.setAttribute(ALLGENRES, genreDao.getAllGenre());
        return SHOPPING;

    }


    @PostMapping(SIGNUPURL)
    public String signupNewUser(HttpServletRequest request, @ModelAttribute(USER) User user,
                                BindingResult result, SessionStatus status,
                                UserDAO userdao, GameDAO gameDAO, GenreDAO genreDao, Model model) throws GamingException, UnsupportedEncodingException {


        if (null == userdao.getUser(user.getEmail())) {
            userdao.save(user);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(USER, user);
            httpSession.setAttribute(ROLE, user.getRole());
            request.setAttribute(USER2, httpSession.getAttribute(USER));
            List<Games> allGamesList = gameDAO.getAllGamesWithValues();
            request.setAttribute(ALLGAMES, allGamesList);
            request.setAttribute(ALLGENRES, genreDao.getAllGenre());
            return SHOPPING;
        }
        request.setAttribute(EXISTINGUSER, EXISTINGUSER);
        model.addAttribute(USER, new User());
        return SIGNUP;
    }


    @GetMapping(SIGNOUT)
    public String signOut(HttpServletRequest request, Model model) {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            httpSession.invalidate();
        }
        //     System.out.println(httpSession);
        model.addAttribute(USER, new User());
        return LOGINPAGE;
    }
}

//    @GetMapping(REDIRECTTOSIGNUP)
//    public String redirectTosignup(HttpServletRequest request, Model model) {
//        HttpSession httpSession = request.getSession(false);
//        if (null != httpSession) {
//            if (null != httpSession.getAttribute(ROLE)) {
//
//            }
//            model.addAttribute(USER, new User());
//            return SIGNUP;
//        }
//        model.addAttribute(USER, new User());
//        return SIGNUP;
//    }
//}


//    @PostMapping(SAVEUSER)
//    public String saveuserPost(HttpServletRequest request, @ModelAttribute(USER) User user,
//                               BindingResult result, SessionStatus status,
//                               UserDAO userdao) throws GamingException {
//
//        if (null != userdao.getUser(user.getEmail())) {
//            request.setAttribute(EXISTINGUSER,EXISTINGUSER);
//            return SIGNUP;
//        } else {
//            userdao.save(user);
//            status.setComplete();
//            return UPDATEPROFILE;
//        }
//    }


//    @GetMapping(SIGNUPURL)
//    public String signup(HttpServletRequest request, Model model) throws GamingException {
//
//        HttpSession httpSession = request.getSession(false);
//        if (null != httpSession) {
//            model.addAttribute(USER, new User());
//            return SIGNUP;
//        }
//        model.addAttribute(USER, new User());
//        return SIGNUP;
//    }

