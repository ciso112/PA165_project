/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165project.service.config;

import com.muni.fi.pa165project.dto.UserRegisterDTO;
import com.muni.fi.pa165project.entity.User;
import org.dozer.DozerBeanMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Radoslav Karlik
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ServiceConfiguration.class);
        DozerBeanMapper mapper = ctx.getBean(DozerBeanMapper.class);
        
        UserRegisterDTO dto = createUser("Radim Novák", true,"25-06-1991", 171, 70, false,
                "rnovak", "12345", "rnovak@fi.muni.cz");
        
        User user = mapper.map(dto, User.class);
        System.out.println(user.getBirthDate());
    }
    
    private static UserRegisterDTO createUser(String name, boolean male, String date, int height, int weight, boolean admin,
                            String username, String password, String email){
        UserRegisterDTO user = new UserRegisterDTO();
        user.setName(name);
        user.setIsMale(male);
        user.setBirthDate(date);
        user.setHeight(height);
        user.setWeight(weight);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }
}
