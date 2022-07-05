package com.example.spring_security_version.utils;

import com.example.spring_security_version.entity.ResponsableRH;
import com.example.spring_security_version.secutity.AppUser;
import com.example.spring_security_version.secutity.UserService;
import com.example.spring_security_version.service.ChefDepartementService;
import com.example.spring_security_version.service.ResponsableRHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstTimeInitializer implements CommandLineRunner{

    @Autowired
    private UserService userService;

    @Autowired
    private ResponsableRHService responsableRHService;

    @Override
    public void run(String... args) throws Exception {

        if(userService.findAll().isEmpty()){
            AppUser user = new AppUser("admin","00001","admin");
            userService.save(user);
            ResponsableRH responsableRH = new ResponsableRH("admin","RESPONSABLERH","status");
            responsableRHService.save(responsableRH);

        }
    }
}
