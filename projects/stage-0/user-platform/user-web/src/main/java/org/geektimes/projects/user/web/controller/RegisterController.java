package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.projects.user.sql.DBConnectionManager;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.SQLException;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/user")
public class RegisterController implements PageController {


    @GET
    @POST
    @Path("/register") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        System.out.println("register start");

        if (HttpMethod.POST.equalsIgnoreCase(request.getMethod())){
            System.out.println("进行注册");
            DBConnectionManager dbConnectionManager = new DBConnectionManager();
            DatabaseUserRepository databaseUserRepository = new DatabaseUserRepository(dbConnectionManager);
            UserServiceImpl userService = new UserServiceImpl(databaseUserRepository);
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setPhoneNumber(request.getParameter("phone"));
            boolean register = userService.register(user);
            if (register){
                User user1 = userService.queryUserByNameAndPassword(request.getParameter("name"), request.getParameter("password"));
                System.out.println(user1);
                System.out.println("注册完成，新用户 " + user1.getName());
            }
            return "login-form.jsp";
        }
        return "register-form.jsp";
    }

}
