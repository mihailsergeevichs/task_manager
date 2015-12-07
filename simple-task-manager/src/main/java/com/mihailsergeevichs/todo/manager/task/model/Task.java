package com.mihailsergeevichs.todo.manager.task.model;

import com.mihailsergeevichs.todo.manager.common.model.BaseEntity;
import com.mihailsergeevichs.todo.manager.user.model.User;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * Created by Overlord on 09.11.2015.
 */
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "label", nullable = false, length = 55)
    private String label;

    @Column(name = "date_to_execute", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateToExecute;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 20, nullable = true)
    private Category category;

    @Column(name = "checked", nullable = false)
    private boolean checked;

    @ManyToOne
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public Task() {
    }

    public Task(String label, LocalDate dateToExecute, String description, Category category, boolean checked, User user) {
        this.label = label;
        this.dateToExecute = dateToExecute;
        this.description = description;
        this.category = category;
        this.checked = checked;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", dateToExecute=" + dateToExecute +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", checked=" + checked +
                ", user=" + user +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateToExecute() {
        return dateToExecute;
    }

    public void setDateToExecute(LocalDate dateToExecute) {
        this.dateToExecute = dateToExecute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
