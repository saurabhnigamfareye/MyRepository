package com.example.controllers;

import com.example.Users.User;
import com.example.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by saurabh on 6/8/16.
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="userid")
//    private Long userId;
//
//    @Column(name = "username")
//    private String userName;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name ="enabled")
//    private int enabled;



    @RequestMapping(value = "userDisplay")
    public List<User> display()
    {
        return userService.display();
    }

    @RequestMapping(value = "/userCreate")
    public User create(@RequestBody User user )
    {


//        User user= new Inventory(productName,costParsed,unitOfMeasurement,openingBalanceParsed,dimensionParsed);


        if(userService.insert(user))
        {
            return user;
        }
        return null;
    }

    @RequestMapping("userDelete")
    public User delete(@RequestParam("userId") String userId)
    {

        Long userIdParsed=Long.parseLong(userId);
        if(userService.delete(userIdParsed))
        {
            return new User();
        }
        else
        {
            return null;
        }
    }

    @RequestMapping("userUpdate")
    public User update(@RequestBody User user)
    {


        if(userService.update(user))
        {
            System.out.print("Inside Update if");
            return new User("successfull");
        }
        else
        {
            System.out.print("Inside Update else");
            return null;
        }

    }

}
