package repository;

import model.JobModel;
import config.MysqlConfig;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobRepository {

    public List<JobModel> getAllJobs(){
        List<JobModel> list = new ArrayList();

        try{
            String query = "select * from jobs;";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                JobModel jobModel = new JobModel();
                jobModel.setId(resultSet.getInt("id"));
                jobModel.setJobName(resultSet.getString("job_name"));
                jobModel.setStartDate(resultSet.getString("start_date"));
                jobModel.setEndDate(resultSet.getString("end_date"));

                list.add(jobModel);
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error getAllJobs " + e.getMessage());
        }

        return list;
    }

    public int deleteJobById(int id){
        int result = 0;
        try{
            String query = "delete from jobs j where j.id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();


            connection.close();

        }catch (Exception e){
            System.out.println("Error deleteJob " + e.getMessage());
        }

        return result;
    }

    public int editJobById(int id, String jobName, String startDate, String endDate){
        int result = 0;
        try {
            String query = "update jobs j set j.job_name = ?, j.start_date = ?, j.end_date = ? where j.id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(4,id);
            preparedStatement.setString(1, jobName);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);

            result = preparedStatement.executeUpdate();
            connection.close();
        }
        catch (Exception e){
            System.out.println("Error editJob " + e.getMessage());
        }
//        System.out.println("kiem tra edit: " + result);
        return result;
    }
}
