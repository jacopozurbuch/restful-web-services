package com.restfulapi.restfulwebservices.User;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {

    private Integer id;

    @Size(min = 4, message = "the min lenght is 2")
    private String name;

    @Past
    private LocalDate birthday;

    protected User(){
        
    }

    public User(Integer id, String name, LocalDate birthday){
  
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;

    }


    public Integer getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public LocalDate getBirthday(){
        return this.birthday;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBirthday(LocalDate birthday){
        this.birthday = birthday;
    }


}