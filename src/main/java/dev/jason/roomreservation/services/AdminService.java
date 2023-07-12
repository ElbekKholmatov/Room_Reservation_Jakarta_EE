package dev.jason.roomreservation.services;

import dev.jason.roomreservation.dao.UserDAO;
import dev.jason.roomreservation.domains.User;

import java.util.List;

public class AdminService {


    private static final AdminService service = ThreadLocal.withInitial(AdminService::new).get();


    public static AdminService getInstance() {
        return service;
    }

    public List<User> usersList(Long adminId) {
        return UserDAO.getInstance().getPage(adminId);
    }


    public boolean changeRole(Integer id, String role) {
        //todo check role is valid
        return UserDAO.getInstance().changeRole(id, role);
    }

    public boolean changeDeleted(Integer userId, boolean deleted) {

        //todo check userId  is valid
        return UserDAO.getInstance().changeDeleted(Long.valueOf(userId), deleted);
    }
}