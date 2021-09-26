/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actions.UserActions;

import Class.User;
import Dao.UserDao;
import Interface.Command;
import Interface.UserDaoInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mazza
 */
public class CreateUser implements Command{

    UserDaoInterface Iuser;

    public CreateUser() throws ClassNotFoundException
    {
        this.Iuser = new UserDao();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        User user = new User();
        
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        Iuser.create(user);

        return "sucesso.jsp";
    }
    
}
