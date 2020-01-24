
package com.example.ex4.controllers;

import com.example.ex4.repos.Message;
import com.example.ex4.repos.User;
import com.example.ex4.repos.msgRepos;
import com.example.ex4.repos.userRepos;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private ApplicationContext context;

    private HttpSession session = null;

    @Autowired
    public UserController(ApplicationContext c) {
        this.context = c;
    }

    private userRepos getRepouser() {

        return this.context.getBean(userRepos.class);
    }

    private msgRepos getRepomsg() {

            return this.context.getBean(msgRepos.class);
    }

    // Root
    @GetMapping("/")
    public String main(User user, Model model, HttpServletRequest req) {

        session = req.getSession(false);
        if(session != null) {
            model.addAttribute("name", session.getAttribute("name"));
            return "redirect:/addUser";
        }
        else
            return "Login";
    }

    // Logout mapping
    @GetMapping("/Logout")
    public String logout(User user, Model model, HttpServletRequest request) {

        session = request.getSession(false);
        if(session != null)
            session.invalidate();
        return "Login";
    }


    // Add message to chat
    @PostMapping("/sendMsg")
    public void sendMsg(@ModelAttribute Message msg2){
        System.out.println(msg2 +"here");
        getRepomsg().save(msg2);

    }

    // Return all messages
    @GetMapping(value="/getjson")
    public @ResponseBody
    List<Message> getAll(Model model, HttpServletRequest request) {

        session = request.getSession(false);
        if (session != null){
            return getRepomsg().findAll();
        }
        else{
            session.invalidate();
            return null;
        }
    }


//    // Check session
//    @GetMapping(value="/chceksession")
//    public @ResponseBody String getSession(Model model, HttpServletRequest request) {
//
//        session = request.getSession(false);
//        if (session != null)
//            return "yes";
//        else {
//            return "no";
//        }
//    }


    // Returns all messages from contacts with name X
    @GetMapping(value="/getuserchat/{name}")
    public @ResponseBody
    List<Message> getMessage(@PathVariable("name") String name, Model model) {

        return getRepomsg().findByName(name);
    }

    // Search text function - returns all messages with given input X
    @GetMapping(value="/gettextchat/{message}")
    public @ResponseBody
    List<Message> getMessagetext(@PathVariable("message") String message, Model model) {

        return getRepomsg().findByMessage1(message);
    }



    // Add user to chat
    @RequestMapping("/addUser")
    public String addUser(@Valid User user, BindingResult result, Model model , HttpServletRequest request) {
        session = request.getSession(false);
        if (session != null)
        {       model.addAttribute("name", session.getAttribute("name"));
                return "Chat";
        }
        else {
            if (result.hasErrors()) {
                System.out.println(result);
                return "Login";
            }
            else{
            session = request.getSession();
            session.setAttribute("name",user.getName());
            }
        }


        userRepos repo = this.context.getBean(userRepos.class);
        repo.save(user);
        //model.addAttribute("users", getRepouser().findByName(user.getName()));
        model.addAttribute("name", user.getName());
        return "Chat";
    }

}

