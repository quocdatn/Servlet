package controller;

import model.RoleModel;
import model.UserModel;
import service.UserService;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userController", urlPatterns = {"/user","/user/add","/user/delete"})
public class UserController extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getServletPath(): Lấy đường dẫn servlet người dùng gọi trên browser
        String path = req.getServletPath();
        switch (path){
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            default:
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/user":
                getAllUser(req,resp);
                break;
            case "/user/add":
                addUser(req,resp);
                break;
            default:
                break;
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = userService.deleteUser(id);

    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userService.getAllUser();
        req.setAttribute("listUsers",list);
        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        List<RoleModel> listRoles = userService.getAllRole();
        if (method.equalsIgnoreCase("post")){
            String fullname = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int roleId = Integer.parseInt(req.getParameter("role"));
            userService.insertUser(email,password,fullname,roleId);
        }
        req.setAttribute("listRole",listRoles);
        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }


}
