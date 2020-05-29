package com.rithin.springboot.practise.service;


import com.rithin.springboot.practise.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users  = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1,"Rithin",new Date()));
        users.add(new User(2,"Sai",new Date()));
        users.add(new User(3,"Gullapalli",new Date()));
    }

    public List<User> finadAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findUser(int id){

        for(User user : users){
            if(id == user.getId()){
                return user;
            }
        }
        return null;
    }

    public User deleteUser(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(id == user.getId()){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
