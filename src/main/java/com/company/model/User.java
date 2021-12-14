package com.company.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {
    @NotEmpty(message = "Enter your first name!")
    private String firstName;

    @NotEmpty(message = "Enter your last name!")
    private String lastName;

    @NotEmpty(message = "Enter your middle name!")
    private String middleName;

    @NotNull(message = "Enter your age!")
    @Min(value = 0, message = "Enter the correct age!")
    private Integer age;

    @NotNull(message = "Enter your salary!")
    @Min(value = 0,message = "Enter the correct salary!")
    private Integer salary;

    @NotEmpty(message = "Enter your email!")
    @Email(message = "Enter the correct email")
    private String email;

    @NotEmpty(message = "Enter your job!")
    private String job;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
