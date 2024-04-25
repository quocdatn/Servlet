package controller;

import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "taskController", urlPatterns = {"/task"})
public class TaskController extends HttpServlet {
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tasksList",taskService.getAllTasks());

//        System.out.println(taskService.getAllTasks().size());
        req.getRequestDispatcher("/task.jsp").forward(req,resp);

    }
}
