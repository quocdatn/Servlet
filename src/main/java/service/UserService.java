package service;

import model.RoleModel;
import model.UserModel;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();

    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }

    public boolean insertUser(String email, String password, String fullname, int roleId){
        return userRepository.insertUser(fullname,email,password,roleId);

    }

    public boolean deleteUser(int id) {
        return userRepository.deleteById(id);
    }

    public List<RoleModel> getAllRole(){
        return roleRepository.getRoles();
    }

    public boolean editUserById(int id, String fullname, int roleId){
        int result = userRepository.editUserById(id, fullname, roleId);

        return result > 0 ? true : false;
    }

    public boolean deleteUserById(int id){

        int result  = userRepository.deleteUserById(id);

        return result > 0 ? true : false;

    }


}
