package com.facebokapp.JForceApplication.controller;

import com.facebokapp.JForceApplication.model.Post;
import com.facebokapp.JForceApplication.model.User;
import com.facebokapp.JForceApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService service;


    @GetMapping("/")
    public ModelAndView welcomePage() {

        return new ModelAndView("wc");
    }

    // Course List


    @GetMapping("/list")
    public ModelAndView userList() {

        ModelAndView model = new ModelAndView();
        List<User> userList = service.getAllUsers();
        model.addObject("userLists", userList);
        model.setViewName("user_list");
        return model;
    }
    @GetMapping("/listadmin")
    public ModelAndView userListAdmin() {

        ModelAndView model = new ModelAndView();
        List<User> userList = service.getAllUsers();
        model.addObject("userLists", userList);
        model.setViewName("userlist_admin");
        return model;
    }
    @GetMapping("/padminlist")
    public ModelAndView adminList() {

        ModelAndView model = new ModelAndView();
        List<Post> userList = service.getAllPost();
        model.addObject("postLists", userList);
        model.setViewName("postadmin_list");
        return model;
    }


    @GetMapping("/plist")
    public ModelAndView postList() {
        ModelAndView model = new ModelAndView();
        List<Post> postList = service.getAllPost();
        model.addObject("postLists", postList);
        model.setViewName("post_list");
        return model;
    }

    // add course Page

    @GetMapping("/addUserPage")
    public ModelAndView addUserPage() {

        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", user);
        modelAndView.setViewName("form");
        return modelAndView;
    }



    @GetMapping("/addPostPage")
    public ModelAndView addPostPage(@ModelAttribute("userForm") Post post) {

        Post user = new Post();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", post);
        modelAndView.setViewName("Post");
        return modelAndView;
    }
    @GetMapping("/addingPostAdmin")
    public ModelAndView addAdminPostPage(@ModelAttribute("userForm") Post post) {

        Post user = new Post();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", post);
        modelAndView.setViewName("PostAdmin");
        return modelAndView;
    }


    // add User
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(@ModelAttribute("userForm") User user) {

        return new ModelAndView("Login");
    }
     @PostMapping(value = "/loginAdd")
    public ModelAndView loginAdd(@ModelAttribute("userForm") User user)
    {
       String uname="";
        ModelAndView modelAndView = new ModelAndView();
        String name = user.getUserName();
        String pwd =user.getPassword();
        System.out.println("Fname: " + name);
        System.out.println("Password: " + pwd);
        User u= service.findByPassword(pwd);
        System.out.println(service.findByPassword(pwd));
        System.out.println("Value:  "+u);
         uname=u.getUserName();
         System.out.println("User My Name is  "+uname);
         if (uname.equals("Admin"))
         {
             ModelAndView model = new ModelAndView();
             List<User> userList = service.getAllUsers();
             model.addObject("userLists", userList);
             model.setViewName("userlist_admin");
             return model;
         }
         else if(uname.isEmpty()) {
          modelAndView.setViewName("Errror");
          System.out.println("Empty");
          return modelAndView;
         }
         else {
             List<Post> postList = service.getAllPost();
            modelAndView.addObject("postLists", postList);
            modelAndView.setViewName("post_list");
            System.out.println("Full");
            return modelAndView;
         }

    }
    @RequestMapping(value = "/addUser")
    public ModelAndView addUser(@ModelAttribute("userForm") User user){
        ModelAndView modelAndView = new ModelAndView();
        service.saveUser(user);
        List<User> userList = service.getAllUsers();
        modelAndView.addObject("userLists", userList);
        modelAndView.setViewName("user_list");
     //   return new ModelAndView("login");
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/addUserAdminA")
    public ModelAndView addUserAdminA() {

        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", user);
        modelAndView.setViewName("AdminUser");
        return modelAndView;
    }
    @RequestMapping(value = "/addAdminUser")
    public ModelAndView addAdminUserU(@ModelAttribute("userForm") User user){
        ModelAndView modelAndView = new ModelAndView();
        service.saveUser(user);
        List<User> userList = service.getAllUsers();
        modelAndView.addObject("userLists", userList);
        modelAndView.setViewName("userlist_admin");
        return modelAndView;
     //   return new ModelAndView("redirect:/userlist_admin");
    }


    @RequestMapping(value = "/addPost",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addPost(@ModelAttribute("userForm") Post post ){
        ModelAndView modelAndView = new ModelAndView();
        service.savePost(post);
        List<Post> postList = service.getAllPost();
        modelAndView.addObject("postLists", postList);
        modelAndView.setViewName("post_list");
        return modelAndView;
    }
    @RequestMapping(value = "/addAdminPost",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addAdminPost(@ModelAttribute("userForm") Post post ){
        ModelAndView modelAndView = new ModelAndView();
        service.savePost(post);
        List<Post> postList = service.getAllPost();
        modelAndView.addObject("postLists", postList);
        modelAndView.setViewName("postadmin_list");
        return modelAndView;
    }
    @RequestMapping(value = "/addPostAdmin",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addPostAdmin(@ModelAttribute("userForm") Post post){
        ModelAndView modelAndView = new ModelAndView();
        service.savePost(post);
        List<Post> postList = service.getAllPost();
        modelAndView.addObject("postLists", postList);
        modelAndView.setViewName("postadmin_list");
        return modelAndView;
    }

    @RequestMapping(value = "/addUserAdmin",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addUserAdmin(@ModelAttribute("userForm") User user){
        ModelAndView modelAndView = new ModelAndView();
        service.saveUser(user);
        List<User> userList = service.getAllUsers();
        modelAndView.addObject("userLists", userList);
        modelAndView.setViewName("userlist_admin");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUserAdmin",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView deleteUserAdmin(@ModelAttribute("userForm") User user){
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = service.getAllUsers();
        modelAndView.addObject("userLists", userList);
        modelAndView.setViewName("userlist_admin");
        return modelAndView;
    }


    // Delete List

    @GetMapping("/deleteUser/{id}")
    public ModelAndView deleteCourse(@PathVariable("id") int id){

        service.deleteUser(id);
        return new ModelAndView("redirect:/deleteUserAdmin");
    }
    @GetMapping("/deletePost/{pid}")
    public ModelAndView deletePost(@PathVariable("pid") int pid){

        service.deletePost(pid);
        return new ModelAndView("redirect:/padminlist");
    }

    // Update List

    @GetMapping("/updateUser/{id}")
    public ModelAndView updateUser(@PathVariable("id") int id){

        ModelAndView model =new ModelAndView();
        User user = service.getUserById(id);
        model.addObject("userForm", user);
        model.setViewName("User_Update");

        return model;
    }

    @GetMapping("/updatePost/{pid}")
    public ModelAndView updatePost(@PathVariable("pid") int pid){

        ModelAndView model =new ModelAndView();
        Post post = service.getPostById(pid);
        model.addObject("userForm",post);
        model.setViewName("Post_Update");

        return model;
    }






}
