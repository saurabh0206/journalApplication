package net.saurabh.journal.services;


import net.saurabh.journal.entity.User;
import net.saurabh.journal.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    public UserRepo userRepo;

    public void saveUser(User user){

        userRepo.save(user);
    }

    public List<User> getAll() {
        return  userRepo.findAll();

    }



     public User findByUserName(String userName){
         return  userRepo.findByUserName(userName);

     }
    public void deleteByID(ObjectId id){
        userRepo.deleteById(id);
    }


}
