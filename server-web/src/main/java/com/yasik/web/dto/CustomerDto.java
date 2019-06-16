package com.yasik.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.yasik.model.entity.Feedback;
import com.yasik.model.entity.Order;
import com.yasik.model.entity.customer.Address;
import com.yasik.model.entity.customer.Authorities;
import com.yasik.model.entity.customer.Customer;
import com.yasik.web.dto.transfer.View;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerDto {

    //@Null(groups = New.class)
    @JsonView({View.UserPrivilege.class, View.AdminPrivilege.class})
    @NotNull(groups = {View.New.class, View.Exist.class})
    private long id;

    @JsonView(View.AdminPrivilege.class)
    @NotNull(groups = {View.New.class, View.Exist.class})
    @Email(groups = {View.New.class, View.Exist.class})
    private String username;

    @JsonView(View.AdminPrivilege.class)
    @NotNull(groups = {View.New.class, View.Exist.class})
    private String password;

    @JsonView({View.UserPrivilege.class, View.AdminPrivilege.class})
    @NotNull(groups = {View.New.class, View.Exist.class})
    private String name;

    @JsonView({View.UserPrivilege.class, View.AdminPrivilege.class})
    @NotNull(groups = {View.New.class, View.Exist.class})
    private String surname;

    @JsonView({View.UserPrivilege.class, View.AdminPrivilege.class})
    @NotNull(groups = {View.New.class, View.Exist.class})
    private String gender;

    @JsonView({View.UserPrivilege.class, View.AdminPrivilege.class})
    @NotNull(groups = {View.New.class, View.Exist.class})
    private String dateOfBirthday;

    @JsonView({View.UserPrivilege.class, View.AdminPrivilege.class})
    @NotNull(groups = {View.New.class, View.Exist.class})
    private String phoneNumber;

    @JsonView({View.AdminPrivilege.class})
    @NotNull(groups = View.New.class)
    private Collection<Authorities> authorities;

    @JsonIgnore
    private List<Address> addresses;

    @JsonIgnore
    private List<Feedback> feedbacks;

    @JsonIgnore
    private List<Order> orders;

    public CustomerDto() {
    }

    public LocalDate getConvertedDateOfBirthday() {
        return LocalDate.parse(dateOfBirthday);
    }

    public void setConvertedDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authorities> authorities) {
        this.authorities = authorities;
        List<Authorities> authoritiesList = new ArrayList<>(authorities);
        Customer customer = authoritiesList.get(0).getCustomer();
        this.authorities.forEach(auth -> auth.setCustomer(customer));
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirthday='" + dateOfBirthday + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
