package com.mihailsergeevichs.todo.manager.task.controller;

import com.mihailsergeevichs.todo.manager.task.model.Task;
import com.mihailsergeevichs.todo.manager.task.service.TaskService;
import com.mihailsergeevichs.todo.manager.user.repository.UserRepository;
import com.mihailsergeevichs.todo.manager.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Overlord on 16.11.2015.
 */
@Controller
@RequestMapping("tasks")
public class TaskViewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskViewController.class);

    private final static String TASK_VIEW_PAGE = "task";
    private final static String TASK_IS_ABSENT = "task_is_absent";
    private final static String HOME_VIEW_PAGE = "redirect:/";

    @Autowired
    TaskService taskService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findTask(@PathVariable("id") Long id, Model model, Principal principal){

        LOGGER.debug("Invoking task edit controller");

        Task currentTask = taskService.findTask(principal.getName(), id);

        if(currentTask == null){
            return TASK_IS_ABSENT;
        }

        model.addAttribute("task", currentTask);

        return TASK_VIEW_PAGE;
    }

    @RequestMapping(value = "/{id}/check", method = RequestMethod.GET)
    public String checkTask(@PathVariable("id") Long id, Principal principal){

        User user = userRepository.findByEmail(principal.getName());

        for(Task task :user.getTasks()){
            if(task.getId().equals(id)){
                task.setChecked(true);
                LOGGER.debug("Marking task " + task + " as checked");
            }
        }
        userRepository.saveAndFlush(user);
        return HOME_VIEW_PAGE;
    }

}
