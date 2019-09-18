package com.vemis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vemis.model.AuteurModeleDeSimulation;
import com.vemis.model.Login;
import com.vemis.model.User;
import com.vemis.utilitaires.Constantes;
import com.vemis.dao.UserDao;

@Controller
public class LoginController {
  @Autowired
  UserDao userDao;
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	//Constantes.CONTEXTE = request.getContextPath();
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("login", new Login());
    mav.addObject("unAuteurModele", new AuteurModeleDeSimulation());//un auteur pour le formulaire du modal
    return mav;
  }
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("login") Login login) {
    ModelAndView mav = null;
    boolean user = userDao.validateUser(login);
    if (user) {
    mav = new ModelAndView("welcome");
    mav.addObject("firstname", "papa");
    } else {
    mav = new ModelAndView("login");
    mav.addObject("message", "Username or Password is wrong!!");
    }
    mav.addObject("unAuteurModele", new AuteurModeleDeSimulation());//un auteur pour le formulaire du modal
    return mav;
  }
}