package repository;

import config.MysqlConfig;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository  {

    public List<RoleModel> getRoles(){
        List<RoleModel> list = new ArrayList();

        try{
            String query = "select * from roles;";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                RoleModel roleModel = new RoleModel();
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("role_name"));
                roleModel.setDesc(resultSet.getString("description"));

                list.add(roleModel);
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error getUsersByEmailAndPassword " + e.getMessage());
        }

        return list;
    }

    public int deleteRolesById(int id){
        int result = 0;
        try{
            String query = "delete from roles r where r.id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();


            connection.close();

        }catch (Exception e){
            System.out.println("Error deleteRoles " + e.getMessage());
        }

        return result;
    }

    public int editRolesById(int id, String name, String desc){
        int result = 0;
        try {
            String query = "update roles r set r.role_name = ?, r.description = ? where r.id = ?";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(3,id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, desc);
            result = preparedStatement.executeUpdate();
            connection.close();
        }
        catch (Exception e){
            System.out.println("Error editRoles " + e.getMessage());
        }
//        System.out.println("kiem tra edit: " + result);
        return result;
    }


}