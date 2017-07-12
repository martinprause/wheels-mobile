package com.doit.wheels.dao.entities;

import com.doit.wheels.dao.entities.basic.Contact;
import com.doit.wheels.utils.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends Contact {

    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    private String employeeNo;

    private String comment;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AccessLevel> accesses;

    @OneToMany(mappedBy = "createdByUser")
    private List<Order> createdOrders;

    @OneToMany(mappedBy = "lastUpdatedByUser")
    private List<Order> lastUpdatedOrders;

    @OneToMany(mappedBy = "driver")
    private List<Order> driverOrders;


    public User() {

    }

    public User(String login, String password) {
        this.username = login;
        this.password = password;
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

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonIgnore
    public Set<AccessLevel> getAccesses() {
        return accesses;
    }

    public void setAccesses(Set<AccessLevel> accesses) {
        this.accesses = accesses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof User){
            if (((User) obj).getId().equals(this.getId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = this.getId().intValue();
        result = 31 * result + getUsername().hashCode();
        return result;
    }

    @JsonIgnore
    public List<Order> getCreatedOrders() {
        return createdOrders;
    }

    public void setCreatedOrders(List<Order> createdOrders) {
        this.createdOrders = createdOrders;
    }

    @JsonIgnore
    public List<Order> getLastUpdatedOrders() {
        return lastUpdatedOrders;
    }

    public void setLastUpdatedOrders(List<Order> lastUpdatedOrders) {
        this.lastUpdatedOrders = lastUpdatedOrders;
    }

    @JsonIgnore
    public List<Order> getDriverOrders() {
        return driverOrders;
    }

    public void setDriverOrders(List<Order> driverOrders) {
        this.driverOrders = driverOrders;
    }
}
