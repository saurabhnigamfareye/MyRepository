package com.example.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by saurabh on 6/8/16.
 */


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRolesRepository userRolesRepository;

    @Transactional
    public boolean  insert(User user)
    {
        if(userRepository.save(user)!= null )
//        &&  userRolesRepository.save(userRole)!=null)
        {
            return true;
        }
        return false;
    }
    @Transactional
    public List<User> display()
    {
       return userRepository.findAll();
//        System.out.println(user);
    }

//    @Transactional
//    public List<String> displayRole(String userName)
//    {
//        return userRolesRepository.findRoleByUserName(userName);
//    }

//    @Transactional
//    public boolean update(Long userId,String userName, String email, int enabled, String userRole , String password)
//    {   User user= userRepository.findByUserId(userId);
//        UserRole userRole1= (UserRole) userRolesRepository.findRoleByUser(user);
//        if(user!=null  && userRole1!=null)
//        {
//            user.setEmail(email);
//            user.setEnabled(enabled);
//            user.setPassword(password);
//            user.setUserName(userName);
//            userRole1.setRole(userRole);
//            return true;
//        }
//        return false;
//    }

    @Transactional
    public boolean delete(long id) {
        User user= userRepository.findByUserId(id);
//        UserRole userRole1= (UserRole) userRolesRepository.findRoleByUserid(id);
        if (user!=null ) {
            userRepository.delete(user);
//            userRolesRepository.delete(userRole1);
            return true;
        }
        return false;
    }

@Transactional
    public boolean update(User user2) {
        User user= userRepository.findByUserId(user2.getUserId());
        if(user!=null )
        {
//            System.out.println(user2.getUserName()+" "+user2.getEmail());
//            System.out.println(user.getUserName());
            user.setEmail(user2.getEmail());
            user.setEnabled(user2.getEnabled());
            user.setPassword(user2.getPassword());
            user.setUserName(user2.getUserName());
          user.setUserRole(user2.getUserRole());

            return true;
        }
        return false;
    }
}
