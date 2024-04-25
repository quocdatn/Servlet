package controller;

import config.MysqlConfig;
import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginController",urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //cookie
//        Cookie cookie = new Cookie("username","nguyenvana");
//        //Yêu cầu client khởi tạo cookie
//        resp.addCookie(cookie);
//        Cookie[] cookies = req.getCookies();
////        [ Cookie("name","value"),Cookie("name","value"),Cookie("name","value")]
//        for (Cookie item : cookies) {
//            if (item.getName().equals("username")){
//                System.out.println("Kiểm tra: " + item.getValue());
//            }
//        }

//        HttpSession session = req.getSession();
//        session.setAttribute("password","123456");
//
//        System.out.println("Session: " + session.getAttribute("password"));

        Cookie[] cookies = req.getCookies();
        String username = "";
        String password = "";
        if (cookies != null){
            for (Cookie item : cookies) {
                if(item.getName().equals("username")){
                    username = item.getValue();
                }

                if (item.getName().equals("password")){
                    password = item.getValue();
                }
            }
        }


        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;

        //B1: lấy tham số username and password từ người dùng
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        boolean isSuccess = loginService.checkLogin(email,password);
        if (isSuccess){
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/dashboard");
        }else {
            PrintWriter writer = resp.getWriter();
            writer.println("Login fail!");
            writer.close();
        }

    }
}