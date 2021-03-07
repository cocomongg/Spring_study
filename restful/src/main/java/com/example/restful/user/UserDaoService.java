package com.example.restful.user;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static{
        users.add(new User(1, "jm", new Date(), "pass1", "970927-1063017"));
        users.add(new User(2, "jm1", new Date(),  "pass2", "870927-1063017"));
        users.add(new User(3, "jm2", new Date(),  "pass3", "770927-1063017"));
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        for( User user: users){
            if(user.getId() == id)
                return user;
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public User updateById(int id, String name){
        for(User user : users){
            if(user.getId() == id){
                user.setName(name);
                return user;
            }
        }
        return null;
    }
}
