package api;

import com.google.gson.Gson;
import payload.ResponseData;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editTask", urlPatterns = {"/api/editTask"})
public class EditTaskApi extends HttpServlet {
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String taskName = req.getParameter("task_name");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        int jobId = Integer.parseInt(req.getParameter("job_id"));
        int statusId = Integer.parseInt(req.getParameter("status_id"));

        boolean isSuccess = taskService.editTaskById(id, taskName, userId, jobId, statusId);

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
