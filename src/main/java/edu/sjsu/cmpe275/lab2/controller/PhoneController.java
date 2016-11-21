package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.Address;
import edu.sjsu.cmpe275.lab2.Phone;
import edu.sjsu.cmpe275.lab2.User;
import edu.sjsu.cmpe275.lab2.dao.IUserDAO;
import edu.sjsu.cmpe275.lab2.service.PhoneManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/phone")
@SessionAttributes("phone")
public class PhoneController {

    @Autowired
    PhoneManager phoneManager;
    @Autowired
    private IUserDAO userDAO;

    /**
     * (6) Get a Phone as HTML
     *
     * @param phoneid
     * @param model
     * @param response
     * @return Phone as HTML page
     */
    @Transactional
    @RequestMapping(value = "/{phoneid}", method = RequestMethod.GET)
    public String getPhoneHtml(@PathVariable("phoneid") int phoneid, Model model, HttpServletResponse response) {
        List<Phone> phones = phoneManager.getPhones(phoneid);
        /**
         * If ID is not found in database
         */
        if (phones.size() < 1) {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.PHONE_ENTITY, phoneid));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        model.addAttribute("phone", phones.get(0));
        List<Integer> userIdsList = userDAO.getAllUsersIDs();
        model.addAttribute("userIdsList", userIdsList);
        return "getPhoneHtmlPage";
    }

    /**
     * (7) Get a phone back as JSON
     *
     * @param phoneid
     * @param isJson
     * @param response
     * @return Phone in JSON format
     */
    @Transactional
    @RequestMapping(value = "/{phoneid}", method = RequestMethod.GET, params = "json", produces = "application/json; charset=UTF-8")
    public
    @ResponseBody
    String getPhoneJson(@PathVariable("phoneid") int phoneid, @RequestParam(value = "json") String isJson, HttpServletResponse response) {
        if (isJson.equals("true")) {
            List<Phone> phones = phoneManager.getPhones(phoneid);
            /**
             * If ID is not found in database
             */
            if (phones.size() < 1) {
                try {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.PHONE_ENTITY, phoneid));
                    response.getWriter().flush();
                    response.getWriter().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            return Helper.phoneJsonBuilder(phones.get(0));
        }
        return "{\"Error\":\"json=" + isJson + " not a valid value\"}";
    }

    /**
     * (8) Get the phone creation HTML
     *
     * @param model
     * @return Page to add a phone
     */
    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String getAddPhonePage(Model model) {
        model.addAttribute("phone", new Phone());
        model.addAttribute("nextid", phoneManager.getNextID()); //to display next ID
        return "addPhonePage";
    }

    /**
     * Method to handle POST request to create a phone
     *
     * @param phone
     * @param model
     * @param response
     * @return HTTP 201 code, phone creation page
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public String addPhone(@ModelAttribute("phone") Phone phone, Model model, HttpServletResponse response) {
        System.out.println("create phone via form");
        try {
            phoneManager.addPhone(phone);
        }
        /**
         * If Unique key number is tried to repeat
         */ catch (PersistenceException pe) {
            try {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(Errors.getDuplicateKeyNotAllowedErrorPage(phone.getNumb()));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        model.addAttribute("phone", new Phone());
        model.addAttribute("nextid", phoneManager.getNextID()); //to display next ID to be generated
        return "addPhonePage";
    }

    /**
     * (9) Create a phone via POST
     *
     * @param numb
     * @param description
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param userids
     * @param model
     * @param response
     * @return newly created phone
     */
    @Transactional
    @RequestMapping(value = "/phoneId", method = RequestMethod.POST, params = {
            "number",
            "description",
            "street",
            "city",
            "state",
            "zip",
            "users[]"
    })
    @ResponseStatus(HttpStatus.CREATED)
    public String createPhone(@RequestParam(value = "number") String numb,
                              @RequestParam(value = "description") String description,
                              @RequestParam(value = "street") String street,
                              @RequestParam(value = "city") String city,
                              @RequestParam(value = "state") String state,
                              @RequestParam(value = "zip") String zip,
                              @RequestParam(value = "users[]", required = false) List<Integer> userids,
                              Model model,
                              HttpServletResponse response
    ) {
        System.out.println("create phone called");
        Phone phone = new Phone(numb, description, street, city, state, zip);
        List<User> userlist = new ArrayList<User>();
        for (int i = 0; i < userids.size(); i++) {
            if (userDAO.getUserDetails(userids.get(i)).size() > 0) {
                User temp = userDAO.getUserDetails(userids.get(i)).get(0);
                userlist.add(temp);
            } else {
                userlist.add(null);
            }
        }
        phone.setUserList(userlist);
        try {
            phoneManager.addPhone(phone);
        } catch (PersistenceException pe) {
            try {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(Errors.getDuplicateKeyNotAllowedErrorPage(phone.getNumb()));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        model.addAttribute("phone", phone);
        return "getPhoneHtmlPage";
    }

    /**
     * (9) Update a phone
     *
     * @param phoneid
     * @param number
     * @param description
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param useridname
     * @param useridnameremoval
     * @param userids
     * @param response
     * @return newly updated phone page
     */
    @Transactional
    @RequestMapping(value = "/{phoneid}", method = RequestMethod.POST)
    public String updatePhone(@PathVariable("phoneid") int phoneid,
                              @RequestParam(value = "number", required = false) String number,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "street", required = false) String street,
                              @RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "state", required = false) String state,
                              @RequestParam(value = "zip", required = false) String zip,
                              @RequestParam(value = "useridname", required = false) String useridname,
                              @RequestParam(value = "useridnameremoval", required = false) String useridnameremoval,
                              @RequestParam(value = "users[]", required = false) List<Integer> userids,
                              HttpServletResponse response
    ) {
        System.out.println("update phone called");
        /**
         * Get the Phone object of the requested phone
         */
        List<Phone> existingphone = phoneManager.getPhones(phoneid);
        /**
         * Set the values as received from the form
         */
        if (existingphone.size() > 0) {
            existingphone.get(0).setId(phoneid);
            existingphone.get(0).setDescription(description);
            existingphone.get(0).setNumb(number);
            Address existingaddress = existingphone.get(0).getAddress();
            existingaddress.setCity(city);
            existingaddress.setState(state);
            existingaddress.setZip(zip);
            existingaddress.setStreet(street);
            existingphone.get(0).setAddress(existingaddress);
        } else {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.PHONE_ENTITY, phoneid));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * Check if number already exists in DB
         */
        if (!phoneManager.iDIsUpdatableWithNumber(phoneid, number)) {
            try {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(Errors.getDuplicateKeyNotAllowedErrorPage(number));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         * Get the Users assigned to this phone
         */
        List<User> userlist = new ArrayList<User>();
        userlist = existingphone.get(0).getUserList();
        /**
         * Check if the request has come via URL update method
         */
        if (userids != null && userids.size() > 0) {
            for (int i = 0; i < userids.size(); i++) {
                if (userDAO.getUserDetails(userids.get(i)).size() > 0) {
                    User temp = userDAO.getUserDetails(userids.get(i)).get(0);
                    if (!userlist.contains(temp)) userlist.add(temp);
                } else {
                    userlist.add(null);
                }
            }
        }
        /**
         * Check if there is any USER ID to be removed.
         */
        if (useridnameremoval != null && useridnameremoval != "") {
            int removaluserId = Integer.parseInt(useridnameremoval);
            boolean validuser = false;
            int index = 0;
            for (int i = 0; i < userlist.size(); i++) {
                if (userlist.get(i).getId() == removaluserId) {
                    index = i;
                    validuser = true;
                    break;
                }
            }
            if (validuser) userlist.remove(index);
        }
        /**
         * Check if a new user is to be ADDED.
         */
        if (useridname != null && useridname != "") {
            int id = Integer.parseInt(useridname);
            if (id > 0) {
                if (userDAO.getUserDetails(id).size() > 0) {
                    User temp = userDAO.getUserDetails(id).get(0);
                    if (!userlist.contains(temp)) userlist.add(temp);
                }
            } else {
                userlist.add(null);
            }
        }
        existingphone.get(0).setUserList(userlist);
        int ret = phoneManager.updatePhone(existingphone.get(0));
        if (ret != 1) {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.PHONE_ENTITY, phoneid));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        System.out.println("reached return");
        return "redirect:/phone/{phoneid}";
    }

    /**
     * (10) Delete a phone
     *
     * @param phoneid
     * @param response
     * @return Add a phone page
     */
    @RequestMapping(value = "/{phoneid}", method = RequestMethod.DELETE)
    @Transactional
    public String deletePhone(@PathVariable("phoneid") int phoneid, HttpServletResponse response) {
        int res;
        try {
            res = phoneManager.deletePhone(phoneid);
        } catch (PersistenceException pse) {
            try {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(Errors.getDeleteNotAllowedErrorPage(Errors.PHONE_ENTITY, Errors.USER_ENTITY, phoneid));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }
        if (res != 1) {
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(Errors.getIDNotFoundErrorPage(Errors.PHONE_ENTITY, phoneid));
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }
        return "redirect:/phone";
    }
}