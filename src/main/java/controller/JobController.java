package controller;


import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jobController", urlPatterns = {"/job"})
public class JobController extends HttpServlet {

    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("jobsList", jobService.getAllJobs());
//        System.out.println(jobService.getAllJobs().get(1).getJobName());
        req.getRequestDispatcher("/groupwork.jsp").forward(req,resp);
    }
}
