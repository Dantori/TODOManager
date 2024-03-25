package ru.trofimov.todomanager.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import ru.trofimov.todomanager.domain.account.Role;
import ru.trofimov.todomanager.web.validation.OnCreate;
import ru.trofimov.todomanager.web.validation.OnUpdate;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class UserDto {

    @NotNull(message = "Id must be not null!", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "Username must be not null!", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length mast be smaller than 255", groups = {OnCreate.class, OnUpdate.class})
    private String username;
    @NotNull(message = "Username must be not null!", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Username length mast be smaller than 255", groups = {OnCreate.class, OnUpdate.class})
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null!", groups = {OnCreate.class, OnUpdate.class})
    private String passwordConfirm;

    private Set<Role> roles;

    public UserDto() {}

    public UserDto(String username, String password, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
