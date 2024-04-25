package api;

import com.google.gson.Gson;
import payload.ResponseData;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editUserApi", urlPatterns = {"/api/editUser"})
public class EditUserApi extends HttpServlet {
    private UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String fullname = req.getParameter("fullname");
        int roleId = Integer.parseInt(req.getParameter("roleId"));

        boolean isSuccess = userService.editUserById(id, fullname, roleId);

        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess ? "Sua thanh cong" : "Sua that bai");

        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        out.println(json);
        out.flush();
    }
}
