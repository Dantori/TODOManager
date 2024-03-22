package ru.trofimov.todomanager.web.dto;

import org.hibernate.validator.constraints.Length;
import ru.trofimov.todomanager.domain.account.User;
import ru.trofimov.todomanager.web.validation.OnCreate;
import ru.trofimov.todomanager.web.validation.OnUpdate;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TodoDto {

    @NotNull(message = "Id must be not null!", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Description must be not null!", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Description length mast be smaller than 255", groups = {OnCreate.class, OnUpdate.class})
    private String description;
    @NotNull(message = "Date must be not null!", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Date length mast be smaller than 255", groups = {OnCreate.class, OnUpdate.class})
    private Date date;
    @NotNull(message = "Completed must be not null!", groups = {OnCreate.class, OnUpdate.class})
    private boolean completed;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
