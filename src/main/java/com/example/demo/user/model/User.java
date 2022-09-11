package com.example.demo.user.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
public class User{

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="name")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 15, message = "Name should be between 3 and 15 characters")
    private String name;

    @Column(name="age")
    @NotNull
    @Min(value = 0, message = "Age should be a positive number")
    @Max(value = 100, message = "Are u really older than 100?")
    private int age;

    @Column(name="email")
    @Email(message = "email should have special structure")
    private String email;

    public User(){

    }

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User(Long id, String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.Id = id;
        this.email = email;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}

