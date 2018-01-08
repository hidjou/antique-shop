/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import com.antique.model.User;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.trueFalseType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hidjou
 */
@Stateless
public class UserDao implements UserDaoLocal {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean authenticateUser(String username, String password) {
       List<User> users = em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
       if(users.isEmpty()){
           return false;
       } else {
           User user = users.get(0);
           return password.equals(user.getPassword());
           }
       }
    }
