package com.mihailsergeevichs.todo.manager.task.service;

import com.mihailsergeevichs.todo.manager.task.model.Task;
import com.mihailsergeevichs.todo.manager.task.dto.TaskForm;
import com.mihailsergeevichs.todo.manager.user.model.User;

import java.util.List;

/**
 * Created by Overlord on 10.11.2015.
 */
public interface TaskService {

    public List<Task> findAll(String email);

    public List<Task> findChecked(String email);

    public List<Task> findUnchecked(String email);

    public Task findTask(String email, Long taskId);

    public void saveTask(String email, Task task);

    public void removeTask(User user, Task task);

    public Task createNewTask(TaskForm taskForm, String email);

}
