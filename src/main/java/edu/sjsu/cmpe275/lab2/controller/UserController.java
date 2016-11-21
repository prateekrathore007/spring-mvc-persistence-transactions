package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.dao.IUserDAO;
import edu.sjsu.cmpe275.lab2.User;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@Transactional
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserDAO userDAO;

    @PersistenceContext
    private EntityManager entitymanager;

    /**
     * Get the user creation HTML
     * @param model
     * @return
     */
    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String getAddUserPage(Model model) {
        model.addAttribute("nextid", userDAO.getNextID()); //to display next ID to be generated
        return "adduser";
    }

    /**
     * (1) Get User by ID.
     * @param userId
     * @param model
     * @param response
     * @return User page
     */
    @Transactional
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("userId") int userId, ModelMap model, HttpServletResponse response) {
        List<User> induser = userDAO.getUserDetails(userId);
        if (induser.size() < 1) {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.USER_ENTITY, userId));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        boolean check = induser.get(0).getPhones().size() > 0;
        model.addAttribute("isPhoneMapped", check);
        model.addAttribute("userdetails", induser.get(0));
        return new ModelAndView("viewuser");
    }

    /**
     * (2) Get a user back as JSON
     * @param userId
     * @param isJson
     * @param response
     * @return User details as JSON
     */
    @Transactional
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, params = "json", produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    String getPhoneJson(@PathVariable("userId") int userId, @RequestParam(value = "json") String isJson, HttpServletResponse response) {
        if (isJson.equals("true")) {
            List<User> users = userDAO.getUserDetails(userId);
            /**
             * If user is not found in Database
             */
            if (users.size() < 1) {
                try {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.USER_ENTITY, userId));
                    response.getWriter().flush();
                    response.getWriter().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            try {
                return Helper.userJSonBuilder(users.get(0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "{\"Error\":\"json=" + isJson + " not a valid value\"}";
    }

    /**
     * Method that handles POST from form to create a new user
     * @param userVO
     * @param model
     * @return Add user form
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public String submitForm(@ModelAttribute("user") User userVO, Model model) {
        int userId = userDAO.saveUserDetails(userVO);
        model.addAttribute("nextid", userDAO.getNextID()); //to display next ID to be generated
        return "adduser";
    }

    /**
     * Create a user via POST(URL)
     * @param firstname
     * @param lastname
     * @param title
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param model
     * @return Page of newly created user
     */
    @RequestMapping(value = "/userId", method = RequestMethod.POST, params = {"firstname", "lastname", "title", "street", "city", "state", "zip"})
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public String createUser(@RequestParam(value = "firstname", required = false) String firstname,
                             @RequestParam(value = "lastname", required = false) String lastname,
                             @RequestParam(value = "title", required = false) String title,
                             @RequestParam(value = "street", required = false) String street,
                             @RequestParam(value = "city", required = false) String city,
                             @RequestParam(value = "state", required = false) String state,
                             @RequestParam(value = "zip", required = false) String zip,
                             Model model
    ) {
        User newuser = new User(firstname, lastname, title, street, city, state, zip);
        int newuserId = userDAO.saveUserDetails(newuser);
        model.addAttribute("userdetails", newuser);
        return "redirect:/user/" + newuserId;
    }

    /**
     * (4) UPDATE a user via URL
     * @param userId
     * @param firstname
     * @param lastname
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param title
     * @param response
     * @return Page of newly updated user
     */
    @Transactional
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public String save(@PathVariable("userId") int userId,
                       @RequestParam(value = "firstname", required = false) String firstname,
                       @RequestParam(value = "lastname", required = false) String lastname,
                       @RequestParam(value = "street", required = false) String street,
                       @RequestParam(value = "city", required = false) String city,
                       @RequestParam(value = "state", required = false) String state,
                       @RequestParam(value = "zip", required = false) String zip,
                       @RequestParam(value = "title", required = false) String title,
                       HttpServletResponse response
    ) {
        User user = new User(firstname,lastname,title,street,city,state,zip);
        user.setId(userId);
        /**
         * If user not found in database
         */
        if (userDAO.updateUser(user) != 1) {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.USER_ENTITY, userId));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return "redirect:/user/{userId}";
    }

    /**
     * (5) Delete a User
     * @param userId
     * @param response
     * @return User creation page
     */
    @Transactional
    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("userId") int userId, HttpServletResponse response) {
        int res = userDAO.deleteUser(userId);
        /**
         * If user ID does not exist in database
         */
        if (res != 1) {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.USER_ENTITY, userId));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return "redirect:/user";
    }
}