package com.example.onlinegamingsite.controller;

import com.example.onlinegamingsite.dao.GameDAO;
import com.example.onlinegamingsite.dao.GenreDAO;
import com.example.onlinegamingsite.exception.GamingException;
import com.example.onlinegamingsite.pojo.Games;
import com.example.onlinegamingsite.pojo.Genre;
import com.example.onlinegamingsite.pojo.User;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GenreController {

    private final String ADDPRODUCT = "addGame";
//    private final String PRODUCTADDED = "productAdded";
//    private final String GAMEPAGE = "seeGamePage";
    private final String LOGINPAGE = "login-view";
    private final String SHOPPING = "shopping-view";
//    private final String SIGNUP = "sign-up";
    private final String MANAGEGENRE = "managegenre";


    @GetMapping("/managegenre.htm")
    public String managegenre(HttpServletRequest request, SessionStatus status,
                              GenreDAO genreDao, Model model) {

        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    model.addAttribute("genre", new Genre());
                    return MANAGEGENRE;
                } else {
                    request.setAttribute("allGenres", genreDao.getAllGenre());
                    model.addAttribute("games", new Games());
                    return SHOPPING;
                }
            }
            model.addAttribute("user", new User());
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }


    @PostMapping("/savegenre.htm")
    public String saveGenreController(HttpServletRequest request, @ModelAttribute("genre") Genre genre,
                                      BindingResult result, SessionStatus status,
                                      GenreDAO genredao, GenreDAO genreDao, Model model) throws GamingException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    if (null == genredao.getGenre(genre.getName())) {
                        genredao.saveGenre(genre);
                        httpSession.setAttribute("genreslist", genredao.getAllGenre());
                        model.addAttribute("games", new Games());
                        return ADDPRODUCT;
                    } else {
                        request.setAttribute("gamingException", "OldGenre");
                        return MANAGEGENRE;
                    }
                }
                request.setAttribute("allGenres", genreDao.getAllGenre());
                return SHOPPING;
            }
            return LOGINPAGE;
        }
        model.addAttribute("user", new User());

        return LOGINPAGE;
    }

    @GetMapping("/savegenre.htm")
    public String saveGenreController1(HttpServletRequest request, @ModelAttribute("genre") Genre genre,
                                       BindingResult result, SessionStatus status, GameDAO gamedao,
                                       GenreDAO genredao, GenreDAO genreDao, Model model) throws GamingException, IOException {
        HttpSession httpSession = request.getSession(false);
        if (null != httpSession) {
            if (null != httpSession.getAttribute("role")) {
                if (httpSession.getAttribute("role").equals("admin")) {
                    model.addAttribute("games", new Games());
                    return ADDPRODUCT;
                }
                request.setAttribute("allGenres", genreDao.getAllGenre());
                List<Games> allGamesList = gamedao.getAllGames();
                request.setAttribute("allGames", allGamesList);
                return SHOPPING;
            }
        }
        model.addAttribute("user", new User());
        return LOGINPAGE;
    }
}




//
//
//    @GetMapping("/deletegenre.htm")
//    public String deleteGenreController(HttpServletRequest request,
//                                        BindingResult result, SessionStatus status,
//                                        GenreDAO genredao, Model model) throws GamingException {
//        HttpSession httpSession = request.getSession(false);
//        if (null != httpSession) {
//            if (null != httpSession.getAttribute("role")) {
//                if (httpSession.getAttribute("role").equals("admin")) {
//
//                    return ADDPRODUCT;
//                } else {
//                    return SHOPPING;
//                }
//            }
//            return ADDPRODUCT;
//        }
//        model.addAttribute("user", new User());
//        return LOGINPAGE;
//    }
//
//    @PostMapping("/deletegenre.htm")
//    public String deleteGenreController1(HttpServletRequest request, @ModelAttribute("genre") Genre genre,
//                                         BindingResult result, SessionStatus status,
//                                         GenreDAO genredao, Model model)
//            throws GamingException, UnsupportedEncodingException {
//
//        //check session here
//
//        if (null != genre) {
//
//            model.addAttribute("genre", genre);
//            return GAMEPAGE;
//        }
//        return SHOPPING;
//    }