package com.mihailsergeevichs.todo.manager.task.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Created by Overlord on 10.11.2015.
 */
public class TaskForm {

    @NotEmpty(message = "Label should be not empty and not more than 55 characters")
    @Size(max = 55)
    private String label;

    @NotNull
    public String dateTime;

    @NotEmpty
    private String category;

    @NotEmpty(message = "Description should be not empty and not more than 255 characters")
    @Size(max = 255)
    private String description;

    public TaskForm() {
    }

    public TaskForm(String label, String dateTime, String category, String description) {
        this.label = label;
        this.dateTime = dateTime;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "label='" + label + '\'' +
                ", dateTime=" + dateTime +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
