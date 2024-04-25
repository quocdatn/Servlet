package service;

import model.RoleModel;
import repository.RoleRepository;

import java.util.List;

public class RoleService {

    RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getAllRoles(){

        return roleRepository.getRoles();


    }

    public boolean deleteRolesById(int id){
        int result = roleRepository.deleteRolesById(id);

        return result > 0 ? true : false;
    }

    public boolean editRolesById(int id, String name, String desc){
        int result = roleRepository.editRolesById(id, name, desc);

        return result > 0 ? true : false;
    }
}