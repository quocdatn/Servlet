package service;

import model.TaskModel;
import repository.TaskRepository;

import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();

    public List<TaskModel> getAllTasks(){
        return taskRepository.getAllTasks();
    }

    public boolean deleteTaskById(int id){

        return taskRepository.deleteTaskById(id) > 0;
    }

    public boolean editTaskById(int id, String taskName, int jobId, int userId, int statusId)
    {
        return taskRepository.editTaskById(id, taskName, jobId, userId, statusId) > 0;
    }
}
