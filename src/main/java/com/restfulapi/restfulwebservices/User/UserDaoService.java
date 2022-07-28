package com.restfulapi.restfulwebservices.User;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.time.LocalDate;

@Component
public class UserDaoService {
    
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static {
        users.add(new User(1, "john arnold", LocalDate.of(1990, 5, 5)));
        users.add(new User(2, "Betty arnold", LocalDate.of(1988,5,5)));
        users.add(new User(3, "Lil arnold", LocalDate.of(2020,1,3)));
    }


    public List<User> findAll(){
        return users;
    }

    public User save(User user){
      if ( user.getId() == null ){
          user.setId(++userCount);
      }
      users.add(user);
      return user;
    }

    public User findOne(int id){ 
        for (User user:users) {
            if (user.getId()==id){
                 return user;
            }
        }
        return null;
    }

    public User deleteById(int id){ 
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if ( user.getId() == id ) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
