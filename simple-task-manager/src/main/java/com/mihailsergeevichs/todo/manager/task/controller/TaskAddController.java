package com.mihailsergeevichs.todo.manager.task.controller;

import com.mihailsergeevichs.todo.manager.task.model.Task;
import com.mihailsergeevichs.todo.manager.task.dto.TaskForm;
import com.mihailsergeevichs.todo.manager.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Overlord on 12.11.2015.
 */
@Controller
@SessionAttributes("task")
public class TaskAddController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskAddController.class);

    @Autowired
    TaskService taskService;

    private static final String ADD_TASK_VIEW_PAGE = "add_task";
    protected static final String MODEL_NAME_ADD_NEW_TASK_DTO = "task";
    private static final String REDIRECT_ROOT_PAGE = "redirect:/";
    protected static final String ERROR_CODE_TASK_EXIST = "Task.already.exist";


    @RequestMapping(value = "/tasks/add", method = RequestMethod.GET)
    public String getAddTaskViewPage(WebRequest request, Model model){

        LOGGER.debug("Rendering the task add page");

        model.addAttribute(MODEL_NAME_ADD_NEW_TASK_DTO, new TaskForm());

        return ADD_TASK_VIEW_PAGE;
    }

    @ModelAttribute("categories")
    public List<String> getCategories(){

        LOGGER.debug("Adding Category attributes to task add page");

        return new ArrayList<>(Arrays.asList(new String[]{
                "WORK", "FAMILY", "FRIENDS", "HEALTH", "BUSINESS", "VACATION", "OTHER"
        }));
    }

    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST)
    public String addNewTask(@Valid @ModelAttribute("task") TaskForm task,
                             BindingResult result, WebRequest request, Principal principal){

        LOGGER.debug("Creating new task with information: {}", task);

        if (result.hasErrors()) {
            LOGGER.debug("Validation errors found. Rendering form view.");
            return ADD_TASK_VIEW_PAGE;
        }

        LOGGER.debug("No validation errors found. Saving task.");

        Task newTask = createNewTask(task, result, principal.getName());

        LOGGER.debug("Current user task is" + newTask);

        if(newTask == null){
            LOGGER.debug("Something wrong with task data. Rendering view page.");
            return ADD_TASK_VIEW_PAGE;
        }

        return REDIRECT_ROOT_PAGE;
    }

    private Task createNewTask(TaskForm task, BindingResult result, String email) {

        LOGGER.debug("Creating new task with information: {}", task);

        Task newTask = taskService.createNewTask(task, email);

        return newTask;
    }
}
