package controller;

import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "rolePage", urlPatterns = {"/role"})
public class RolePage extends HttpServlet {

    private RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Goi link");
        req.setAttribute("roles", roleService.getAllRoles());

        req.getRequestDispatcher("/role-table.jsp").forward(req,resp);
    }
}