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

    //main page
    @GetMapping("/")
    public String main(User user, Model model, HttpServletRequest req) {

        session = req.getSession(false);
        if(session != null)
            return "Chat";
        else
            return "Login";
    }

    //logout
    @GetMapping("/Logout")
    public String logout(User user, Model model, HttpServletRequest request) {

        session = request.getSession(false);
        if(session != null)
            session.invalidate();
        return "Login";
    }


    //add message to chat
    @PostMapping("/sendMsg")
    public void sendMsg(@ModelAttribute Message msg2){
        System.out.println(msg2 +"here");
        getRepomsg().save(msg2);

    }

    //get all messages to chat
    @GetMapping(value="/getjson")
    public @ResponseBody
    List<Message> getAll(Model model) {

        return getRepomsg().findAll();
    }


    //get all messages from user
    @GetMapping(value="/getuserchat/{name}")
    public @ResponseBody
    List<Message> getMessage(@PathVariable("name") String name, Model model) {

        return getRepomsg().findByName(name);
    }

    //get all messages with text
    @GetMapping(value="/gettextchat/{message}")
    public @ResponseBody
    List<Message> getMessagetext(@PathVariable("message") String message, Model model) {

        return getRepomsg().findByMessage1(message);
    }






    //add user to chat
    @RequestMapping("/addUser")
    public String addUser(@Valid User user, BindingResult result, Model model , HttpServletRequest request) {
        session = request.getSession(false);
        if (result.hasErrors()) {
            System.out.println(result);
            return "Login";
        }

        userRepos repo = this.context.getBean(userRepos.class);
        repo.save(user);
        //model.addAttribute("users", getRepouser().findByName(user.getName()));
        model.addAttribute("name", user.getName());
        return "Chat";
    }

}

