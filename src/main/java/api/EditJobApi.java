package api;

import com.google.gson.Gson;
import payload.ResponseData;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "editJob", urlPatterns = {"/api/editJob"})
public class EditJobApi extends HttpServlet {
    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String jobName = req.getParameter("job_name");
        String startDate = req.getParameter("start_date");
        String endDate = req.getParameter("end_date");

        boolean isSuccess = jobService.editJobById(id, jobName, startDate, endDate);

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
