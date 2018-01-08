/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antique.dao;

import javax.ejb.Local;

/**
 *
 * @author Hidjou
 */
@Local
public interface UserDaoLocal {

    boolean authenticateUser(String username, String password);
    
}
