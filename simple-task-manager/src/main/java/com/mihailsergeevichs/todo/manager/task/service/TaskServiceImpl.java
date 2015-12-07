package com.mihailsergeevichs.todo.manager.task.service;

import com.mihailsergeevichs.todo.manager.task.model.Task;
import com.mihailsergeevichs.todo.manager.user.repository.UserRepository;
import com.mihailsergeevichs.todo.manager.task.dto.TaskForm;
import com.mihailsergeevichs.todo.manager.task.model.Category;
import com.mihailsergeevichs.todo.manager.user.model.User;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Overlord on 12.11.2015.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);


    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public List<Task> findAll(String email) {

        LOGGER.debug("Getting all tasks from user " + email);

        User user = userRepository.findByEmail(email);
        return user.getTasks();
    }

    @Override
    @Transactional
    public List<Task> findChecked(String email) {
        User user = userRepository.findByEmail(email);
        List<Task> checked = new ArrayList<>();
        for(Task task : user.getTasks()){
            if(task.isChecked()){
                checked.add(task);
            }
        }
        return checked;
    }

    @Override
    @Transactional
    public List<Task> findUnchecked(String email) {

        LOGGER.debug("Getting all unchecked tasks from user " + email);

        User user = userRepository.findByEmail(email);
        List<Task> unchecked = new ArrayList<>();
        for(Task task : user.getTasks()){
            if(!task.isChecked()){
                unchecked.add(task);
            }
        }
        return unchecked;
    }

    @Override
    @Transactional
    public Task findTask(String email, Long taskId) {

        LOGGER.debug("Getting task " + taskId +  " from user " + email);
        User user = userRepository.findByEmail(email);
        for(Task task : user.getTasks()){
            if(task.getId().equals(taskId)){
                return task;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void saveTask(String email, Task task) {

        LOGGER.debug("Saving task " + task + " to user " + email);

        User user = userRepository.findByEmail(email);
        user.getTasks().add(task);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void removeTask(User user, Task task) {

        LOGGER.debug("Removing task " + task + " to user " + user.getEmail());

        user.getTasks().remove(task);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public Task createNewTask(TaskForm taskForm, String email) {

        LOGGER.debug("Creating new task with taskForm parameters" + taskForm + " to user " + email);
        User user = userRepository.findByEmail(email);
        List<Task> tasks = user.getTasks();

        final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/mm/yyyy");

        Task task = new Task(taskForm.getLabel(), dtf.parseLocalDate(taskForm.getDateTime()),
                taskForm.getDescription(), Category.valueOf(taskForm.getCategory()),
                false, user);

        tasks.add(task);
        user.setTasks(tasks);
        userRepository.saveAndFlush(user);
        LOGGER.debug("Task " + task + " has successfully added to user " + email);
        return task;

    }
}
