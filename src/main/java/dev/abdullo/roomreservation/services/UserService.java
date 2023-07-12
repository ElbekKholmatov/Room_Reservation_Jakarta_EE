package dev.abdullo.roomreservation.services;

import dev.abdullo.roomreservation.dao.FieldDAO;
import dev.abdullo.roomreservation.dao.UserDAO;
import dev.abdullo.roomreservation.domains.User;
import dev.abdullo.roomreservation.enums.Roles;
import dev.abdullo.roomreservation.utils.Encrypt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    public static final ThreadLocal<UserService> instance = ThreadLocal.withInitial(UserService::new);
//    public static final int minutes = 60;
//    public static final int hours = 3600;
    public static final int oneDay = 86400;
    public static final String USER_PAGE = "/user";
    public static final String ADMIN_PAGE = "/tafakkooradmin";

    public static UserService getInstance() {
        return instance.get();
    }

    public Cookie createCookie(String value) {
        Cookie cookie = new Cookie("remember_me", value);
        cookie.setPath("/");
        cookie.setMaxAge(oneDay);
        return cookie;
    }

    public boolean changeDeleted(Integer userId, boolean deleted) {

        //todo check userId  is valid
        boolean result = UserDAO.getInstance().changeDeleted(Long.valueOf(userId), deleted);
        return result;
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fieldId = request.getParameter("field_id");

        User newUser = User.builder()
                .email(email)
                .username(username)
                .password(Encrypt.hashPassword(password))
                .field(FieldDAO.getInstance().findById(Long.parseLong(fieldId)))
                .build();

        UserDAO.getInstance().save(newUser);
        response.sendRedirect("/login");
    }

    public boolean isCorrectPassword(String password, String hashedPassword) {
        return Encrypt.checkPassword(password, hashedPassword);
    }

    public boolean isCorrectPass(String userId, String password) {
        User user = null;
        try {
            user = UserDAO.getInstance().findById(Long.parseLong(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCorrectPassword(password, user.getPassword());
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        String newPassword = request.getParameter("new_password");

        UserDAO instance = UserDAO.getInstance();
        User user = instance.findById(Long.parseLong(userId));
        user.setPassword(Encrypt.hashPassword(newPassword));
        instance.update(user);
        response.sendRedirect("/logout");
    }


    public User findById(Long id) {
        User user = null;
        try {
            user = UserDAO.getInstance().findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public User findByUsername(String username) {
        User user = null;
        try {
            user = UserDAO.getInstance().findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public boolean update(User user) {
        return UserDAO.getInstance().update(user);
    }

    public static String redirectByRole(User user) {
        Roles role = user.getRole();
        return switch (role) {
            case ADMIN -> ADMIN_PAGE;
            case USER -> USER_PAGE;
            default -> "/";
        };
    }
}
