package com.mihailsergeevichs.todo.manager.task.scheduler;

import com.mihailsergeevichs.todo.manager.task.controller.TaskAddController;
import com.mihailsergeevichs.todo.manager.user.model.User;
import com.mihailsergeevichs.todo.manager.user.repository.UserRepository;
import com.mihailsergeevichs.todo.manager.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Overlord on 16.11.2015.
 */
@Component
public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskAddController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Scheduled(cron = "0 0 8 * * ?")
    public void reportAllUsers(){

        LOGGER.debug("Invoking method to send for users scheduled tasks by email");

        List<User> users = userRepository.findAll();

        for(User user : users){

        }

    }

}
