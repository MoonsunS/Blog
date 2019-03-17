package com.moonsuns.myblog.controller;

import com.moonsuns.myblog.domain.User;
import com.moonsuns.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/listusers")
    public ModelAndView listUsers(Model model) {
        model.addAttribute("listUsers", userRepository.listUsers());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("user/list", "userModel", model);
    }

    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        User user = userRepository.getUserByid(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("user/view", "userModel", model);
    }

    @GetMapping("/form")
    public ModelAndView vreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("user/form", "userModel", model);
    }

    @PostMapping
    public ModelAndView saveOrUpdateUser(User user) {
        user = userRepository.saveOrUpdateUser(user);
        return new ModelAndView("redirect:/user/listusers");
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        userRepository.deleteUser(id);
        return new ModelAndView("redirect:/user/listusers");
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.getUserByid(id);
        model.addAttribute("user", user);
        model.addAttribute("title", "编辑用户");
        return new ModelAndView("user/form", "userModel", model);
    }

}
