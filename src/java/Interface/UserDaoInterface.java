/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Class.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mazza
 */
public interface UserDaoInterface {
    
    public int create(User user);
    public int update(User user);
    public User edit(User user);
    public ArrayList<User> list();
    public int delete(User user);
}
