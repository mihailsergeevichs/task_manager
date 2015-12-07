package com.mihailsergeevichs.todo.manager.common.controller;

import com.mihailsergeevichs.todo.manager.task.model.Task;
import com.mihailsergeevichs.todo.manager.task.service.TaskService;
import com.mihailsergeevichs.todo.manager.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@EnableWebMvcSecurity
@EnableWebSecurity
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TaskService taskService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    protected static final String VIEW_NAME_HOMEPAGE = "index";

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(Principal principal, Model model) {

        LOGGER.debug("Rendering homepage.");

        LOGGER.debug("Current principal is " + principal.getName());

        List<Task> unchecked = taskService.findUnchecked(principal.getName());

        LOGGER.debug("List of unchecked task for current principal is + " + unchecked);

        LOGGER.debug("adding list of unchecked notations to model");

        model.addAttribute("unchecked", unchecked);

        return VIEW_NAME_HOMEPAGE;
    }
}
