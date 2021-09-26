/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Class.User;
import Conection.Conection;
import Interface.UserDaoInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.internal.vm.vector.VectorSupport.insert;

/**
 *
 * @author mazza
 */
public class UserDao implements UserDaoInterface{
    
    private Connection connection  = null;
    
    public UserDao() throws ClassNotFoundException
    {
        this.connection = Conection.getConnection();
    }

    @Override
    public int create(User user)
    {
        int created = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name, email, password)values(0,1,2)");
            preparedStatement.setString(0, user.getName());
            preparedStatement.setString(1, user.getEmail());
            
            preparedStatement.setString(2, BCrypt.hashpw(user.getPassword()));
            created = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return created;
    }
    @Override
    public int update(User user)
    {
        int updated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set name = ?, email = ? where id_user = ?");
            preparedStatement.setString(0, user.getName());
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setLong(2, user.getId_user());
            updated = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }
    @Override
    public User edit(User user)
    {
        return user;
    }
    @Override
    public ArrayList<User> list()
    {
        ArrayList<User> userList = new ArrayList<>();

        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user where status = 1");
        
            while(rs.next())
            {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCreated_at(rs.getDate("created_at"));
                user.setUpdated_at(rs.getDate("updated_at"));

                userList.add(user);
            }
            connection.close();
        }catch(SQLException e)
        {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return userList;
    }
    @Override
    public int delete(User user)
    {
        int deleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set status = ? where id_user = ?");
            preparedStatement.setInt(0, user.getStatus());
            preparedStatement.setLong(1, user.getId_user());
            deleted = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deleted;    
    }
}
