package repository;

import config.MysqlConfig;
import model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<TaskModel> getAllTasks(){
        List<TaskModel> list = new ArrayList();

        try{
            String query = "select\n" +
                    " t.id as id,\n" +
                    " task_name, \n" +
                    " u.fullname as user_name, \n" +
                    " job_name, status,\n" +
                    " t.start_date as start_date,\n" +
                    " t.end_date as end_date\n" +
                    "from tasks t, users u, jobs j, status s\n" +
                    "where t.user_id = u.id and\n" +
                    "t.job_id = j.id\n" +
                    "and t.status_id = s.id\n" +
                    "order by t.id;\n";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setTaskName(resultSet.getString("task_name"));
                taskModel.setUserName(resultSet.getString("user_name"));
                taskModel.setJobName(resultSet.getString("job_name"));
                taskModel.setStatus(resultSet.getString("status"));
                taskModel.setStartDate(resultSet.getString("start_date"));
                taskModel.setEndDate(resultSet.getString("end_date"));

                list.add(taskModel);
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error getAllTasks " + e.getMessage());
        }

        return list;
    }

    public int deleteTaskById(int id){
        int result = 0;
        try{
            String query = "delete from tasks t where t.id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();

            connection.close();

        }catch (Exception e){
            System.out.println("Error deleteTask " + e.getMessage());
        }

        return result;
    }

    public int editTaskById(int id, String taskName, int jobId, int userId, int statusId){
        int result = 0;
        try {
//            update tasks t set t.task_name = "Code cho vui", t.job_id = 2, t.user_id = 1, t.status_id = 1 where t.id = 6;

            String query = "update tasks t set t.task_name = ?, t.job_id = ?, t.user_id = ?, t.status_id = ? where t.id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(5,id);
            preparedStatement.setString(1, taskName);
            preparedStatement.setInt(2, jobId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, statusId);

            result = preparedStatement.executeUpdate();
            connection.close();
        }
        catch (Exception e){
            System.out.println("Error editTask " + e.getMessage());
        }
//        System.out.println("kiem tra edit: " + result);
        return result;
    }
}
