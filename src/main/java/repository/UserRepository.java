package repository;

import config.MysqlConfig;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<UserModel> findByEmailAndPassword(String email, String password){
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users u WHERE u.email = ? and u.password = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định và lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRole(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error FindEmailAndPassword: " + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối FindEmailAndPassword: " + e.getMessage());
                }
            }
        }

        return userModelList;
    }

    public List<UserModel> findAll(){

        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users u";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định và lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRole(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error FindAll: " + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối FindAll: " + e.getMessage());
                }
            }
        }

        return userModelList;
    }

    public boolean insertUser(String fullname, String email, String password, int roleId){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT into users(email,password,fullname,role_id) value(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setInt(4,roleId);

            isSuccess = statement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi Query." + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi insert user: " + e.getMessage());
                }
            }
        }

        return isSuccess;
    }

    public boolean deleteById(int id){
        Connection connection = null;
        boolean isSuccess = false;
        try{
            connection = MysqlConfig.getConnection();
            String sql = "delete FROM users u WHERE u.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
            isSuccess = statement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi Query deleteById." + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteById: " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public int editUserById(int id, String fullname, int roleId){
        int result = 0;
        try {
            String query = "update users u set u.fullname = ?, u.role_id = ? where u.id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(3,id);
            preparedStatement.setString(1, fullname);
            preparedStatement.setInt(2, roleId);
            result = preparedStatement.executeUpdate();
            connection.close();
        }
        catch (Exception e){
            System.out.println("Error editUserInfo " + e.getMessage());
        }
//        System.out.println("kiem tra edit: " + result);
        return result;
    }

    public int deleteUserById(int id){

        int result = 0;
        try{
            String query = "delete from users u where u.id = ?;";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();


            connection.close();

        }catch (Exception e){
            System.out.println("Error deleteUser " + e.getMessage());
        }

        return result;
    }

}
